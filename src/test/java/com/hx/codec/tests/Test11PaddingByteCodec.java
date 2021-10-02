package com.hx.codec.tests;

import com.hx.codec.codec.common.PaddingByteCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
public class Test11PaddingByteCodec extends Test00BaseTests {

    @Test
    public void test01PaddingByte() {
        int fixedLength = 0x10;
        PaddingByteCodec protocol = new PaddingByteCodec(0x10);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "dummy";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Object decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000000"), " unexpected value ");
    }

    @Test
    public void test01PaddingByte02() {
        int fixedLength = 0x10;
        PaddingByteCodec protocol = new PaddingByteCodec((byte) 0x07, 0x10);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "dummy";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Object decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("07070707070707070707070707070707"), " unexpected value ");
    }

}
