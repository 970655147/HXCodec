package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.UnsignedWordDecoder;
import com.hx.codec.encoder.common.WordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * UnsignedWordProtocol (2 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedWordCodec extends AbstractCodec<Integer, Integer> {

    public UnsignedWordCodec(ByteOrder byteOrder) {
        encoder = new WordEncoder(byteOrder);
        decoder = new UnsignedWordDecoder(byteOrder);
    }

    public UnsignedWordCodec() {
        encoder = new WordEncoder();
        decoder = new UnsignedWordDecoder();
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
        return 2;
    }

}
