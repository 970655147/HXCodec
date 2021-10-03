package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.GenericBeanCollectionWithLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.constants.ByteType;
import com.hx.codec.schema.GenericBeanSchema;

/**
 * GenericBeanArrayCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-03 12:06
 */
public class GenericBeanCollectionWithLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteType lengthByteType = fieldAnno.lengthByteType();
        java.lang.reflect.Field field = context.getField();
        Class eleType = field.getType().getComponentType();
        return new GenericBeanCollectionWithLenCodec<>(new GenericBeanSchema<>(eleType, context.getVersion()), lengthByteType);
    }
}
