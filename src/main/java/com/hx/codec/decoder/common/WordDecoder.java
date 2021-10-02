package com.hx.codec.decoder.common;

import com.hx.codec.decoder.AbstractDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * WordDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:10
 */
public class WordDecoder extends AbstractDecoder<Integer> {

    public WordDecoder(ByteOrder byteOrder) {
        super(byteOrder);
    }

    public WordDecoder() {
    }

    @Override
    public Integer decode(ByteBuf buf) {
        Integer result = 0;
        if (isBigEndian()) {
            result = (int) buf.readShort();
        } else {
            result = (int) buf.readShortLE();
        }

        return result;
    }

}
