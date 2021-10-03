package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordArrayWithExactlyLenDecoder extends AbstractDecoder<Long[]> {

    private QWordDecoder decoder;
    private int eleLength;

    public QWordArrayWithExactlyLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new QWordDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public QWordArrayWithExactlyLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Long[] decode(ByteBuf buf) {
        Long[] result = new Long[eleLength];
        for (int i = 0; i < eleLength; i++) {
            Long ele = decoder.decode(buf);
            result[i] = ele;
        }

        return result;
    }
}
