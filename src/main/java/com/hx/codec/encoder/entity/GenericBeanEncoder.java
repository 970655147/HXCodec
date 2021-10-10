package com.hx.codec.encoder.entity;

import com.alibaba.fastjson.JSON;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.interceptor.FieldInterceptContext;
import com.hx.codec.interceptor.FieldInterceptor;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.GenericFieldSchema;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 13:40
 */
public class GenericBeanEncoder<T> extends AbstractEncoder<T> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanEncoder(GenericBeanSchema<T> beanSchema) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
    }

    @Override
    public void encode(T entity, ByteBuf buf) {
        try {
            List<GenericFieldSchema> fieldSchemaList = beanSchema.getFieldSchemaList();
            FieldInterceptContext fieldInterceptContext = new FieldInterceptContext();
            wrapSubjectRelated(fieldInterceptContext, beanSchema, entity, buf);
            for (GenericFieldSchema fieldSchema : fieldSchemaList) {
                Method getterMethod = fieldSchema.getGetterMethod();
                Object fieldValue = getterMethod.invoke(entity);
                AbstractCodec codec = fieldSchema.getCodec();

                wrapFieldRelated(fieldInterceptContext, fieldSchema, fieldValue);
                encodedField(codec, fieldValue, buf, fieldInterceptContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " an error occurred while encode the object : " + JSON.toJSONString(entity));
        }
    }

    // ------------------------------------------ assist methods ------------------------------------------

    /**
     * encodedField
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:08
     */
    private void encodedField(AbstractCodec codec, Object fieldValue, ByteBuf buf, FieldInterceptContext fieldInterceptContext) throws Exception {
        FieldInterceptor fieldInterceptor = fieldInterceptContext.getFieldSchema().getFieldInterceptor();
        if (fieldInterceptor != null) {
            fieldInterceptor.beforeEncode(fieldInterceptContext);
        }

        // if fieldValue is null, only fill padding bytes
        if (fieldValue == null) {
            byte[] paddingBytes = new byte[codec.length()];
            Arrays.fill(paddingBytes, CodecConstants.DEFAULT_PADDING_BYTE);
            buf.writeBytes(paddingBytes);
        } else {
            codec.encode(fieldValue, buf);
        }

        if (fieldInterceptor != null) {
            fieldInterceptor.afterEncode(fieldInterceptContext);
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
