package com.hx.codec.codec.collection;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * UnsignedWordCollectionWithEleLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedWordCollectionWithFixedLenCodec extends DelegateCodec<Collection<Integer>, Collection<Integer>> {

    public UnsignedWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new UnsignedWordCollectionCodec(byteOrder), paddingByte, paddingFirst, WORD_UNIT, fixedLength));
    }

    public UnsignedWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public UnsignedWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public UnsignedWordCollectionWithFixedLenCodec(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }
}
