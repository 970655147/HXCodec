package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.QWordCollectionDecoder;
import com.hx.codec.encoder.collection.QWordCollectionEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordCollectionCodec extends AbstractCodec<Collection<Long>, Collection<Long>> {

    public QWordCollectionCodec(ByteOrder byteOrder) {
        encoder = new QWordCollectionEncoder(byteOrder);
        decoder = new QWordCollectionDecoder(byteOrder);
    }

    public QWordCollectionCodec() {
        encoder = new QWordCollectionEncoder();
        decoder = new QWordCollectionDecoder();
    }

    @Override
    public void encode(Collection<Long> entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Collection<Long> decode(ByteBuf buf) {
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
