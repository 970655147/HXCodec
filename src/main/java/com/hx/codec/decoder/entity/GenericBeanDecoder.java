package com.hx.codec.decoder.entity;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.interceptor.FieldInterceptContext;
import com.hx.codec.interceptor.FieldInterceptor;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.GenericFieldSchema;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.lang.reflect.Method;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 14:19
 */
public class GenericBeanDecoder<T> extends AbstractDecoder<T> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanDecoder(GenericBeanSchema<T> beanSchema) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
    }

    @Override
    public T decode(ByteBuf buf) {
        return ByteBufUtils.doWith(buf.copy(), dupBuf -> {
            try {
                Class<T> clazz = beanSchema.getClazz();
                Object result = clazz.newInstance();

                List<GenericFieldSchema> fieldSchemaList = beanSchema.getFieldSchemaList();
                FieldInterceptContext fieldInterceptContext = new FieldInterceptContext();
                wrapSubjectRelated(fieldInterceptContext, beanSchema, (T) result, buf);
                for (GenericFieldSchema fieldSchema : fieldSchemaList) {
                    AbstractCodec codec = fieldSchema.getCodec();
                    wrapFieldRelated(fieldInterceptContext, fieldSchema, null);
                    decodedField(codec, buf, fieldInterceptContext);
                }
                return (T) result;
            } catch (Exception e) {
                e.printStackTrace();
                AssertUtils.state(false, " an error occurred while decode the buf : " + ByteBufUtil.hexDump(dupBuf));
                return null;
            }
        });
    }

    // ------------------------------------------ assist methods ------------------------------------------

    /**
     * decodedField
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:08
     */
    private void decodedField(AbstractCodec codec, ByteBuf buf, FieldInterceptContext fieldInterceptContext) throws Exception {
        FieldInterceptor fieldInterceptor = fieldInterceptContext.getFieldSchema().getFieldInterceptor();
        if (fieldInterceptor != null) {
            fieldInterceptor.beforeDecode(fieldInterceptContext);
        }

        Object fieldValue = codec.decode(buf);
        GenericFieldSchema fieldSchema = fieldInterceptContext.getFieldSchema();
        Object subjectValue = fieldInterceptContext.getSubjectValue();
        Method setterMethod = fieldSchema.getSetterMethod();
        setterMethod.invoke(subjectValue, fieldValue);

        if (fieldInterceptor != null) {
            fieldInterceptContext.setFieldValue(fieldValue);
            fieldInterceptor.afterDecode(fieldInterceptContext);
        }
    }

    /**
     * wrapSubjectRelated
     *
     * @param fieldInterceptContext fieldInterceptContext
     * @param entity                entity
     * @param byteBuf               byteBuf
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:11
     */
    private void wrapSubjectRelated(FieldInterceptContext fieldInterceptContext, GenericBeanSchema beanSchema, T entity, ByteBuf byteBuf) {
        fieldInterceptContext.setBeanSchema(beanSchema);
        fieldInterceptContext.setSubjectValue(entity);
        fieldInterceptContext.setByteBuf(byteBuf);
    }

    /**
     * wrapFieldRelated
     *
     * @param fieldInterceptContext fieldInterceptContext
     * @param fieldSchema           fieldSchema
     * @param fieldValue            fieldValue
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:12
     */
    private void wrapFieldRelated(FieldInterceptContext fieldInterceptContext, GenericFieldSchema fieldSchema, Object fieldValue) {
        fieldInterceptContext.setFieldSchema(fieldSchema);
        fieldInterceptContext.setFieldValue(fieldValue);
    }
}
