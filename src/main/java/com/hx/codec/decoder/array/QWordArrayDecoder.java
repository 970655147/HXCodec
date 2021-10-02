package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordArrayDecoder extends AbstractDecoder<Long[]> {

    private QWordDecoder decoder;

    public QWordArrayDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new QWordDecoder(byteOrder);
    }

    public QWordArrayDecoder() {
        decoder = new QWordDecoder();
    }

    @Override
    public Long[] decode(ByteBuf buf) {
        List<Long> result = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            long b = decoder.decode(buf);
            result.add(b);
        }
        return result.toArray(new Long[0]);
    }
}
