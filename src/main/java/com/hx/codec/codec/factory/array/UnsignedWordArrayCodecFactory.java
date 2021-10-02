package com.hx.codec.codec.factory.array;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.UnsignedWordArrayCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;

import java.nio.ByteOrder;

/**
 * WordArrayCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 16:33
 */
public class UnsignedWordArrayCodecFactory implements AbstractCodecFactory<Integer[], Integer[]> {

    @Override
    public AbstractCodec<Integer[], Integer[]> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        return new UnsignedWordArrayCodec(byteOrder);
    }
}
