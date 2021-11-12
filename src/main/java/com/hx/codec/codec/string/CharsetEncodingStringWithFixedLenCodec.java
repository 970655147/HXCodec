package com.hx.codec.codec.string;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;
import com.hx.codec.constants.CodecConstants;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * CharsetEncodingStringWithFixedLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class CharsetEncodingStringWithFixedLenCodec extends DelegateCodec<String, String> {

    public CharsetEncodingStringWithFixedLenCodec(ByteOrder byteOrder, byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        super(new DelegateWithFixedLenCodec<>(new CharsetEncodingStringCodec(byteOrder, charset), paddingByte, paddingFirst, fixedLength));
    }

    public CharsetEncodingStringWithFixedLenCodec(byte paddingByte, boolean paddingFirst, int fixedLength, Charset charset) {
        this(CodecConstants.DEFAULT_BYTE_ORDER, paddingByte, paddingFirst, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(byte paddingByte, int fixedLength, Charset charset) {
        this(paddingByte, CodecConstants.DEFAULT_PADDING_FIRST, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(int fixedLength, Charset charset) {
        this(CodecConstants.DEFAULT_PADDING_BYTE, fixedLength, charset);
    }

    public CharsetEncodingStringWithFixedLenCodec(int fixedLength) {
        this(fixedLength, CodecConstants.DEFAULT_CHARSET);
    }
}
