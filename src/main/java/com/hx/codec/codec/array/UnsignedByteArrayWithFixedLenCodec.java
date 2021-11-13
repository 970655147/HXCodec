package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * UnsignedByteArrayWithFixedLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedByteArrayWithFixedLenCodec extends DelegateCodec<Integer[], Integer[]> {

    public UnsignedByteArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new UnsignedByteArrayCodec(byteOrder), paddingByte, paddingFirst, fixedLength));
    }

    public UnsignedByteArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public UnsignedByteArrayWithFixedLenCodec(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public UnsignedByteArrayWithFixedLenCodec(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }

}
