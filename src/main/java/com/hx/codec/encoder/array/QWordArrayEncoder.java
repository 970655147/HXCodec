package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordArrayEncoder extends AbstractEncoder<Long[]> {

    private QWordEncoder encoder;

    public QWordArrayEncoder(ByteOrder byteOrder) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
    }

    public QWordArrayEncoder() {
        this.encoder = new QWordEncoder();
    }

    @Override
    public void encode(Long[] entity, ByteBuf buf) {
        for (long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
