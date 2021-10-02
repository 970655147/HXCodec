package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.WordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class WordArrayEncoder extends AbstractEncoder<Integer[]> {

    private WordEncoder encoder;

    public WordArrayEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new WordEncoder(byteOrder);
    }

    public WordArrayEncoder() {
        this.encoder = new WordEncoder();
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        for (int ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
