package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.DWordCollectionWithLenDecoder;
import com.hx.codec.encoder.collection.DWordCollectionWithLenEncoder;
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
public class DWordCollectionWithLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    public DWordCollectionWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new DWordCollectionWithLenEncoder(byteOrder, lenByteType);
        decoder = new DWordCollectionWithLenDecoder(byteOrder, lenByteType);
    }

    public DWordCollectionWithLenCodec(ByteType lenByteType) {
        encoder = new DWordCollectionWithLenEncoder(lenByteType);
        decoder = new DWordCollectionWithLenDecoder(lenByteType);
    }

    public DWordCollectionWithLenCodec() {
        encoder = new DWordCollectionWithLenEncoder();
        decoder = new DWordCollectionWithLenDecoder();
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
