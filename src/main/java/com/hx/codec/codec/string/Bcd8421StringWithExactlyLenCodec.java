package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.decoder.string.Bcd8421StringWithExactlyLenDecoder;
import com.hx.codec.encoder.string.Bcd8421StringWithExactlyLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * Bcd8421StringWithExactlyLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-16 19:27
 */
public class Bcd8421StringWithExactlyLenCodec extends AbstractCodec<String, String> {

    private int eleLength;

    public Bcd8421StringWithExactlyLenCodec(ByteOrder byteOrder, int eleLength) {
        this.eleLength = eleLength;
        encoder = new Bcd8421StringWithExactlyLenEncoder(byteOrder, eleLength);
        decoder = new Bcd8421StringWithExactlyLenDecoder(byteOrder, eleLength);
    }

    public Bcd8421StringWithExactlyLenCodec(int eleLength) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, eleLength);
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
        return eleLength;
    }
}
