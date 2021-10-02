package com.hx.codec.decoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordArrayWithLenDecoder extends AbstractDecoder<Long[]> {

    private QWordDecoder decoder;
    private ByteType lenByteType;

    public QWordArrayWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new QWordDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public QWordArrayWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public QWordArrayWithLenDecoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public Long[] decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        Long[] result = new Long[(int) len];
        for (int i = 0; i < len; i++) {
            Long b = decoder.decode(buf);
            result[i] = b;
        }
        return result;
    }
}
