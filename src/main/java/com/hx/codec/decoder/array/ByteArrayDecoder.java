package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.ByteDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class ByteArrayDecoder extends AbstractDecoder<Integer[]> {

    private ByteDecoder decoder;

    public ByteArrayDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new ByteDecoder(byteOrder);
    }

    public ByteArrayDecoder() {
        decoder = new ByteDecoder();
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        List<Integer> result = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            int b = decoder.decode(buf);
            result.add(b);
        }
        return result.toArray(new Integer[0]);
    }
}
