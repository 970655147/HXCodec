package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * DWordDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class DWordDecoder extends AbstractDecoder<Integer> {

    public DWordDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public DWordDecoder() {
    }

    @Override
    public Integer decode(ByteBuf buf) {
        Integer result = 0;
        if (isBigEndian()) {
            result = buf.readInt();
        } else {
            result = buf.readIntLE();
        }

        return result;
    }

}
