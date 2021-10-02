package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.ByteEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class ByteArrayEncoder extends AbstractEncoder<Integer[]> {

    private ByteEncoder encoder;

    public ByteArrayEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new ByteEncoder(byteOrder);
    }

    public ByteArrayEncoder() {
        this.encoder = new ByteEncoder();
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        for (int ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
