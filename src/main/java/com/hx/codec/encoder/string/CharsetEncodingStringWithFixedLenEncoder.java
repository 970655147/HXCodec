package com.hx.codec.encoder.string;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class CharsetEncodingStringWithFixedLenEncoder extends AbstractEncoder<String> {

    // padding related
    private byte paddingByte;
    private boolean paddingFirst;
    // length
    private int fixedLength;
    // charset
    private Charset charset;

    public CharsetEncodingStringWithFixedLenEncoder(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        super(byteOrder);
        this.paddingByte = paddingByte;
        this.paddingFirst = paddingFirst;
        this.fixedLength = fixedLength;
        this.charset = charset;
    }

    public CharsetEncodingStringWithFixedLenEncoder(byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        this(DEFAULT_BYTE_ORDER, paddingByte, paddingFirst, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenEncoder(byte paddingByte, int fixedLength, Charset charset) {
        this(DEFAULT_BYTE_ORDER, paddingByte, DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenEncoder(int fixedLength, Charset charset) {
        this(DEFAULT_PADDING_BYTE, DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenEncoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTE, DEFAULT_PADDING_FIRST, fixedLength, DEFAULT_CHARSET);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        byte[] entityBytes = entity.getBytes(charset);
        AssertUtils.state(entityBytes.length <= fixedLength, "unexpected string length");
        int paddingLength = fixedLength - entityBytes.length;
        // cache for this
        byte[] paddingBytes = new byte[paddingLength];
        Arrays.fill(paddingBytes, paddingByte);

        if (paddingFirst) {
            buf.writeBytes(paddingBytes);
        }
        buf.writeBytes(entityBytes);
        if (!paddingFirst) {
            buf.writeBytes(paddingBytes);
        }
    }

}
