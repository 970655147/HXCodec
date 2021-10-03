package com.hx.codec.decoder.string;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * CharsetEncodingStringEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:59
 */
public class CharsetEncodingStringWithFixedLenDecoder extends AbstractDecoder<String> {

    // padding related
    private byte paddingByte;
    private boolean paddingFirst;
    // length
    private int fixedLength;
    // charset
    private Charset charset;

    public CharsetEncodingStringWithFixedLenDecoder(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        super(byteOrder);
        this.paddingByte = paddingByte;
        this.paddingFirst = paddingFirst;
        this.fixedLength = fixedLength;
        this.charset = charset;
    }

    public CharsetEncodingStringWithFixedLenDecoder(byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        this(DEFAULT_BYTE_ORDER, paddingByte, paddingFirst, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenDecoder(byte paddingByte, int fixedLength, Charset charset) {
        this(DEFAULT_BYTE_ORDER, paddingByte, DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenDecoder(int fixedLength, Charset charset) {
        this(DEFAULT_PADDING_BYTE, DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenDecoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTE, DEFAULT_PADDING_FIRST, fixedLength, DEFAULT_CHARSET);
    }

    @Override
    public String decode(ByteBuf buf) {
        byte[] entityBytes = new byte[fixedLength];
        buf.readBytes(entityBytes);

        int startIdx = 0, endIdx = entityBytes.length - 1;
        for (startIdx = 0; entityBytes[startIdx] == paddingByte && startIdx < endIdx; startIdx++) ;
        for (endIdx = entityBytes.length - 1; endIdx >= startIdx && entityBytes[endIdx] == paddingByte; endIdx--) ;
        return new String(entityBytes, startIdx, (endIdx - startIdx + 1), charset);
    }

}
