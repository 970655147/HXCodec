package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.UnsignedByteDecoder;
import com.hx.codec.encoder.common.ByteEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * UnsignedByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedByteCodec extends AbstractCodec<Integer, Integer> {

    public UnsignedByteCodec(ByteOrder byteOrder) {
        encoder = new ByteEncoder(byteOrder);
        decoder = new UnsignedByteDecoder(byteOrder);
    }

    public UnsignedByteCodec() {
        encoder = new ByteEncoder();
        decoder = new UnsignedByteDecoder();
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
        return 1;
    }

}
