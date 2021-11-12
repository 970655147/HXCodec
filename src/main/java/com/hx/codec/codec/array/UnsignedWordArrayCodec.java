package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.UnsignedWordArrayDecoder;
import com.hx.codec.encoder.array.WordArrayEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedWordArrayCodec extends AbstractCodec<Integer[], Integer[]> {

    public UnsignedWordArrayCodec(ByteOrder byteOrder) {
        encoder = new WordArrayEncoder(byteOrder);
        decoder = new UnsignedWordArrayDecoder(byteOrder);
    }

    public UnsignedWordArrayCodec() {
        this(DEFAULT_BYTE_ORDER);
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
        return 2;
    }
}
