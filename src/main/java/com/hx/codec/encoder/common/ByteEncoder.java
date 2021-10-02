package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * SingleByteEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class ByteEncoder extends AbstractEncoder<Integer> {

    public ByteEncoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public ByteEncoder() {
    }

    @Override
    public void encode(Integer entity, ByteBuf buf) {
        buf.writeByte(entity);
    }

}
