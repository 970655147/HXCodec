package com.hx.codec.codec.string;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.decoder.string.Bcd8421StringWithLenDecoder;
import com.hx.codec.encoder.string.Bcd8421StringWithLenEncoder;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteProtocol (1 byte)
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class Bcd8421StringWithLenCodec extends AbstractCodec<String, String> {

    private ByteType lenByteTpe;

    public Bcd8421StringWithLenCodec(ByteOrder byteOrder, ByteType lenByteTpe, byte paddingByte) {
        this.lenByteTpe = lenByteTpe;
        encoder = new Bcd8421StringWithLenEncoder(byteOrder, lenByteTpe, paddingByte);
        decoder = new Bcd8421StringWithLenDecoder(byteOrder, lenByteTpe, paddingByte);
    }

    public Bcd8421StringWithLenCodec(ByteOrder byteOrder, ByteType lenByteTpe) {
        this(byteOrder, lenByteTpe, DEFAULT_BCD8421_PADDING);
    }

    public Bcd8421StringWithLenCodec(ByteType lenByteTpe) {
        this(DEFAULT_BYTE_ORDER, lenByteTpe);
    }

    @Override
    public void encode(String entity, ByteBuf buf) {
        encoder.encode(entity, buf);
    }

    @Override
    public String decode(ByteBuf buf) {
        return decoder.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 32;
    }
}
