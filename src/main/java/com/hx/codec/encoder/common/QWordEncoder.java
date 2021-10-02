package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * QWordEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:45
 */
public class QWordEncoder extends AbstractEncoder<Long> {

    public QWordEncoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public QWordEncoder() {
    }

    @Override
    public void encode(Long entity, ByteBuf buf) {
        if (isBigEndian()) {
            buf.writeLong(entity);
        } else {
            buf.writeLongLE(entity);
        }
    }
}
