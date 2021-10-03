package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordArrayWithExactlyLenEncoder extends AbstractEncoder<Long[]> {

    private QWordEncoder encoder;
    private int eleLength;

    public QWordArrayWithExactlyLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public QWordArrayWithExactlyLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Long[] entity, ByteBuf buf) {
        AssertUtils.state(entity.length == eleLength, " unexpected entity length ");
        for (Long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
