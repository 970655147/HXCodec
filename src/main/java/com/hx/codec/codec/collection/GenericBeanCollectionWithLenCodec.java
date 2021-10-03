package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.collection.GenericBeanCollectionWithLenDecoder;
import com.hx.codec.encoder.collection.GenericBeanCollectionWithLenEncoder;
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
public class GenericBeanCollectionWithLenCodec<T> extends AbstractCodec<Collection<T>, Collection<T>> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanCollectionWithLenCodec(GenericBeanSchema<T> beanSchema, ByteType lenByteType) {
        this.beanSchema = beanSchema;
        encoder = new GenericBeanCollectionWithLenEncoder<>(beanSchema, lenByteType);
        decoder = new GenericBeanCollectionWithLenDecoder<>(beanSchema, lenByteType);
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
