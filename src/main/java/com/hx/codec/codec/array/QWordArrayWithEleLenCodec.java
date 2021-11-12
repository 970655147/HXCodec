package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * WordProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordArrayWithEleLenCodec extends DelegateCodec<Long[], Long[]> {

    public QWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new QWordArrayCodec(byteOrder), paddingByte, paddingFirst, QWORD_UNIT, eleLength * QWORD_UNIT));
    }

    public QWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public QWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength) {
        this(byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public QWordArrayWithEleLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

}
