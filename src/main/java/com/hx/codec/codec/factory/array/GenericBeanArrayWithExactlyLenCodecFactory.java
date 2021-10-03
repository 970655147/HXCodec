package com.hx.codec.codec.factory.array;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.GenericBeanArrayWithExactlyLenCodec;
import com.hx.codec.codec.array.GenericBeanArrayWithFixedLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.schema.GenericBeanSchema;

/**
 * GenericBeanArrayCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-03 12:06
 */
public class GenericBeanArrayWithExactlyLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        int eleLength = fieldAnno.eleLength();
        java.lang.reflect.Field field = context.getField();
        Class eleType = field.getType().getComponentType();
        return new GenericBeanArrayWithExactlyLenCodec<>(new GenericBeanSchema<>(eleType, context.getVersion()), eleLength);
    }
}
