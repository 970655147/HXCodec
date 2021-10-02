package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.array.QWordArrayWithLenDecoder;
import com.hx.codec.encoder.array.QWordArrayWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordArrayWithLenCodec extends AbstractCodec<Long[], Long[]> {

    public QWordArrayWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new QWordArrayWithLenEncoder(byteOrder, lenByteType);
        decoder = new QWordArrayWithLenDecoder(byteOrder, lenByteType);
    }

    public QWordArrayWithLenCodec(ByteType lenByteType) {
        encoder = new QWordArrayWithLenEncoder(lenByteType);
        decoder = new QWordArrayWithLenDecoder(lenByteType);
    }

    public QWordArrayWithLenCodec() {
        encoder = new QWordArrayWithLenEncoder();
        decoder = new QWordArrayWithLenDecoder();
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
