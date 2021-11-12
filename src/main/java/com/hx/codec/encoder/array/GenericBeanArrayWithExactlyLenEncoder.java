package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.entity.GenericBeanEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayEncoder
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:19
 */
public class GenericBeanArrayWithExactlyLenEncoder<T> extends AbstractEncoder<T[]> {

    private GenericBeanEncoder<T> encoder;
    private GenericBeanSchema<T> beanSchema;
    private int eleLength;

    public GenericBeanArrayWithExactlyLenEncoder(GenericBeanSchema<T> beanSchema, int eleLength) {
        super(DEFAULT_BYTE_ORDER);
        this.beanSchema = beanSchema;
        this.encoder = new GenericBeanEncoder<>(beanSchema);
        this.eleLength = eleLength;
    }

    @Override
    public void encode(T[] entity, ByteBuf buf) {
        AssertUtils.state(entity.length == eleLength, " unexpected entity length ");
        for (T ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
