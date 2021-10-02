package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.PaddingBytesDecoder;
import com.hx.codec.encoder.common.PaddingBytesEncoder;
import io.netty.buffer.ByteBuf;

/**
 * PaddingBytesCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:50
 */
public class PaddingBytesCodec extends AbstractCodec<Object, Object> {

    private int fixedLength;

    public PaddingBytesCodec(byte[] loopPaddingBytes, int fixedLength) {
        this.fixedLength = fixedLength;
        encoder = new PaddingBytesEncoder(loopPaddingBytes, fixedLength);
        decoder = new PaddingBytesDecoder(loopPaddingBytes, fixedLength);
    }

    public PaddingBytesCodec(int fixedLength) {
        encoder = new PaddingBytesEncoder(fixedLength);
        decoder = new PaddingBytesDecoder(fixedLength);
    }

    @Override
    public void encode(Object entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Object decode(ByteBuf buf) {
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
