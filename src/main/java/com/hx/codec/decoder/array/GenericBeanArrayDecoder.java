package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class GenericBeanArrayDecoder<T> extends AbstractDecoder<T[]> {

    private GenericBeanDecoder<T> decoder;
    private GenericBeanSchema<T> beanSchema;

    public GenericBeanArrayDecoder(GenericBeanSchema<T> beanSchema) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        decoder = new GenericBeanDecoder<>(beanSchema);
    }

    @Override
    public T[] decode(ByteBuf buf) {
        List<T> list = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            T b = decoder.decode(buf);
            list.add(b);
        }

        T[] result = (T[]) Array.newInstance(beanSchema.getClazz(), list.size());
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
