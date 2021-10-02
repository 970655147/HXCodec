package com.hx.codec.encoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.ByteEncoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * ByteCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class ByteCollectionWithLenEncoder extends AbstractEncoder<Collection<Integer>> {

    private ByteEncoder encoder;
    private ByteType lenByteType;

    public ByteCollectionWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.encoder = new ByteEncoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public ByteCollectionWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public ByteCollectionWithLenEncoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        CodecUtils.writeLen(lenByteType, byteOrder, entity.size(), buf);
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
