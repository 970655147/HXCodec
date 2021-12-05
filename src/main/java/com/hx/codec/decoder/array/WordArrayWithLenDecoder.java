package com.hx.codec.decoder.array;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.WordDecoder;
import com.hx.codec.utils.ByteBufUtils;
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
public class WordArrayWithLenDecoder extends AbstractDecoder<Integer[]> {

    private WordDecoder decoder;
    private ByteType lenByteType;

    public WordArrayWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new WordDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public WordArrayWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public WordArrayWithLenDecoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        long len = ByteBufUtils.readLen(lenByteType, byteOrder, buf);
        Integer[] result = new Integer[(int) len];
        for (int i = 0; i < len; i++) {
            Integer b = decoder.decode(buf);
            result[i] = b;
        }
        return result;
    }
}
