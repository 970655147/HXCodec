package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_ELE_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class GenericBeanCollectionWithEleLenDecoder<T> extends AbstractDecoder<Collection<T>> {

    private GenericBeanDecoder<T> decoder;
    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanCollectionWithEleLenDecoder(GenericBeanSchema<T> beanSchema, int eleLength) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        decoder = new GenericBeanDecoder<>(beanSchema);
        this.eleLength = eleLength;
    }

    @Override
    public Collection<T> decode(ByteBuf buf) {
        List<T> result = new ArrayList<>(eleLength);
        for (int i = 0; i < eleLength; i++) {
            byte nextByte = buf.getByte(buf.readerIndex());
            T ele = decoder.decode(buf);
            if (nextByte != DEFAULT_ARRAY_WITH_ELE_LEN_PADDING) {
                result.add(ele);
            }
        }

        return result;
    }
}
