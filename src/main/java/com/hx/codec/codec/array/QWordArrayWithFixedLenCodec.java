package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * QWordArrayWithFixedLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordArrayWithFixedLenCodec extends DelegateCodec<Long[], Long[]> {

    public QWordArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new QWordArrayCodec(byteOrder), paddingByte, paddingFirst, QWORD_UNIT, fixedLength));
    }

    public QWordArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public QWordArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public QWordArrayWithFixedLenCodec(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }

}
