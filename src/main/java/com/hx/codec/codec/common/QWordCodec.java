package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.QWordDecoder;
import com.hx.codec.encoder.common.QWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * WordProtocol (2 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordCodec extends AbstractCodec<Long, Long> {

    public QWordCodec(ByteOrder byteOrder) {
        encoder = new QWordEncoder(byteOrder);
        decoder = new QWordDecoder(byteOrder);
    }

    public QWordCodec() {
        encoder = new QWordEncoder();
        decoder = new QWordDecoder();
    }

    @Override
    public void encode(Long entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Long decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return 8;
    }

}
