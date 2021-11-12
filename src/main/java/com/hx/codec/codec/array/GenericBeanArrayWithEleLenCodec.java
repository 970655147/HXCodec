package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;
import com.hx.codec.schema.GenericBeanSchema;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithEleLenCodec<T> extends DelegateCodec<T[], T[]> {

    // todo, apply byteOrder
    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new GenericBeanArrayCodec<>(beanSchema), paddingByte, paddingFirst, beanSchema.getLength(), eleLength * beanSchema.getLength()));
    }

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(beanSchema, byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int eleLength) {
        this(beanSchema, byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this(beanSchema, DEFAULT_BYTE_ORDER, eleLength);
    }
}
