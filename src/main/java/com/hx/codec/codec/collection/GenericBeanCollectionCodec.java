package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.GenericBeanCollectionDecoder;
import com.hx.codec.encoder.collection.GenericBeanCollectionEncoder;
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
public class GenericBeanCollectionCodec<T> extends AbstractCodec<Collection<T>, Collection<T>> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanCollectionCodec(GenericBeanSchema<T> beanSchema) {
        this.beanSchema = beanSchema;
        encoder = new GenericBeanCollectionEncoder<>(beanSchema);
        decoder = new GenericBeanCollectionDecoder<>(beanSchema);
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
        return false;
    }

    @Override
    public int length() {
        return beanSchema.getLength();
    }
}
