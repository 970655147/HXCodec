package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.UnsignedByteArrayDecoder;
import com.hx.codec.encoder.array.ByteArrayEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedByteArrayCodec extends AbstractCodec<Integer[], Integer[]> {

    public UnsignedByteArrayCodec(ByteOrder byteOrder) {
        encoder = new ByteArrayEncoder(byteOrder);
        decoder = new UnsignedByteArrayDecoder(byteOrder);
    }

    public UnsignedByteArrayCodec() {
        encoder = new ByteArrayEncoder();
        decoder = new UnsignedByteArrayDecoder();
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 1;
    }
}
