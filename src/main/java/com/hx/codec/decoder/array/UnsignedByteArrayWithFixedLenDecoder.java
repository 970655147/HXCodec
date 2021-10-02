package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.UnsignedByteDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class UnsignedByteArrayWithFixedLenDecoder extends AbstractDecoder<Integer[]> {

    private UnsignedByteDecoder decoder;
    private int eleLength;

    public UnsignedByteArrayWithFixedLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new UnsignedByteDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public UnsignedByteArrayWithFixedLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        Integer[] tmpResult = new Integer[eleLength];
        int length = 0;
        for (int i = 0; i < eleLength; i++) {
            byte nextByte = buf.getByte(buf.readerIndex());
            Integer ele = decoder.decode(buf);
            if (nextByte != DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING) {
                length = i + 1;
                tmpResult[i] = ele;
            }
        }

        Integer[] result = new Integer[length];
        for (int i = 0; i < length; i++) {
            result[i] = tmpResult[i];
        }
        return result;
    }
}
