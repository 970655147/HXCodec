package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.math.BigDecimal;
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
public class Bcd8421BigDecimalWithOneByteLenEncoder extends AbstractEncoder<BigDecimal> {

    // 如果是正数, 使用 0, 如果是负数使用 0xff;
    public static Integer NEGATIVE_PADDING_BYTE = 0xff;

    // paddingByte
    private byte paddingByte;

    public Bcd8421BigDecimalWithOneByteLenEncoder(ByteOrder byteOrder, byte paddingByte) {
        super(byteOrder);
        this.paddingByte = paddingByte;
    }

    public Bcd8421BigDecimalWithOneByteLenEncoder(byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, paddingByte);
    }

    @Override
    public void encode(BigDecimal entity, ByteBuf buf) {
        String entityString = entity.toString().replace("-", "");
        String toTransferString = entityString.replace(".", "");

        // write toTransferCount && afterDotCount
        byte[] randomEntityBytes = toTransferString.getBytes(Charset.defaultCharset());
        int toTransferCount = (randomEntityBytes.length + 1) >> 1;
        int afterDotCount = 0;
        if (entityString.contains(".")) {
            afterDotCount = entityString.length() - entityString.indexOf(".") - 1;
        }
        int lenDesc = ((toTransferCount & 0b00011111) << 3) | (afterDotCount & 0b00000111);
        buf.writeByte(lenDesc);

        // write signPaddingByte
        Integer signPaddingByte = 0x00;
        if (entity.compareTo(BigDecimal.ZERO) < 0) {
            signPaddingByte = NEGATIVE_PADDING_BYTE;
        }
        buf.writeByte(signPaddingByte);

        // write from lastChar, if randomEntityBytes.length is odd, fill paddingByte before
        for (byte b : randomEntityBytes) {
            AssertUtils.state(b >= '0' && b <= '9', "unexpected string encoding by BCD8421");
        }
        byte[] entityBytes = new byte[((randomEntityBytes.length + 1) >> 1)];
        boolean isOdd = (randomEntityBytes.length & 0x1) != 0;
        int fillBytesLen = isOdd ? entityBytes.length - 1 : entityBytes.length;
        int entityByteIdx = entityBytes.length - 1, randomEntityByteIdx = randomEntityBytes.length - 1;
        for (int i = 0; i < fillBytesLen; i++) {
            entityBytes[entityByteIdx--] = (byte) ((randomEntityBytes[randomEntityByteIdx--] - '0') | ((randomEntityBytes[randomEntityByteIdx--] - '0') & 0xf) << 4);
        }
        if (isOdd) {
            entityBytes[0] = (byte) (((paddingByte & 0x0f) << 4) | ((randomEntityBytes[0] - '0')));
        }

        buf.writeBytes(entityBytes);
    }

}
