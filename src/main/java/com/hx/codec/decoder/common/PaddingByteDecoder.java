package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTE;

/**
 * PaddingByteEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:37
 */
public class PaddingByteDecoder extends AbstractDecoder<Object> {

    // padding related
    private byte paddingByte;
    // length
    private int fixedLength;
    // padding bytes
    private byte[] paddingBytes;

    public PaddingByteDecoder(byte paddingByte, int fixedLength) {
        this.paddingByte = paddingByte;
        this.fixedLength = fixedLength;

        paddingBytes = new byte[fixedLength];
        Arrays.fill(paddingBytes, paddingByte);
    }

    public PaddingByteDecoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTE, fixedLength);
    }

    @Override
    public Object decode(ByteBuf buf) {
        byte[] paddingBytes = new byte[fixedLength];
        buf.readBytes(paddingBytes);
        return paddingBytes;
    }
}
