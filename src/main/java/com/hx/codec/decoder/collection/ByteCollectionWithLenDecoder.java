package com.hx.codec.decoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.ByteDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * ByteCollectionDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class ByteCollectionWithLenDecoder extends AbstractDecoder<Collection<Integer>> {

    private ByteDecoder decoder;
    private ByteType lenByteType;

    public ByteCollectionWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new ByteDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public ByteCollectionWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public ByteCollectionWithLenDecoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public Collection<Integer> decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        List<Integer> result = new ArrayList<>((int) len);
        for (int i = 0; i < len; i++) {
            Integer b = decoder.decode(buf);
            result.add(b);
        }
        return result;
    }
}