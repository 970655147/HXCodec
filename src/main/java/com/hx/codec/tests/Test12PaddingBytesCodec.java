package com.hx.codec.tests;

import com.hx.codec.codec.common.PaddingBytesCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
public class Test12PaddingBytesCodec extends Test00BaseTests {

    // Test07ByteArrayCodec
    public static void main(String[] args) {

        test01PaddingByte();
        test01PaddingByte02();

    }

    // test01PaddingByte
    public static void test01PaddingByte() {
        int fixedLength = 0x10;
        PaddingBytesCodec protocol = new PaddingBytesCodec(0x10);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "dummy";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Object decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000000"), " unexpected value ");
    }

    // test01PaddingByte
    public static void test01PaddingByte02() {
        int fixedLength = 0x10;
        byte[] paddingBytes = new byte[]{0x01, 0x02, 0x03};
        PaddingBytesCodec protocol = new PaddingBytesCodec(paddingBytes, 0x10);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "dummy";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Object decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("01020301020301020301020301020301"), " unexpected value ");
    }

}
