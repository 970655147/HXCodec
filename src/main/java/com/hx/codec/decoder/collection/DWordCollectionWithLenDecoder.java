package com.hx.codec.decoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.DWordDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * DWordCollectionDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class DWordCollectionWithLenDecoder extends AbstractDecoder<Collection<Integer>> {

    private DWordDecoder decoder;
    private ByteType lenByteType;

    public DWordCollectionWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new DWordDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public DWordCollectionWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public DWordCollectionWithLenDecoder() {
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