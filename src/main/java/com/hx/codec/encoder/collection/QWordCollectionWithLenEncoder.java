package com.hx.codec.encoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * QWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordCollectionWithLenEncoder extends AbstractEncoder<Collection<Long>> {

    private QWordEncoder encoder;
    private ByteType lenByteType;

    public QWordCollectionWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public QWordCollectionWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public QWordCollectionWithLenEncoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public void encode(Collection<Long> entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.size(), buf);
        for (Long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
