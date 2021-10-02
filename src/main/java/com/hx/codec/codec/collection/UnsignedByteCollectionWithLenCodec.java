package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.UnsignedByteCollectionWithLenDecoder;
import com.hx.codec.encoder.collection.ByteCollectionWithLenEncoder;
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
public class UnsignedByteCollectionWithLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    public UnsignedByteCollectionWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new ByteCollectionWithLenEncoder(byteOrder, lenByteType);
        decoder = new UnsignedByteCollectionWithLenDecoder(byteOrder, lenByteType);
    }

    public UnsignedByteCollectionWithLenCodec(ByteType lenByteType) {
        encoder = new ByteCollectionWithLenEncoder(lenByteType);
        decoder = new UnsignedByteCollectionWithLenDecoder(lenByteType);
    }

    public UnsignedByteCollectionWithLenCodec() {
        encoder = new ByteCollectionWithLenEncoder();
        decoder = new UnsignedByteCollectionWithLenDecoder();
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
        return 1;
    }
}
