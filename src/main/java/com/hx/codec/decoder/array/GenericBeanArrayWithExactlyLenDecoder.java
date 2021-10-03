package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Array;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class GenericBeanArrayWithExactlyLenDecoder<T> extends AbstractDecoder<T[]> {

    private GenericBeanDecoder<T> decoder;
    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanArrayWithExactlyLenDecoder(GenericBeanSchema<T> beanSchema, int eleLength) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        decoder = new GenericBeanDecoder<>(beanSchema);
        this.eleLength = eleLength;
    }

    @Override
    public T[] decode(ByteBuf buf) {
        T[] result = (T[]) Array.newInstance(beanSchema.getClazz(), eleLength);
        for (int i = 0; i < eleLength; i++) {
            T ele = decoder.decode(buf);
            result[i] = ele;
        }

        return result;
    }
}
