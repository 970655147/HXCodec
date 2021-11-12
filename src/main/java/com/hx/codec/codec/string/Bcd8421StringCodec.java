package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.string.Bcd8421StringDecoder;
import com.hx.codec.encoder.string.Bcd8421StringEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class Bcd8421StringCodec extends AbstractCodec<String, String> {

    public Bcd8421StringCodec(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst) {
        encoder = new Bcd8421StringEncoder(byteOrder, paddingByte, paddingFirst);
        decoder = new Bcd8421StringDecoder(byteOrder, paddingByte, paddingFirst);
    }

    public Bcd8421StringCodec(ByteOrder byteOrder, byte paddingByte) {
        this(byteOrder, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public Bcd8421StringCodec(byte paddingByte) {
        this(DEFAULT_BYTE_ORDER, paddingByte);
    }

    public Bcd8421StringCodec() {
        this(DEFAULT_BCD8421_PADDING);
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
        return false;
    }

    @Override
    public int length() {
        return 32;
    }
}
