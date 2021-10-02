package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.decoder.string.Bcd8421StringDecoder;
import com.hx.codec.encoder.string.Bcd8421StringEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class Bcd8421StringCodec extends AbstractCodec<String, String> {

    private int fixedLength;

    public Bcd8421StringCodec(ByteOrder byteOrder, int fixedLength) {
        this.fixedLength = fixedLength;
        encoder = new Bcd8421StringEncoder(byteOrder, fixedLength);
        decoder = new Bcd8421StringDecoder(byteOrder, fixedLength);
    }

    public Bcd8421StringCodec(int fixedLength) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, fixedLength);
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
