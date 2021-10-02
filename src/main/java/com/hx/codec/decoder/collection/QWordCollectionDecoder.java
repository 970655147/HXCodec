package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * QWordCollectionDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordCollectionDecoder extends AbstractDecoder<Collection<Long>> {

    private QWordDecoder decoder;

    public QWordCollectionDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new QWordDecoder(byteOrder);
    }

    public QWordCollectionDecoder() {
        decoder = new QWordDecoder();
    }

    @Override
    public Collection<Long> decode(ByteBuf buf) {
        List<Long> result = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            Long b = decoder.decode(buf);
            result.add(b);
        }
        return result;
    }
}
