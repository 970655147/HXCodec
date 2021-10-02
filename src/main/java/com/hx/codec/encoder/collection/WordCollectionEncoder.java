package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.WordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * WordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class WordCollectionEncoder extends AbstractEncoder<Collection<Integer>> {

    private WordEncoder encoder;

    public WordCollectionEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new WordEncoder(byteOrder);
    }

    public WordCollectionEncoder() {
        this.encoder = new WordEncoder();
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
