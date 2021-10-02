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
public class Bcd8421StringEncoder extends AbstractEncoder<String> {

    // length
    private int fixedLength;

    public Bcd8421StringEncoder(ByteOrder byteOrder, int fixedLength) {
        super(byteOrder);
        this.fixedLength = fixedLength;
    }

    public Bcd8421StringEncoder(int fixedLength) {
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
                    | (DEFAULT_BCD8421_PADDING & 0xf));
        }

        // cache for this
        byte[] paddingBytes = new byte[fixedLength - entityBytes.length];
        Arrays.fill(paddingBytes, DEFAULT_BCD8421_PADDING);

        buf.writeBytes(entityBytes);
        buf.writeBytes(paddingBytes);
    }

}
