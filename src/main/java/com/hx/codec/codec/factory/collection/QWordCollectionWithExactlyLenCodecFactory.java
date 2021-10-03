package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.QWordCollectionWithExactlyLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * QWordCollectionCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:18
 */
public class QWordCollectionWithExactlyLenCodecFactory implements AbstractCodecFactory<Collection<Long>, Collection<Long>> {

    @Override
    public AbstractCodec<Collection<Long>, Collection<Long>> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int eleLength = fieldAnno.eleLength();
        return new QWordCollectionWithExactlyLenCodec(byteOrder, eleLength);
    }

}
