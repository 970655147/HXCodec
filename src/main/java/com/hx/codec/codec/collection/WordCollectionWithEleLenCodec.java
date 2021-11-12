package com.hx.codec.codec.collection;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * WordCollectionWithEleLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordCollectionWithEleLenCodec extends DelegateCodec<Collection<Integer>, Collection<Integer>> {

    public WordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new WordCollectionCodec(byteOrder), paddingByte, paddingFirst, WORD_UNIT, eleLength * WORD_UNIT));
    }

    public WordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public WordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength) {
        this(byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public WordCollectionWithEleLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }
}
