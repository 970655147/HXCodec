package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Array;
import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_ELE_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class GenericBeanArrayWithEleLenDecoder<T> extends AbstractDecoder<T[]> {

    private GenericBeanDecoder<T> decoder;
    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanArrayWithEleLenDecoder(GenericBeanSchema<T> beanSchema, int eleLength) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        decoder = new GenericBeanDecoder<>(beanSchema);
        this.eleLength = eleLength;
    }

    @Override
    public T[] decode(ByteBuf buf) {
        T[] tmpResult = (T[]) Array.newInstance(beanSchema.getClazz(), eleLength);
        int length = 0;
        for (int i = 0; i < eleLength; i++) {
            byte nextByte = buf.getByte(buf.readerIndex());
            T ele = decoder.decode(buf);
            if (nextByte != DEFAULT_ARRAY_WITH_ELE_LEN_PADDING) {
                length = i + 1;
                tmpResult[i] = ele;
            }
        }

        T[] result = (T[]) Array.newInstance(beanSchema.getClazz(), length);
        for (int i = 0; i < length; i++) {
            result[i] = tmpResult[i];
        }
        return result;
    }
}
