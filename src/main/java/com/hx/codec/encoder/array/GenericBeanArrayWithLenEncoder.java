package com.hx.codec.encoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.entity.GenericBeanEncoder;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * GenericBeanArrayEncoder
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:19
 */
public class GenericBeanArrayWithLenEncoder<T> extends AbstractEncoder<T[]> {

    private GenericBeanEncoder<T> encoder;
    private ByteType lenByteType;

    public GenericBeanArrayWithLenEncoder(GenericBeanSchema<T> beanSchema, ByteType lenByteType) {
        super(DEFAULT_BYTE_ORDER);
        this.encoder = new GenericBeanEncoder<>(beanSchema);
        this.lenByteType = lenByteType;
    }

    @Override
    public void encode(T[] entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.length, buf);
        for (T ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
