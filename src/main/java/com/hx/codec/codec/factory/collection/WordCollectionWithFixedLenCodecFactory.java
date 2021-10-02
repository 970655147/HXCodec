package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.WordCollectionWithFixedLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * WordCollectionCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:18
 */
public class WordCollectionWithFixedLenCodecFactory implements AbstractCodecFactory<Collection<Integer>, Collection<Integer>> {

    @Override
    public AbstractCodec<Collection<Integer>, Collection<Integer>> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int eleLength = fieldAnno.eleLength();
        return new WordCollectionWithFixedLenCodec(byteOrder, eleLength);
    }

}
