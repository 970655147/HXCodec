package com.hx.codec.encoder.entity;

import com.alibaba.fastjson.JSON;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.encoder.AbstractEncoder;
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
            for (GenericFieldSchema fieldSchema : fieldSchemaList) {
                Method getterMethod = fieldSchema.getGetterMethod();
                Object fieldValue = getterMethod.invoke(entity);
                AbstractCodec codec = fieldSchema.getCodec();
                // if fieldValue is null, only fill padding bytes
                if (fieldValue == null) {
                    byte[] paddingBytes = new byte[codec.length()];
                    Arrays.fill(paddingBytes, CodecConstants.DEFAULT_PADDING_BYTE);
                    buf.writeBytes(paddingBytes);
                    continue;
                }

                codec.encode(fieldValue, buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " an error occurred while encode the object : " + JSON.toJSONString(entity));
        }
    }

}
