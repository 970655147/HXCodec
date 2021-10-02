package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.decoder.string.CharsetEncodingStringDecoder;
import com.hx.codec.encoder.string.CharsetEncodingStringEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class CharsetEncodingStringCodec extends AbstractCodec<String, String> {

    public CharsetEncodingStringCodec(ByteOrder byteOrder, Charset charset) {
        encoder = new CharsetEncodingStringEncoder(byteOrder, charset);
        decoder = new CharsetEncodingStringDecoder(byteOrder, charset);
    }

    public CharsetEncodingStringCodec(Charset charset) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, charset);
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
