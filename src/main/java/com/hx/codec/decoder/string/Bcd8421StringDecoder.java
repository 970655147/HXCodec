package com.hx.codec.decoder.string;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_FIRST;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringDecoder extends AbstractDecoder<String> {

    // paddingByte
    private byte paddingByte;
    // paddingFirst
    private boolean paddingFirst;

    public Bcd8421StringDecoder(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst) {
        super(byteOrder);
        AssertUtils.state(paddingByte <= 0xf, " unexpected padding byte ");
        this.paddingByte = paddingByte;
        this.paddingFirst = paddingFirst;
    }

    public Bcd8421StringDecoder(ByteOrder byteOrder, byte paddingByte) {
        this(byteOrder, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public Bcd8421StringDecoder(byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, paddingByte);
    }

    @Override
    public String decode(ByteBuf buf) {
        byte[] entityBytes = new byte[buf.readableBytes()];
        buf.readBytes(entityBytes);

        byte[] decodedBytes = new byte[(entityBytes.length) << 1];
        int decodeIdx = 0;
        for (int i = 0; i < entityBytes.length; i++) {
            byte higherPart = (byte) (((entityBytes[i] >> 4) & 0xf) + '0');
            byte lowerPart = (byte) ((entityBytes[i] & 0xf) + '0');
            decodedBytes[decodeIdx++] = higherPart;
            decodedBytes[decodeIdx++] = lowerPart;
        }

        byte paddingByteAfterPlusZero = (byte) ('0' + paddingByte);
        int startIdx = 0, endIdx = decodedBytes.length - 1;
        for (startIdx = 0; decodedBytes[startIdx] == paddingByteAfterPlusZero && startIdx < endIdx; startIdx++) ;
        for (endIdx = decodedBytes.length - 1; endIdx >= startIdx && decodedBytes[endIdx] == paddingByteAfterPlusZero; endIdx--)
            ;

        return new String(decodedBytes, startIdx, (endIdx - startIdx + 1));
    }

}
