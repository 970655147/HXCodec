package com.hx.codec.decoder.entity;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.GenericFieldSchema;
import com.hx.codec.utils.AssertUtils;
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
        ByteBuf dupBuf = buf.copy();
        try {
            Class<T> clazz = beanSchema.getClazz();
            Object result = clazz.newInstance();
            List<GenericFieldSchema> fieldSchemaList = beanSchema.getFieldSchemaList();
            for (GenericFieldSchema fieldSchema : fieldSchemaList) {
                AbstractCodec codec = fieldSchema.getCodec();
                Object fieldValue = codec.decode(buf);
                Method setterMethod = fieldSchema.getSetterMethod();
                setterMethod.invoke(result, fieldValue);
            }
            return (T) result;
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " an error occurred while decode the buf : " + ByteBufUtil.hexDump(dupBuf));
            return null;
        }
    }

}
