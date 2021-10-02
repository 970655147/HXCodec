package com.hx.codec.decoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.ByteDecoder;
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
public class ByteArrayWithLenDecoder extends AbstractDecoder<Integer[]> {

    private ByteDecoder decoder;
    private ByteType lenByteType;

    public ByteArrayWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new ByteDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public ByteArrayWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public ByteArrayWithLenDecoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        Integer[] result = new Integer[(int) len];
        for (int i = 0; i < len; i++) {
            Integer b = decoder.decode(buf);
            result[i] = b;
        }
        return result;
    }
}
