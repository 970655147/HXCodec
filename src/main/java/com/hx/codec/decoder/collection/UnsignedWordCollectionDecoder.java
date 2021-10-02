package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.UnsignedWordDecoder;
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
public class UnsignedWordCollectionDecoder extends AbstractDecoder<Collection<Integer>> {

    private UnsignedWordDecoder decoder;

    public UnsignedWordCollectionDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new UnsignedWordDecoder(byteOrder);
    }

    public UnsignedWordCollectionDecoder() {
        decoder = new UnsignedWordDecoder();
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
