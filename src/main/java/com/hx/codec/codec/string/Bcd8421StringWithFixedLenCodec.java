package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.string.Bcd8421StringWithFixedLenDecoder;
import com.hx.codec.encoder.string.Bcd8421StringWithFixedLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class Bcd8421StringWithFixedLenCodec extends AbstractCodec<String, String> {

    private int fixedLength;

    public Bcd8421StringWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this.fixedLength = fixedLength;
        encoder = new Bcd8421StringWithFixedLenEncoder(byteOrder, fixedLength, paddingByte);
        decoder = new Bcd8421StringWithFixedLenDecoder(byteOrder, fixedLength, paddingByte);
    }

    public Bcd8421StringWithFixedLenCodec(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_BCD8421_PADDING);
    }

    public Bcd8421StringWithFixedLenCodec(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public String decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return fixedLength;
    }
}
