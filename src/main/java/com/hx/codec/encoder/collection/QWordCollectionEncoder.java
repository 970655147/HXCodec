package com.hx.codec.encoder.collection;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

/**
 * QWordCollectionEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordCollectionEncoder extends AbstractEncoder<Collection<Long>> {

    private QWordEncoder encoder;

    public QWordCollectionEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
    }

    public QWordCollectionEncoder() {
        this.encoder = new QWordEncoder();
    }

    @Override
    public void encode(Collection<Long> entity, ByteBuf buf) {
        for (Long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
