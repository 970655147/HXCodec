package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.array.WordArrayWithExactlyLenDecoder;
import com.hx.codec.encoder.array.WordArrayWithExactlyLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordArrayWithExactlyLenCodec extends AbstractCodec<Integer[], Integer[]> {

    private int eleLength;

    public WordArrayWithExactlyLenCodec(ByteOrder byteOrder, int eleLength) {
        this.eleLength = eleLength;
        encoder = new WordArrayWithExactlyLenEncoder(byteOrder, eleLength);
        decoder = new WordArrayWithExactlyLenDecoder(byteOrder, eleLength);
    }

    public WordArrayWithExactlyLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
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
        return true;
    }

    @Override
    public int length() {
        return 2 * eleLength;
    }
}
