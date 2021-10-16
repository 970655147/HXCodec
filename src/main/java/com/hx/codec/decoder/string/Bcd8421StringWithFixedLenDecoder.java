package com.hx.codec.decoder.string;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithFixedLenDecoder extends AbstractDecoder<String> {

    // length
    private int fixedLength;
    // paddingByte
    private byte paddingByte;

    public Bcd8421StringWithFixedLenDecoder(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        super(byteOrder);
        this.fixedLength = fixedLength;
        this.paddingByte = paddingByte;
    }

    public Bcd8421StringWithFixedLenDecoder(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_BCD8421_PADDING);
    }

    public Bcd8421StringWithFixedLenDecoder(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }

    @Override
    public String decode(ByteBuf buf) {
        byte[] entityBytes = new byte[fixedLength];
        buf.readBytes(entityBytes);

        int startIdx = 0, endIdx = entityBytes.length - 1;
        for (startIdx = 0; entityBytes[startIdx] == paddingByte && startIdx < endIdx; startIdx++) ;
        for (endIdx = entityBytes.length - 1; endIdx >= startIdx && entityBytes[endIdx] == paddingByte; endIdx--) ;

        byte[] decodedBytes = new byte[fixedLength << 1];
        int decodeIdx = 0;
        for (int i = startIdx; i <= endIdx; i++) {
            byte higherPart = (byte) (((entityBytes[i] >> 4) & 0xf) + '0');
            byte lowerPart = (byte) ((entityBytes[i] & 0xf) + '0');
            decodedBytes[decodeIdx++] = higherPart;
            decodedBytes[decodeIdx++] = lowerPart;
            if ((lowerPart - '0') == paddingByte) {
                decodeIdx--;
            }
        }

        return new String(decodedBytes, 0, decodeIdx);
    }

}
