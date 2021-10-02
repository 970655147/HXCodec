package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.UnsignedByteDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ByteCollectionDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class UnsignedByteCollectionDecoder extends AbstractDecoder<Collection<Integer>> {

    private UnsignedByteDecoder decoder;

    public UnsignedByteCollectionDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new UnsignedByteDecoder(byteOrder);
    }

    public UnsignedByteCollectionDecoder() {
        decoder = new UnsignedByteDecoder();
    }

    @Override
    public Collection<Integer> decode(ByteBuf buf) {
        List<Integer> result = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            Integer b = decoder.decode(buf);
            result.add(b);
        }
        return result;
    }
}
