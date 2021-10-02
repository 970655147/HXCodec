package com.hx.codec.codec.entity;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.encoder.entity.GenericBeanEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

/**
 * GenericBeanCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 14:27
 */
public class GenericBeanCodec<T> extends AbstractCodec<T, T> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanCodec(GenericBeanSchema<T> beanSchema) {
        this.beanSchema = beanSchema;
        encoder = new GenericBeanEncoder<>(beanSchema);
        decoder = new GenericBeanDecoder<>(beanSchema);
    }

    @Override
    public void encode(T entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public T decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return beanSchema.isFixedLength();
    }

    @Override
    public int length() {
        return beanSchema.getLength();
    }
}
