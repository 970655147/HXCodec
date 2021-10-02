package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
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
public class PaddingByteEncoder extends AbstractEncoder<Object> {

    // padding related
    private byte paddingByte;
    // length
    private int fixedLength;
    // padding bytes
    private byte[] paddingBytes;

    public PaddingByteEncoder(byte paddingByte, int fixedLength) {
        this.paddingByte = paddingByte;
        this.fixedLength = fixedLength;

        paddingBytes = new byte[fixedLength];
        Arrays.fill(paddingBytes, paddingByte);
    }

    public PaddingByteEncoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTE, fixedLength);
    }

    @Override
    public void encode(Object entity, ByteBuf buf) {
        buf.writeBytes(paddingBytes);
    }

}
