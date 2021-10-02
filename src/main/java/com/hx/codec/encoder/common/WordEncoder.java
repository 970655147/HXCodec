package com.hx.codec.encoder.common;

import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * WordEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class WordEncoder extends AbstractEncoder<Integer> {

    public WordEncoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public WordEncoder() {
    }

    @Override
    public void encode(Integer entity, ByteBuf buf) {
        if (isBigEndian()) {
            buf.writeShort(entity);
        } else {
            buf.writeShortLE(entity);
        }
    }

}
