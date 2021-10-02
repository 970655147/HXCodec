package com.hx.codec.codec.factory.common;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.WordCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;

import java.nio.ByteOrder;

/**
 * WordCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 10:31
 */
public class WordCodecFactory implements AbstractCodecFactory<Integer, Integer> {

    @Override
    public AbstractCodec<Integer, Integer> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        return new WordCodec(byteOrder);
    }

}
