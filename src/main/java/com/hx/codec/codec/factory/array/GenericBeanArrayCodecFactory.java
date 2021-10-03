package com.hx.codec.codec.factory.array;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.GenericBeanArrayCodec;
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
public class GenericBeanArrayCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        java.lang.reflect.Field field = context.getField();
        Class eleType = field.getType().getComponentType();
        return new GenericBeanArrayCodec<>(new GenericBeanSchema<>(eleType, context.getVersion()));
    }
}
