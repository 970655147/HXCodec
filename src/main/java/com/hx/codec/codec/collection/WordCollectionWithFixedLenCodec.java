package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.WordCollectionWithFixedLenDecoder;
import com.hx.codec.encoder.collection.WordCollectionWithFixedLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordCollectionWithFixedLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    private int eleLength;

    public WordCollectionWithFixedLenCodec(ByteOrder byteOrder, int eleLength) {
        this.eleLength = eleLength;
        encoder = new WordCollectionWithFixedLenEncoder(byteOrder, eleLength);
        decoder = new WordCollectionWithFixedLenDecoder(byteOrder, eleLength);
    }

    public WordCollectionWithFixedLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
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
        return true;
    }

    @Override
    public int length() {
        return 2 * eleLength;
    }
}
