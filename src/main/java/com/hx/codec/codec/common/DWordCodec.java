package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.DWordDecoder;
import com.hx.codec.encoder.common.DWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * WordProtocol (2 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class DWordCodec extends AbstractCodec<Integer, Integer> {

    public DWordCodec(ByteOrder byteOrder) {
        encoder = new DWordEncoder(byteOrder);
        decoder = new DWordDecoder(byteOrder);
    }

    public DWordCodec() {
        encoder = new DWordEncoder();
        decoder = new DWordDecoder();
    }

    @Override
    public void encode(Integer entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Integer decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return 4;
    }
}
