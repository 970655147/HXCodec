package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.WordEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * WordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class WordCollectionWithExactlyLenEncoder extends AbstractEncoder<Collection<Integer>> {

    private WordEncoder encoder;
    private int eleLength;

    public WordCollectionWithExactlyLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new WordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public WordCollectionWithExactlyLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        AssertUtils.state(entity.size() == eleLength, " unexpected entity length ");
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
