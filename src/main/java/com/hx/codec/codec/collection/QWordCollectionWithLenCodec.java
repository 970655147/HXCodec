package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.QWordCollectionWithLenDecoder;
import com.hx.codec.encoder.collection.QWordCollectionWithLenEncoder;
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
public class QWordCollectionWithLenCodec extends AbstractCodec<Collection<Long>, Collection<Long>> {

    public QWordCollectionWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new QWordCollectionWithLenEncoder(byteOrder, lenByteType);
        decoder = new QWordCollectionWithLenDecoder(byteOrder, lenByteType);
    }

    public QWordCollectionWithLenCodec(ByteType lenByteType) {
        encoder = new QWordCollectionWithLenEncoder(lenByteType);
        decoder = new QWordCollectionWithLenDecoder(lenByteType);
    }

    public QWordCollectionWithLenCodec() {
        encoder = new QWordCollectionWithLenEncoder();
        decoder = new QWordCollectionWithLenDecoder();
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
