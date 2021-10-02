package com.hx.codec.codec.array;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.array.WordArrayWithLenDecoder;
import com.hx.codec.encoder.array.WordArrayWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordArrayWithLenCodec extends AbstractCodec<Integer[], Integer[]> {

    public WordArrayWithLenCodec(ByteOrder byteOrder, ByteType lenByteType) {
        encoder = new WordArrayWithLenEncoder(byteOrder, lenByteType);
        decoder = new WordArrayWithLenDecoder(byteOrder, lenByteType);
    }

    public WordArrayWithLenCodec(ByteType lenByteType) {
        encoder = new WordArrayWithLenEncoder(lenByteType);
        decoder = new WordArrayWithLenDecoder(lenByteType);
    }

    public WordArrayWithLenCodec() {
        encoder = new WordArrayWithLenEncoder();
        decoder = new WordArrayWithLenDecoder();
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
        return 2;
    }
}
