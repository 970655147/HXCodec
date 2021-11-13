package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;
import com.hx.codec.schema.GenericBeanSchema;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * GenericBeanArrayWithFixedLenCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithFixedLenCodec<T> extends DelegateCodec<T[], T[]> {

    // todo, apply byteOrder
    public GenericBeanArrayWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new GenericBeanArrayCodec<>(beanSchema), paddingByte, paddingFirst, beanSchema.getLength(), fixedLength));
    }

    public GenericBeanArrayWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength, byte paddingByte) {
        this(beanSchema, byteOrder, fixedLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public GenericBeanArrayWithFixedLenCodec(GenericBeanSchema<T> beanSchema, ByteOrder byteOrder, int fixedLength) {
        this(beanSchema, byteOrder, fixedLength, DEFAULT_PADDING_BYTE);
    }

    public GenericBeanArrayWithFixedLenCodec(GenericBeanSchema<T> beanSchema, int fixedLength) {
        this(beanSchema, DEFAULT_BYTE_ORDER, fixedLength);
    }
}
