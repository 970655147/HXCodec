package com.hx.codec.decoder.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.UnsignedByteCodec;
import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.math.BigDecimal;
import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class Bcd8421BigDecimalWithOneByteLenDecoder extends AbstractDecoder<BigDecimal> {

    // 如果是正数, 使用 0, 如果是负数使用 0xff;
    public static Integer NEGATIVE_PADDING_BYTE = 0xff;

    // paddingByte
    private byte paddingByte;

    public Bcd8421BigDecimalWithOneByteLenDecoder(ByteOrder byteOrder, byte paddingByte) {
        super(byteOrder);
        this.paddingByte = paddingByte;
    }

    public Bcd8421BigDecimalWithOneByteLenDecoder(byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, paddingByte);
    }

    @Override
    public BigDecimal decode(ByteBuf buf) {
        // read toTransferCount & afterDotCount
        AbstractCodec<Integer, Integer> unsignedByteCodec = new UnsignedByteCodec();
        int toTransferCountAndAfterDotCount = unsignedByteCodec.decode(buf);
        int toTransferCount = ((toTransferCountAndAfterDotCount & 0b11111000) >> 3);
        int afterDotCount = (toTransferCountAndAfterDotCount & 0b00000111);

        // read signPaddingByte
        int signPaddingByte = unsignedByteCodec.decode(buf);
        boolean isNegative = signPaddingByte == NEGATIVE_PADDING_BYTE;

        // read entityBytes
        byte[] entityBytes = new byte[toTransferCount];
        buf.readBytes(entityBytes);
        int startIdx = 0, endIdx = entityBytes.length - 1;
        byte[] decodedBytes = new byte[(int) (toTransferCount << 1)];
        int decodeIdx = 0;
        for (int i = startIdx; i <= endIdx; i++) {
            byte higherPart = (byte) (((entityBytes[i] >> 4) & 0xf) + '0');
            byte lowerPart = (byte) ((entityBytes[i] & 0xf) + '0');
            decodedBytes[decodeIdx++] = higherPart;
            decodedBytes[decodeIdx++] = lowerPart;
            if ((higherPart - '0') == paddingByte && startIdx == 0) {
                startIdx = 1;
            }
        }

        // wrap result
        int toDecodedStringLen = decodedBytes.length - startIdx;
        String toDecodedString = new String(decodedBytes, startIdx, toDecodedStringLen);
        StringBuilder resultString = new StringBuilder();
        if (isNegative) {
            resultString.append("-");
        }
        if (afterDotCount <= 0) {
            resultString.append(toDecodedString);
            return new BigDecimal(resultString.toString());
        }

        int dotIdx = toDecodedString.length() - afterDotCount;
        resultString.append(toDecodedString.substring(0, dotIdx));
        resultString.append(".");
        resultString.append(toDecodedString.substring(dotIdx));
        return new BigDecimal(resultString.toString());
    }

}
