package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithExactlyLenEncoder extends AbstractEncoder<String> {

    // length
    private int eleLength;

    public Bcd8421StringWithExactlyLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.eleLength = eleLength;
    }

    public Bcd8421StringWithExactlyLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] randomEntityBytes = entity.getBytes(Charset.defaultCharset());
        AssertUtils.state((randomEntityBytes.length) == eleLength * 2, "unexpected string length");
        for (byte b : randomEntityBytes) {
            AssertUtils.state(b >= '0' && b <= '9', "unexpected string encoding by BCD8421");
        }

        byte[] entityBytes = new byte[((randomEntityBytes.length + 1) >> 1)];
        int fillBytesLen = entityBytes.length;
        for (int i = 0; i < fillBytesLen; i++) {
            entityBytes[i] = (byte) (((randomEntityBytes[i << 1] - '0') << 4) | ((randomEntityBytes[(i << 1) + 1] - '0') & 0xf));
        }

        buf.writeBytes(entityBytes);
    }

}
