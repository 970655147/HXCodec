package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.ByteDecoder;
import com.hx.codec.encoder.common.ByteEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.BYTE_UNIT;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class ByteCodec extends AbstractCodec<Integer, Integer> {

    public ByteCodec(ByteOrder byteOrder) {
        encoder = new ByteEncoder(byteOrder);
        decoder = new ByteDecoder(byteOrder);
    }

    public ByteCodec() {
        encoder = new ByteEncoder();
        decoder = new ByteDecoder();
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
        return BYTE_UNIT;
    }
}
