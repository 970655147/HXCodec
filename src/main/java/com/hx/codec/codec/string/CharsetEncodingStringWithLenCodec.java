package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.string.CharsetEncodingStringWithLenDecoder;
import com.hx.codec.encoder.string.CharsetEncodingStringWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_CHARSET;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class CharsetEncodingStringWithLenCodec extends AbstractCodec<String, String> {

    public CharsetEncodingStringWithLenCodec(ByteOrder byteOrder, ByteType lenByteType, Charset charset) {
        encoder = new CharsetEncodingStringWithLenEncoder(byteOrder, charset, lenByteType);
        decoder = new CharsetEncodingStringWithLenDecoder(byteOrder, charset, lenByteType);
    }

    public CharsetEncodingStringWithLenCodec(ByteType lenByteType, Charset charset) {
        this(DEFAULT_BYTE_ORDER, lenByteType, charset);
    }

    public CharsetEncodingStringWithLenCodec(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType, DEFAULT_CHARSET);
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
