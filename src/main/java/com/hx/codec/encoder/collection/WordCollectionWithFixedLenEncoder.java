package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.WordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * WordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class WordCollectionWithFixedLenEncoder extends AbstractEncoder<Collection<Integer>> {

    private WordEncoder encoder;
    private int eleLength;

    public WordCollectionWithFixedLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new WordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public WordCollectionWithFixedLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }

        byte[] paddingBytes = new byte[eleLength - entity.size()];
        Arrays.fill(paddingBytes, DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING);
        buf.writeBytes(paddingBytes);
    }

}
