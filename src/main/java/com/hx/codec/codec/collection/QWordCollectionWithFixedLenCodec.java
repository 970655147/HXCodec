package com.hx.codec.codec.collection;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * QWordCollectionWithEleLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordCollectionWithFixedLenCodec extends DelegateCodec<Collection<Long>, Collection<Long>> {

    public QWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new QWordCollectionCodec(byteOrder), paddingByte, paddingFirst, QWORD_UNIT, fixedLength));
    }

    public QWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public QWordCollectionWithFixedLenCodec(ByteOrder byteOrder, int fixedLength) {
        this(byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public QWordCollectionWithFixedLenCodec(int fixedLength) {
        this(DEFAULT_BYTE_ORDER, fixedLength);
    }
}
