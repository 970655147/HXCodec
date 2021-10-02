package com.hx.codec.decoder.array;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordArrayWithFixedLenDecoder extends AbstractDecoder<Long[]> {

    private QWordDecoder decoder;
    private int eleLength;

    public QWordArrayWithFixedLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new QWordDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public QWordArrayWithFixedLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Long[] decode(ByteBuf buf) {
        Long[] tmpResult = new Long[eleLength];
        int length = 0;
        for (int i = 0; i < eleLength; i++) {
            byte nextByte = buf.getByte(buf.readerIndex());
            Long ele = decoder.decode(buf);
            if (nextByte != DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING) {
                length = i + 1;
                tmpResult[i] = ele;
            }
        }

        Long[] result = new Long[length];
        for (int i = 0; i < length; i++) {
            result[i] = tmpResult[i];
        }
        return result;
    }
}
