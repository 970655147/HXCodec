package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.collection.QWordCollectionWithExactlyLenDecoder;
import com.hx.codec.encoder.collection.QWordCollectionWithExactlyLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class QWordCollectionWithExactlyLenCodec extends AbstractCodec<Collection<Long>, Collection<Long>> {

    private int eleLength;

    public QWordCollectionWithExactlyLenCodec(ByteOrder byteOrder, int eleLength) {
        this.eleLength = eleLength;
        encoder = new QWordCollectionWithExactlyLenEncoder(byteOrder, eleLength);
        decoder = new QWordCollectionWithExactlyLenDecoder(byteOrder, eleLength);
    }

    public QWordCollectionWithExactlyLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Collection<Long> entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Collection<Long> decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return 8 * eleLength;
    }
}
