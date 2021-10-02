package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * QWordDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class QWordDecoder extends AbstractDecoder<Long> {

    public QWordDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public QWordDecoder() {
    }

    @Override
    public Long decode(ByteBuf buf) {
        Long result = 0L;
        if (isBigEndian()) {
            result = buf.readLong();
        } else {
            result = buf.readLongLE();
        }

        return result;
    }

}
