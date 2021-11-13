package com.hx.codec.codec.collection;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;
import com.hx.codec.schema.GenericBeanSchema;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * GenericBeanCollectionCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanCollectionWithFixedLenCodec<T> extends DelegateCodec<Collection<T>, Collection<T>> {

    // todo, apply byteOrder
    public GenericBeanCollectionWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new GenericBeanCollectionCodec<>(beanSchema), paddingByte, paddingFirst, beanSchema.getLength(), fixedLength));
    }

    public GenericBeanCollectionWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(beanSchema, byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public GenericBeanCollectionWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength) {
        this(beanSchema, byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public GenericBeanCollectionWithFixedLenCodec(GenericBeanSchema<T> beanSchema, int fixedLength) {
        this(beanSchema, DEFAULT_BYTE_ORDER, fixedLength);
    }

}
