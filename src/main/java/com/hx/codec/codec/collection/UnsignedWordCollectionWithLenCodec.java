package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.UnsignedWordCollectionWithLenDecoder;
import com.hx.codec.encoder.collection.WordCollectionWithLenEncoder;
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
public class UnsignedWordCollectionWithLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    public UnsignedWordCollectionWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new WordCollectionWithLenEncoder(byteOrder, lenByteType);
        decoder = new UnsignedWordCollectionWithLenDecoder(byteOrder, lenByteType);
    }

    public UnsignedWordCollectionWithLenCodec(ByteType lenByteType) {
        encoder = new WordCollectionWithLenEncoder(lenByteType);
        decoder = new UnsignedWordCollectionWithLenDecoder(lenByteType);
    }

    public UnsignedWordCollectionWithLenCodec() {
        encoder = new WordCollectionWithLenEncoder();
        decoder = new UnsignedWordCollectionWithLenDecoder();
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
        return 2;
    }
}
