package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTES;

/**
 * PaddingByteEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:37
 */
public class PaddingBytesEncoder extends AbstractEncoder<Object> {

    // padding related
    private byte[] loopPaddingBytes;
    // length
    private int fixedLength;
    // padding bytes
    private byte[] paddingBytes;

    public PaddingBytesEncoder(byte[] loopPaddingBytes, int fixedLength) {
        this.loopPaddingBytes = loopPaddingBytes;
        this.fixedLength = fixedLength;
        initPaddingBytes();
    }

    public PaddingBytesEncoder(int fixedLength) {
        this(DEFAULT_PADDING_BYTES, fixedLength);
    }

    @Override
    public void encode(Object entity, ByteBuf buf) {
        buf.writeBytes(paddingBytes);
    }

    // ------------------------------------------ assist methods ------------------------------------------

    /**
     * initPaddingBytes
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:45
     */
    private void initPaddingBytes() {
        paddingBytes = new byte[fixedLength];
        int lastBatch = (fixedLength % loopPaddingBytes.length);
        int batchTimes = fixedLength / loopPaddingBytes.length;
        int iteIdx = 0;
        for (int i = 0; i < batchTimes; i++) {
            for (byte b : loopPaddingBytes) {
                paddingBytes[iteIdx++] = b;
            }
        }
        for (int i = 0; i < lastBatch; i++) {
            paddingBytes[iteIdx++] = loopPaddingBytes[i];
        }
    }

}
