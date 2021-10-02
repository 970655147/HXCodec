package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.array.DWordArrayWithLenDecoder;
import com.hx.codec.encoder.array.DWordArrayWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class DWordArrayWithLenCodec extends AbstractCodec<Integer[], Integer[]> {

    public DWordArrayWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new DWordArrayWithLenEncoder(byteOrder, lenByteType);
        decoder = new DWordArrayWithLenDecoder(byteOrder, lenByteType);
    }

    public DWordArrayWithLenCodec(ByteType lenByteType) {
        encoder = new DWordArrayWithLenEncoder(lenByteType);
        decoder = new DWordArrayWithLenDecoder(lenByteType);
    }

    public DWordArrayWithLenCodec() {
        encoder = new DWordArrayWithLenEncoder();
        decoder = new DWordArrayWithLenDecoder();
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
