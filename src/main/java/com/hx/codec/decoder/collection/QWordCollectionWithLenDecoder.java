package com.hx.codec.decoder.collection;

import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * QWordCollectionDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:50
 */
public class QWordCollectionWithLenDecoder extends AbstractDecoder<Collection<Long>> {

    private QWordDecoder decoder;
    private ByteType lenByteType;

    public QWordCollectionWithLenDecoder(ByteOrder byteOrder, ByteType lenByteType) {
        super(byteOrder);
        this.decoder = new QWordDecoder(byteOrder);
        this.lenByteType = lenByteType;
    }

    public QWordCollectionWithLenDecoder(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public QWordCollectionWithLenDecoder() {
        this(DEFAULT_BYTE_ORDER, DEFAULT_LEN_BYTE_TYPE);
    }

    @Override
    public Collection<Long> decode(ByteBuf buf) {
        long len = CodecUtils.readLen(lenByteType, byteOrder, buf);
        List<Long> result = new ArrayList<>((int) len);
        for (int i = 0; i < len; i++) {
            Long b = decoder.decode(buf);
            result.add(b);
        }
        return result;
    }
}