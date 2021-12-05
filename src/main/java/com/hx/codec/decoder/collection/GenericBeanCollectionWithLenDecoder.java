package com.hx.codec.decoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.entity.GenericBeanDecoder;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class GenericBeanCollectionWithLenDecoder<T> extends AbstractDecoder<Collection<T>> {

    private GenericBeanDecoder<T> decoder;
    private GenericBeanSchema<T> beanSchema;
    private ByteType lenByteType;

    public GenericBeanCollectionWithLenDecoder(GenericBeanSchema<T> beanSchema, ByteType lenByteType) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        decoder = new GenericBeanDecoder<>(beanSchema);
        this.lenByteType = lenByteType;
    }

    @Override
    public Collection<T> decode(ByteBuf buf) {
        long len = ByteBufUtils.readLen(lenByteType, byteOrder, buf);
        List<T> result = new ArrayList<>((int) len);
        for (int i = 0; i < len; i++) {
            T b = decoder.decode(buf);
            result.add(b);
        }
        return result;
    }
}
