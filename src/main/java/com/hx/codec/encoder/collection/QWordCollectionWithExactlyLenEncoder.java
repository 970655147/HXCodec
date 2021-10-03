package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * QWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordCollectionWithExactlyLenEncoder extends AbstractEncoder<Collection<Long>> {

    private QWordEncoder encoder;
    private int eleLength;

    public QWordCollectionWithExactlyLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public QWordCollectionWithExactlyLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Collection<Long> entity, ByteBuf buf) {
        AssertUtils.state(entity.size() == eleLength, " unexpected entity length ");
        for (Long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
