package com.hx.codec.decoder.string;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 15:45
 */
public class CharsetEncodingStringDecoder extends AbstractDecoder<String> {

    // charset
    private Charset charset;

    public CharsetEncodingStringDecoder(ByteOrder byteOrder, Charset charset) {
        super(byteOrder);
        this.charset = charset;
    }

    public CharsetEncodingStringDecoder(Charset charset) {
        this(DEFAULT_BYTE_ORDER, charset);
    }

    @Override
    public String decode(ByteBuf buf) {
        byte[] entityBytes = new byte[buf.readableBytes()];
        buf.readBytes(entityBytes);
        return new String(entityBytes, charset);
    }

}
