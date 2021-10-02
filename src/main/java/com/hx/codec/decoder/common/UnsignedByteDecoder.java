package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * UnsignedByteDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class UnsignedByteDecoder extends AbstractDecoder<Integer> {

    public UnsignedByteDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public UnsignedByteDecoder() {
        super();
    }

    @Override
    public Integer decode(ByteBuf buf) {
        int result = (int) buf.readByte();
        if (result < 0) {
            result += (1 << 8);
        }
        return result;
    }

}
