package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * DWordEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:45
 */
public class DWordEncoder extends AbstractEncoder<Integer> {

    public DWordEncoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public DWordEncoder() {
    }

    @Override
    public void encode(Integer entity, ByteBuf buf) {
        if (isBigEndian()) {
            buf.writeInt(entity);
        } else {
            buf.writeIntLE(entity);
        }
    }
}
