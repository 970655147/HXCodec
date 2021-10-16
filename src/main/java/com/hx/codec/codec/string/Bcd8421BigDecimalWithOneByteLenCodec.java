package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.decoder.string.Bcd8421BigDecimalWithOneByteLenDecoder;
import com.hx.codec.encoder.string.Bcd8421BigDecimalWithOneByteLenEncoder;
import io.netty.buffer.ByteBuf;

import java.math.BigDecimal;
import java.nio.ByteOrder;

/**
 * Bcd8421StringWithExactlyLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-16 19:27
 */
public class Bcd8421BigDecimalWithOneByteLenCodec extends AbstractCodec<BigDecimal, BigDecimal> {

    public Bcd8421BigDecimalWithOneByteLenCodec(ByteOrder byteOrder, byte paddingByte) {
        encoder = new Bcd8421BigDecimalWithOneByteLenEncoder(byteOrder, paddingByte);
        decoder = new Bcd8421BigDecimalWithOneByteLenDecoder(byteOrder, paddingByte);
    }

    public Bcd8421BigDecimalWithOneByteLenCodec(byte paddingByte) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, paddingByte);
    }

    @Override
    public void encode(BigDecimal entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public BigDecimal decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 32;
    }
}
