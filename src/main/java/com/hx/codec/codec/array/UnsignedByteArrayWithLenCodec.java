package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.array.UnsignedByteArrayWithLenDecoder;
import com.hx.codec.encoder.array.ByteArrayWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;
import static com.hx.codec.constants.CodecConstants.DEFAULT_LEN_BYTE_TYPE;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class UnsignedByteArrayWithLenCodec extends AbstractCodec<Integer[], Integer[]> {

    public UnsignedByteArrayWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new ByteArrayWithLenEncoder(byteOrder, lenByteType);
        decoder = new UnsignedByteArrayWithLenDecoder(byteOrder, lenByteType);
    }

    public UnsignedByteArrayWithLenCodec(ByteType lenByteType) {
        this(DEFAULT_BYTE_ORDER, lenByteType);
    }

    public UnsignedByteArrayWithLenCodec() {
        this(DEFAULT_LEN_BYTE_TYPE);
    }


    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Integer[] decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 1;
    }
}
