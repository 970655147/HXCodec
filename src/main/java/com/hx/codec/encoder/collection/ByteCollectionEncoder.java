package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.ByteEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * ByteCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class ByteCollectionEncoder extends AbstractEncoder<Collection<Integer>> {

    private ByteEncoder encoder;

    public ByteCollectionEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new ByteEncoder(byteOrder);
    }

    public ByteCollectionEncoder() {
        this.encoder = new ByteEncoder();
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
