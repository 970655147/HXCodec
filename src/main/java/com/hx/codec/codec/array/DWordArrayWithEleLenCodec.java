package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * DWordArrayWithEleLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class DWordArrayWithEleLenCodec extends DelegateCodec<Integer[], Integer[]> {

    public DWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new DWordArrayCodec(byteOrder), paddingByte, paddingFirst, DWORD_UNIT, eleLength * DWORD_UNIT));
    }

    public DWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public DWordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength) {
        this(byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public DWordArrayWithEleLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

}
