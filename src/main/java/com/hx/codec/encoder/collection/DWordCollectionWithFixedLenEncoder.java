package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * DWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class DWordCollectionWithFixedLenEncoder extends AbstractEncoder<Collection<Integer>> {

    private DWordEncoder encoder;
    private int eleLength;

    public DWordCollectionWithFixedLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public DWordCollectionWithFixedLenEncoder(int eleLength) {
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
