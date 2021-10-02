package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * UnsignedWordDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class UnsignedWordDecoder extends AbstractDecoder<Integer> {

    public UnsignedWordDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public UnsignedWordDecoder() {
    }

    @Override
    public Integer decode(ByteBuf buf) {
        Integer result = 0;
        if (isBigEndian()) {
            result = (int) buf.readShort();
        } else {
            result = (int) buf.readShortLE();
        }

        if (result < 0) {
            result += (1 << 8);
        }
        return result;
    }

}
