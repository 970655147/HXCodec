package com.hx.codec.encoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.entity.GenericBeanEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanCollectionWithLenEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-03 11:56
 */
public class GenericBeanCollectionWithLenEncoder<T> extends AbstractEncoder<Collection<T>> {

    private GenericBeanEncoder<T> encoder;
    private ByteType lenByteType;

    public GenericBeanCollectionWithLenEncoder(GenericBeanSchema<T> beanSchema, ByteType lenByteType) {
        super(DEFAULT_BYTE_ORDER);
        this.encoder = new GenericBeanEncoder<>(beanSchema);
        this.lenByteType = lenByteType;
    }

    @Override
    public void encode(Collection<T> entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.size(), buf);
        for (T ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
