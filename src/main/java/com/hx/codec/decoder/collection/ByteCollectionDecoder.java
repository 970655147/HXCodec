package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.ByteDecoder;
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
public class ByteCollectionDecoder extends AbstractDecoder<Collection<Integer>> {

    private ByteDecoder decoder;

    public ByteCollectionDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new ByteDecoder(byteOrder);
    }

    public ByteCollectionDecoder() {
        decoder = new ByteDecoder();
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
