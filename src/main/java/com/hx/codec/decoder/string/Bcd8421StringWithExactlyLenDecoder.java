package com.hx.codec.decoder.string;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithExactlyLenDecoder extends AbstractDecoder<String> {

    // length
    private int eleLength;

    public Bcd8421StringWithExactlyLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.eleLength = eleLength;
    }

    public Bcd8421StringWithExactlyLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public String decode(ByteBuf buf) {
        byte[] entityBytes = new byte[eleLength];
        buf.readBytes(entityBytes);

        int startIdx = 0, endIdx = entityBytes.length - 1;
        byte[] decodedBytes = new byte[eleLength << 1];
        int decodeIdx = 0;
        for (int i = startIdx; i <= endIdx; i++) {
            byte higherPart = (byte) (((entityBytes[i] >> 4) & 0xf) + '0');
            byte lowerPart = (byte) ((entityBytes[i] & 0xf) + '0');
            decodedBytes[decodeIdx++] = higherPart;
            decodedBytes[decodeIdx++] = lowerPart;
        }

        return new String(decodedBytes, 0, decodeIdx);
    }

}
