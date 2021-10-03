package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.DWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class DWordArrayWithExactlyLenDecoder extends AbstractDecoder<Integer[]> {

    private DWordDecoder decoder;
    private int eleLength;

    public DWordArrayWithExactlyLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new DWordDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public DWordArrayWithExactlyLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        Integer[] result = new Integer[eleLength];
        for (int i = 0; i < eleLength; i++) {
            Integer ele = decoder.decode(buf);
            result[i] = ele;
        }

        return result;
    }
}
