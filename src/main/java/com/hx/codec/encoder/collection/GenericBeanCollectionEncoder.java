package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.entity.GenericBeanEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayEncoder
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:19
 */
public class GenericBeanCollectionEncoder<T> extends AbstractEncoder<Collection<T>> {

    private GenericBeanEncoder<T> encoder;

    public GenericBeanCollectionEncoder(GenericBeanSchema<T> beanSchema) {
        super(DEFAULT_BYTE_ORDER);
        this.encoder = new GenericBeanEncoder<>(beanSchema);
    }

    @Override
    public void encode(Collection<T> entity, ByteBuf buf) {
        for (T ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
