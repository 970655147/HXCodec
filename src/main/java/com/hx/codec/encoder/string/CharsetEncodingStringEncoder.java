package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 15:47
 */
public class CharsetEncodingStringEncoder extends AbstractEncoder<String> {

    // charset
    private Charset charset;

    public CharsetEncodingStringEncoder(ByteOrder byteOrder, Charset charset) {
        super(byteOrder);
        this.charset = charset;
    }

    public CharsetEncodingStringEncoder(Charset charset) {
        this(DEFAULT_BYTE_ORDER, charset);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] entityBytes = entity.getBytes(charset);
        buf.writeBytes(entityBytes);
    }

}
