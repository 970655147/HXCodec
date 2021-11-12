package com.hx.codec.codec.string;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * Bcd8421StringWithFixedLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class Bcd8421StringWithFixedLenCodec extends DelegateCodec<String, String> {

    public Bcd8421StringWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new Bcd8421StringCodec(byteOrder, paddingByte, paddingFirst),
                ((byte) ((paddingByte << 4) | paddingByte)),
                paddingFirst, fixedLength));
    }

    public Bcd8421StringWithFixedLenCodec(ByteOrder byteOrder, int lengthInBytes, byte paddingByte) {
        this(byteOrder, lengthInBytes, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public Bcd8421StringWithFixedLenCodec(ByteOrder byteOrder, int lengthInBytes) {
        this(byteOrder, lengthInBytes, DEFAULT_BCD8421_PADDING);
    }

    public Bcd8421StringWithFixedLenCodec(int lengthInBytes) {
        this(DEFAULT_BYTE_ORDER, lengthInBytes);
    }

}
