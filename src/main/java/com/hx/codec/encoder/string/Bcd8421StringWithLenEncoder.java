package com.hx.codec.encoder.string;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421StringWithLenEncoder extends AbstractEncoder<String> {

    // lenByteType
    private ByteType lenByteType;
    // paddingByte
    private byte paddingByte;

    public Bcd8421StringWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType, byte paddingByte) {
        super(byteOrder);
        this.lenByteType = lenByteType;
        this.paddingByte = paddingByte;
    }

    public Bcd8421StringWithLenEncoder(ByteType lenByteType, byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, lenByteType, paddingByte);
    }

    public Bcd8421StringWithLenEncoder(ByteType lenByteType) {
        this(lenByteType, DEFAULT_BCD8421_PADDING);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] randomEntityBytes = entity.getBytes(Charset.defaultCharset());
        ByteBufUtils.writeLen(lenByteType, byteOrder, ((randomEntityBytes.length + 1) >> 1), buf);
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

        buf.writeBytes(entityBytes);
    }

}
