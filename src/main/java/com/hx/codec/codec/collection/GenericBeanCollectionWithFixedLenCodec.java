package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.GenericBeanCollectionWithFixedLenDecoder;
import com.hx.codec.encoder.collection.GenericBeanCollectionWithFixedLenEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.util.Collection;

/**
 * GenericBeanCollectionCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanCollectionWithFixedLenCodec<T> extends AbstractCodec<Collection<T>, Collection<T>> {

    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanCollectionWithFixedLenCodec(GenericBeanSchema<T> beanSchema, int eleLength) {
        this.beanSchema = beanSchema;
        this.eleLength = eleLength;
        encoder = new GenericBeanCollectionWithFixedLenEncoder<>(beanSchema, eleLength);
        decoder = new GenericBeanCollectionWithFixedLenDecoder<>(beanSchema, eleLength);
    }

    @Override
    public void encode(Collection<T> entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Collection<T> decode(ByteBuf buf) {
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
