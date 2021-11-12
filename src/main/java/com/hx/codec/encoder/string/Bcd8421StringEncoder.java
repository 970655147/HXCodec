package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_FIRST;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringEncoder extends AbstractEncoder<String> {

    // paddingByte
    private byte paddingByte;
    // paddingFirst
    private boolean paddingFirst;

    public Bcd8421StringEncoder(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst) {
        super(byteOrder);
        AssertUtils.state(paddingByte <= 0xf, " unexpected padding byte ");
        this.paddingByte = paddingByte;
        this.paddingFirst = paddingFirst;
    }

    public Bcd8421StringEncoder(ByteOrder byteOrder, byte paddingByte) {
        this(byteOrder, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public Bcd8421StringEncoder(byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, paddingByte);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] randomEntityBytes = entity.getBytes(Charset.defaultCharset());
        for (byte b : randomEntityBytes) {
            AssertUtils.state(b >= '0' && b <= '9', "unexpected string encoding by BCD8421");
        }
        byte doublePaddingByte = (byte) ((paddingByte << 4) | paddingByte);

        byte[] entityBytes = new byte[((randomEntityBytes.length + 1) >> 1)];
        boolean isOdd = (randomEntityBytes.length & 0x1) != 0;
        int loopCount = isOdd ? entityBytes.length - 1 : entityBytes.length;
        if (!paddingFirst) {
            int startIdx = 0;
            for (int i = 0; i < loopCount; i++) {
                entityBytes[i] = (byte) (((randomEntityBytes[startIdx++] - '0') << 4) | ((randomEntityBytes[startIdx++] - '0') & 0xf));
            }
            if (isOdd) {
                int encodedLastByteIdx = entityBytes.length - 1;
                entityBytes[entityBytes.length - 1] = (byte) (((randomEntityBytes[encodedLastByteIdx << 1] - '0') << 4)
                        | (doublePaddingByte & 0xf));
            }
        } else {
            int entityBytesOffset = isOdd ? 1 : 0;
            int startIdx = isOdd ? 1 : 0;
            for (int i = 0; i < loopCount; i++) {
                entityBytes[i + entityBytesOffset] = (byte) (((randomEntityBytes[startIdx++] - '0') << 4) | ((randomEntityBytes[startIdx++] - '0') & 0xf));
            }
            if (isOdd) {
                entityBytes[0] = (byte) (((doublePaddingByte << 4) & 0xf0) | ((randomEntityBytes[0] - '0') & 0x0f));
            }
        }

        buf.writeBytes(entityBytes);
    }

}
