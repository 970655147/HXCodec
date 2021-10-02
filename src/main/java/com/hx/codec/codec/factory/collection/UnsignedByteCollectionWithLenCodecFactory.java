package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.UnsignedByteCollectionWithLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.constants.ByteType;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * ByteCollectionCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:18
 */
public class UnsignedByteCollectionWithLenCodecFactory implements AbstractCodecFactory<Collection<Integer>, Collection<Integer>> {

    @Override
    public AbstractCodec<Collection<Integer>, Collection<Integer>> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        ByteType lengthByteType = fieldAnno.lengthByteType();
        return new UnsignedByteCollectionWithLenCodec(byteOrder, lengthByteType);
    }

}
