package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.DWordCollectionDecoder;
import com.hx.codec.encoder.collection.DWordCollectionEncoder;
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
public class DWordCollectionCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    public DWordCollectionCodec(ByteOrder byteOrder) {
        encoder = new DWordCollectionEncoder(byteOrder);
        decoder = new DWordCollectionDecoder(byteOrder);
    }

    public DWordCollectionCodec() {
        encoder = new DWordCollectionEncoder();
        decoder = new DWordCollectionDecoder();
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Collection<Integer> decode(ByteBuf buf) {
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
