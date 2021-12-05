package com.hx.codec.encoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
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
public class DWordArrayWithLenEncoder extends AbstractEncoder<Integer[]> {

    private DWordEncoder encoder;
    private ByteType lenByteType;

    public DWordArrayWithLenEncoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public DWordArrayWithLenEncoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public DWordArrayWithLenEncoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, byteOrder, entity.length, buf);
        for (int ele : entity) {
            encoder.encode(ele, buf);
        }
    }

}
