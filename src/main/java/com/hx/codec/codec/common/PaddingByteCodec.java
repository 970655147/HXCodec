package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.PaddingByteDecoder;
import com.hx.codec.encoder.common.PaddingByteEncoder;
import io.netty.buffer.ByteBuf;

/**
 * PaddingBytesCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:50
 */
public class PaddingByteCodec extends AbstractCodec<Object, Object> {

    private int fixedLength;

    public PaddingByteCodec(byte paddingByte, int fixedLength) {
        this.fixedLength = fixedLength;
        encoder = new PaddingByteEncoder(paddingByte, fixedLength);
        decoder = new PaddingByteDecoder(paddingByte, fixedLength);
    }

    public PaddingByteCodec(int fixedLength) {
        encoder = new PaddingByteEncoder(fixedLength);
        decoder = new PaddingByteDecoder(fixedLength);
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
