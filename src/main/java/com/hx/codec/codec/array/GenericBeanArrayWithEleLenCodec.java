package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;
import com.hx.codec.schema.GenericBeanSchema;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTE;
import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_FIRST;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithEleLenCodec<T> extends DelegateCodec<T[], T[]> {

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new GenericBeanArrayCodec<>(beanSchema), paddingByte, paddingFirst, beanSchema.getLength(), eleLength * beanSchema.getLength()));
    }

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, int eleLength, byte paddingByte) {
        this(beanSchema, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public GenericBeanArrayWithEleLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this(beanSchema, eleLength, DEFAULT_PADDING_BYTE);
    }

}
