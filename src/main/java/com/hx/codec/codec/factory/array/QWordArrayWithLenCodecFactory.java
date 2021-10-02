package com.hx.codec.codec.factory.array;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.QWordArrayWithLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.constants.ByteType;

import java.nio.ByteOrder;

/**
 * QWordArrayWithLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 16:33
 */
public class QWordArrayWithLenCodecFactory implements AbstractCodecFactory<Long[], Long[]> {

    @Override
    public AbstractCodec<Long[], Long[]> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        ByteType lengthByteType = fieldAnno.lengthByteType();
        return new QWordArrayWithLenCodec(byteOrder, lengthByteType);
    }
}
