package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * SingleByteEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class ByteDecoder extends AbstractDecoder<Integer> {

    public ByteDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public ByteDecoder() {
    }

    @Override
    public Integer decode(ByteBuf buf) {
        return (int) buf.readByte();
    }

}
