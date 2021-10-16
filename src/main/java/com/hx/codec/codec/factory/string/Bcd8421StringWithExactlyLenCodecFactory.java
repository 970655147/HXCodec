package com.hx.codec.codec.factory.string;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.string.Bcd8421StringWithExactlyLenCodec;

import java.nio.ByteOrder;

/**
 * Bcd8421StringWithFixedLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:57
 */
public class Bcd8421StringWithExactlyLenCodecFactory implements AbstractCodecFactory<String, String> {

    @Override
    public AbstractCodec<String, String> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int eleLength = fieldAnno.eleLength();
        return new Bcd8421StringWithExactlyLenCodec(byteOrder, eleLength);
    }

}
