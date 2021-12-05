package com.hx.codec.encoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * DWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class DWordCollectionWithLenEncoder extends AbstractEncoder<Collection<Integer>> {

    private DWordEncoder encoder;
    private ByteType lenByteType;

    public DWordCollectionWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public DWordCollectionWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public DWordCollectionWithLenEncoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public void encode(Collection<Integer> entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.size(), buf);
        for (Integer ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
