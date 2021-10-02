package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTES;

/**
 * PaddingByteEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:37
 */
public class PaddingBytesDecoder extends AbstractDecoder<Object> {

    // padding related
    private byte[] loopPaddingBytes;
    // length
    private int fixedLength;
    // padding bytes
    private byte[] paddingBytes;

    public PaddingBytesDecoder(byte[] loopPaddingBytes, int fixedLength) {
        this.loopPaddingBytes = loopPaddingBytes;
        this.fixedLength = fixedLength;
    }

    public PaddingBytesDecoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTES, fixedLength);
    }

    @Override
    public Object decode(ByteBuf buf) {
        byte[] paddingBytes = new byte[fixedLength];
        buf.readBytes(paddingBytes);
        return paddingBytes;
    }
}
