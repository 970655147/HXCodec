package com.hx.codec.codec.factory.entity;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.schema.GenericBeanSchema;

import java.nio.ByteOrder;

/**
 * GenericBeanCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 15:10
 */
public class GenericBeanCodecFactory implements AbstractCodecFactory<Object, Object> {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        java.lang.reflect.Field field = context.getField();
        Class fieldClazz = field.getType();
        GenericBeanSchema beanSchema = new GenericBeanSchema<>(fieldClazz, context.getVersion());
        return new GenericBeanCodec<>(beanSchema);
    }

}
