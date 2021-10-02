package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class DWordArrayEncoder extends AbstractEncoder<Integer[]> {

    private DWordEncoder encoder;

    public DWordArrayEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
    }

    public DWordArrayEncoder() {
        this.encoder = new DWordEncoder();
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        for (int ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
