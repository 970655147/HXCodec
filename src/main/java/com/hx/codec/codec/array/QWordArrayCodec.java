package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.QWordArrayDecoder;
import com.hx.codec.encoder.array.QWordArrayEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordArrayCodec extends AbstractCodec<Long[], Long[]> {

    public QWordArrayCodec(ByteOrder byteOrder) {
        encoder = new QWordArrayEncoder(byteOrder);
        decoder = new QWordArrayDecoder(byteOrder);
    }

    public QWordArrayCodec() {
        encoder = new QWordArrayEncoder();
        decoder = new QWordArrayDecoder();
    }

    @Override
    public void encode(Long[] entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Long[] decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 8;
    }
}
