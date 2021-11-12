package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.DWordArrayDecoder;
import com.hx.codec.encoder.array.DWordArrayEncoder;
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
public class DWordArrayCodec extends AbstractCodec<Integer[], Integer[]> {

    public DWordArrayCodec(ByteOrder byteOrder) {
        encoder = new DWordArrayEncoder(byteOrder);
        decoder = new DWordArrayDecoder(byteOrder);
    }

    public DWordArrayCodec() {
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
        return 4;
    }
}
