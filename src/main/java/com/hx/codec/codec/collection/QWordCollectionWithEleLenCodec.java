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
public class QWordCollectionWithEleLenCodec extends DelegateCodec<Collection<Long>, Collection<Long>> {

    public QWordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new QWordCollectionCodec(byteOrder), paddingByte, paddingFirst, QWORD_UNIT, eleLength * QWORD_UNIT));
    }

    public QWordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public QWordCollectionWithEleLenCodec(ByteOrder byteOrder, int eleLength) {
        this(byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public QWordCollectionWithEleLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }
}
