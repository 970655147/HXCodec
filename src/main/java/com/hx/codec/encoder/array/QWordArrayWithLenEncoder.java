package com.hx.codec.encoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.QWordEncoder;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class QWordArrayWithLenEncoder extends AbstractEncoder<Long[]> {

    private QWordEncoder encoder;
    private ByteType lenByteType;

    public QWordArrayWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.encoder = new QWordEncoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public QWordArrayWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public QWordArrayWithLenEncoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public void encode(Long[] entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.length, buf);
        for (long ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
