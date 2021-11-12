package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.array.GenericBeanArrayWithLenDecoder;
import com.hx.codec.encoder.array.GenericBeanArrayWithLenEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

/**
 * GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class GenericBeanArrayWithLenCodec<T> extends AbstractCodec<T[], T[]> {

    private GenericBeanSchema<T> beanSchema;

    public GenericBeanArrayWithLenCodec(GenericBeanSchema<T> beanSchema, ByteType lenByteType) {
        this.beanSchema = beanSchema;
        encoder = new GenericBeanArrayWithLenEncoder<>(beanSchema, lenByteType);
        decoder = new GenericBeanArrayWithLenDecoder<>(beanSchema, lenByteType);
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
