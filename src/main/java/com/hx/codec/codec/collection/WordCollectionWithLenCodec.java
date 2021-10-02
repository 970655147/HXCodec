package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.WordCollectionWithLenDecoder;
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
public class WordCollectionWithLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    public WordCollectionWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new WordCollectionWithLenEncoder(byteOrder, lenByteType);
        decoder = new WordCollectionWithLenDecoder(byteOrder, lenByteType);
    }

    public WordCollectionWithLenCodec(ByteType lenByteType) {
        encoder = new WordCollectionWithLenEncoder(lenByteType);
        decoder = new WordCollectionWithLenDecoder(lenByteType);
    }

    public WordCollectionWithLenCodec() {
        encoder = new WordCollectionWithLenEncoder();
        decoder = new WordCollectionWithLenDecoder();
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
