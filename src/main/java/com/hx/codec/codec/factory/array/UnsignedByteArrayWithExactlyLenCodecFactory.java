package com.hx.codec.codec.factory.array;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.ByteArrayWithFixedLenCodec;
import com.hx.codec.codec.array.UnsignedByteArrayWithExactlyLenCodec;
import com.hx.codec.codec.array.UnsignedByteArrayWithFixedLenCodec;
import com.hx.codec.codec.array.UnsignedByteArrayWithLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;

import java.nio.ByteOrder;

/**
 * ByteArrayWithLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 16:33
 */
public class UnsignedByteArrayWithExactlyLenCodecFactory implements AbstractCodecFactory<Integer[], Integer[]> {

    @Override
    public AbstractCodec<Integer[], Integer[]> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int eleLength = fieldAnno.eleLength();
        return new UnsignedByteArrayWithExactlyLenCodec(byteOrder, eleLength);
    }
}