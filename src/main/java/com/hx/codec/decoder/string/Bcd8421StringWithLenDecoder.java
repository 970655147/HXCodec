package com.hx.codec.decoder.string;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * Bcd8421StringWithLenDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithLenDecoder extends AbstractDecoder<String> {

    // lenByteType
    private ByteType lenByteType;
    // paddingByte
    private byte paddingByte;

    public Bcd8421StringWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType, byte paddingByte) {
        super(byteOrder);
        this.lenByteType = lenByteType;
        this.paddingByte = paddingByte;
    }

    public Bcd8421StringWithLenDecoder(ByteType lenByteType, byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, lenByteType, paddingByte);
    }

    public Bcd8421StringWithLenDecoder(ByteType lenByteType) {
        this(lenByteType, DEFAULT_BCD8421_PADDING);
    }

    @Override
    public String decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        byte[] entityBytes = new byte[(int) len];
        buf.readBytes(entityBytes);

        int startIdx = 0, endIdx = entityBytes.length - 1;
        byte[] decodedBytes = new byte[(int) (len << 1)];
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
