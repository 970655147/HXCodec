package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * DWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class DWordCollectionEncoder extends AbstractEncoder<Collection<Integer>> {

    private DWordEncoder encoder;

    public DWordCollectionEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
    }

    public DWordCollectionEncoder() {
        this.encoder = new DWordEncoder();
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
