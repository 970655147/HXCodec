package com.hx.codec.decoder.collection;

import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.decoder.common.QWordDecoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * QWordCollectionWithFixedLenDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:00
 */
public class QWordCollectionWithExactlyLenDecoder extends AbstractDecoder<Collection<Long>> {

    private QWordDecoder decoder;
    private int eleLength;

    public QWordCollectionWithExactlyLenDecoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.decoder = new QWordDecoder(byteOrder);
        this.eleLength = eleLength;
    }

    public QWordCollectionWithExactlyLenDecoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public Collection<Long> decode(ByteBuf buf) {
        List<Long> result = new ArrayList<>(eleLength);
        for (int i = 0; i < eleLength; i++) {
            Long ele = decoder.decode(buf);
            result.add(ele);
        }
        return result;
    }
}