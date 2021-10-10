package com.hx.codec.tests.codec;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.CodecConstants;
import io.netty.buffer.ByteBuf;

/**
 * MyStringCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 17:34
 */
public class MyStringCodec extends AbstractCodec<String, String> {

    private String padding = "0x0x123";

    @Override
    public void encode(String entity, ByteBuf buf) {
        String updated = entity + padding;
        buf.writeByte(updated.getBytes(CodecConstants.DEFAULT_CHARSET).length);
        buf.writeBytes(updated.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    @Override
    public String decode(ByteBuf buf) {
        int lenWithPadding = buf.readByte();
        byte[] bytesWithPadding = new byte[lenWithPadding];
        buf.readBytes(bytesWithPadding);

        int originalLen = lenWithPadding - padding.getBytes(CodecConstants.DEFAULT_CHARSET).length;
        return new String(bytesWithPadding, 0, originalLen);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 0;
    }

}
