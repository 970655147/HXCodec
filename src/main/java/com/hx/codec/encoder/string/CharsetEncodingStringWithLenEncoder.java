package com.hx.codec.encoder.string;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import com.hx.codec.utils.AssertUtils;
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
public class CharsetEncodingStringWithLenEncoder extends AbstractEncoder<String> {

    // charset
    private Charset charset;
    private ByteType lenByteType;

    public CharsetEncodingStringWithLenEncoder(ByteOrder byteOrder, Charset charset, ByteType lenByteType) {
        super(byteOrder);
        this.charset = charset;
        this.lenByteType = lenByteType;
    }

    public CharsetEncodingStringWithLenEncoder(Charset charset, ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, charset, lenByteType);
    }

    public CharsetEncodingStringWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, DEFAULT_CHARSET, lenByteType);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] entityBytes = entity.getBytes(charset);
        AssertUtils.state(entityBytes.length <= CodecUtils.maxLenCouldHold(lenByteType), "unexpected string length");

        ByteBufUtils.writeLen(lenByteType, byteOrder, entityBytes.length, buf);
        buf.writeBytes(entityBytes);
    }

}
