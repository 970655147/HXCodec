package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.GenericBeanArrayDecoder;
import com.hx.codec.encoder.array.GenericBeanArrayEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayCodec<T> extends AbstractCodec<T[], T[]> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanArrayCodec(GenericBeanSchema<T> beanSchema) {
        this.beanSchema = beanSchema;
        encoder = new GenericBeanArrayEncoder<>(beanSchema);
        decoder = new GenericBeanArrayDecoder<>(beanSchema);
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
        return false;
    }

    @Override
    public int length() {
        return beanSchema.getLength();
    }
}
