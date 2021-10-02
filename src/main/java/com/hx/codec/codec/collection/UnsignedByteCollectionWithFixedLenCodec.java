package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.UnsignedByteCollectionWithFixedLenDecoder;
import com.hx.codec.encoder.collection.ByteCollectionWithFixedLenEncoder;
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
public class UnsignedByteCollectionWithFixedLenCodec extends AbstractCodec<Collection<Integer>, Collection<Integer>> {

    private int eleLength;

    public UnsignedByteCollectionWithFixedLenCodec(ByteOrder byteOrder, int eleLength) {
        this.eleLength = eleLength;
        encoder = new ByteCollectionWithFixedLenEncoder(byteOrder, eleLength);
        decoder = new UnsignedByteCollectionWithFixedLenDecoder(byteOrder, eleLength);
    }

    public UnsignedByteCollectionWithFixedLenCodec(int eleLength) {
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
        return 1 * eleLength;
    }
}
