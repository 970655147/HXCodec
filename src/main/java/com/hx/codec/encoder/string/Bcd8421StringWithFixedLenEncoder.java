package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithFixedLenEncoder extends AbstractEncoder<String> {

    // length
    private int fixedLength;
    // paddingByte
    private byte paddingByte;

    public Bcd8421StringWithFixedLenEncoder(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        super(byteOrder);
        this.fixedLength = fixedLength;
        this.paddingByte = paddingByte;
    }

    public Bcd8421StringWithFixedLenEncoder(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_BCD8421_PADDING);
    }

    public Bcd8421StringWithFixedLenEncoder(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] randomEntityBytes = entity.getBytes(Charset.defaultCharset());
        AssertUtils.state(((randomEntityBytes.length + 1) >> 1) <= fixedLength, "unexpected string length");
        for (byte b : randomEntityBytes) {
            AssertUtils.state(b >= '0' && b <= '9', "unexpected string encoding by BCD8421");
        }

        byte[] entityBytes = new byte[((randomEntityBytes.length + 1) >> 1)];
        boolean isOdd = (randomEntityBytes.length & 0x1) != 0;
        int fillBytesLen = isOdd ? entityBytes.length - 1 : entityBytes.length;
        for (int i = 0; i < fillBytesLen; i++) {
            entityBytes[i] = (byte) (((randomEntityBytes[i << 1] - '0') << 4) | ((randomEntityBytes[(i << 1) + 1] - '0') & 0xf));
        }
        if (isOdd) {
            int encodedLastByteIdx = entityBytes.length - 1;
            entityBytes[entityBytes.length - 1] = (byte) (((randomEntityBytes[encodedLastByteIdx << 1] - '0') << 4)
                    | (paddingByte & 0xf));
        }

        // cache for this
        byte[] paddingBytes = new byte[fixedLength - entityBytes.length];
        Arrays.fill(paddingBytes, paddingByte);

        buf.writeBytes(entityBytes);
        buf.writeBytes(paddingBytes);
    }

}
