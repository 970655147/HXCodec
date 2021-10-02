package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.UnsignedByteDecoder;
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
public class UnsignedByteArrayDecoder extends AbstractDecoder<Integer[]> {

    private UnsignedByteDecoder decoder;

    public UnsignedByteArrayDecoder(ByteOrder byteOrder) {
        super(byteOrder);
        decoder = new UnsignedByteDecoder(byteOrder);
    }

    public UnsignedByteArrayDecoder() {
        decoder = new UnsignedByteDecoder();
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
