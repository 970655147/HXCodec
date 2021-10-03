package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.GenericBeanArrayWithExactlyLenDecoder;
import com.hx.codec.encoder.array.GenericBeanArrayWithExactlyLenEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithExactlyLenCodec<T> extends AbstractCodec<T[], T[]> {

    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanArrayWithExactlyLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this.beanSchema = beanSchema;
        this.eleLength = eleLength;
        encoder = new GenericBeanArrayWithExactlyLenEncoder<>(beanSchema, eleLength);
        decoder = new GenericBeanArrayWithExactlyLenDecoder<>(beanSchema, eleLength);
    }


    @Override
    public void encode(T[] entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public T[] decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return beanSchema.isFixedLength();
    }

    @Override
    public int length() {
        return eleLength * beanSchema.getLength();
    }
}
