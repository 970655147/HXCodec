package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.decoder.string.CharsetEncodingStringWithFixedLenDecoder;
import com.hx.codec.encoder.string.CharsetEncodingStringWithFixedLenEncoder;
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
public class CharsetEncodingStringWithFixedLenCodec extends AbstractCodec<String, String> {

    private int fixedLength;

    public CharsetEncodingStringWithFixedLenCodec(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        this.fixedLength = fixedLength;
        encoder = new CharsetEncodingStringWithFixedLenEncoder(byteOrder, paddingByte, paddingFirst, fixedLength, charset);
        decoder = new CharsetEncodingStringWithFixedLenDecoder(byteOrder, paddingByte, paddingFirst, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, paddingByte, paddingFirst, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(byte paddingByte, int fixedLength, Charset charset) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, paddingByte, CodecConstants.DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(int fixedLength, Charset charset) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, CodecConstants.DEFAULT_PADDING_BYTE,
                CodecConstants.DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(int fixedLength) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, CodecConstants.DEFAULT_PADDING_BYTE,
                CodecConstants.DEFAULT_PADDING_FIRST, fixedLength, CodecConstants.DEFAULT_CHARSET);
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
