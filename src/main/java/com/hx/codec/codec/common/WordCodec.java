package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.decoder.common.WordDecoder;
import com.hx.codec.encoder.common.WordEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.WORD_UNIT;

/**
 * WordProtocol (2 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordCodec extends AbstractCodec<Integer, Integer> {

    public WordCodec(ByteOrder byteOrder) {
        encoder = new WordEncoder(byteOrder);
        decoder = new WordDecoder(byteOrder);
    }

    public WordCodec() {
        encoder = new WordEncoder();
        decoder = new WordDecoder();
    }

    @Override
    public void encode(Integer entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public Integer decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return WORD_UNIT;
    }

}
