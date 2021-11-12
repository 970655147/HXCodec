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
public class GenericBeanCollectionWithEleLenCodec<T> extends DelegateCodec<Collection<T>, Collection<T>> {

    // todo, apply byteOrder
    public GenericBeanCollectionWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new GenericBeanCollectionCodec<>(beanSchema), paddingByte, paddingFirst, beanSchema.getLength(), eleLength * beanSchema.getLength()));
    }

    public GenericBeanCollectionWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(beanSchema, byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public GenericBeanCollectionWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength) {
        this(beanSchema, byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public GenericBeanCollectionWithEleLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this(beanSchema, DEFAULT_BYTE_ORDER, eleLength);
    }

}
