package com.hx.codec.decoder.string;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_CHARSET;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class CharsetEncodingStringWithLenDecoder extends AbstractDecoder<String> {

    // charset
    private Charset charset;
    private ByteType lenByteType;

    public CharsetEncodingStringWithLenDecoder(ByteOrder byteOrder, Charset charset, ByteType lenByteType) {
        super(byteOrder);
        this.charset = charset;
        this.lenByteType = lenByteType;
    }

    public CharsetEncodingStringWithLenDecoder(Charset charset, ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, charset, lenByteType);
    }

    public CharsetEncodingStringWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, DEFAULT_CHARSET, lenByteType);
    }

    @Override
    public String decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        byte[] entityBytes = new byte[(int) len];
        buf.readBytes(entityBytes);
        return new String(entityBytes, charset);
    }
}
