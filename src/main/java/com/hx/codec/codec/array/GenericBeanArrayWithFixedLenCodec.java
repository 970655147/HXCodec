package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.GenericBeanArrayWithFixedLenDecoder;
import com.hx.codec.encoder.array.GenericBeanArrayWithFixedLenEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithFixedLenCodec<T> extends AbstractCodec<T[], T[]> {

    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanArrayWithFixedLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this.beanSchema = beanSchema;
        this.eleLength = eleLength;
        encoder = new GenericBeanArrayWithFixedLenEncoder<>(beanSchema, eleLength);
        decoder = new GenericBeanArrayWithFixedLenDecoder<>(beanSchema, eleLength);
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
