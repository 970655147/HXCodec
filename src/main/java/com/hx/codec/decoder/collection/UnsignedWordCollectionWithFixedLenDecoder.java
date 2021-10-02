package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.UnsignedWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * WordCollectionWithFixedLenDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:00
 */
public class UnsignedWordCollectionWithFixedLenDecoder extends AbstractDecoder<Collection<Integer>> {

    private UnsignedWordDecoder decoder;
    private int eleLength;

    public UnsignedWordCollectionWithFixedLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new UnsignedWordDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public UnsignedWordCollectionWithFixedLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Collection<Integer> decode(ByteBuf buf) {
        List<Integer> result = new ArrayList<>(eleLength);
        for (int i = 0; i < eleLength; i++) {
            byte nextByte = buf.getByte(buf.readerIndex());
            Integer ele = decoder.decode(buf);
            if (nextByte != DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING) {
                result.add(ele);
            }
        }
        return result;
    }
}