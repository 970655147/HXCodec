package com.hx.codec.tests;

import com.hx.codec.codec.common.DWordCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * Test01DWordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
public class Test03DWordCodec extends Test00BaseTests {

    // Test01DWordProtocol
    public static void main(String[] args) {

        test01SingedDWord();
        test01SingedDWord02();


    }

    // test01SingedDWord
    public static void test01SingedDWord() {
        DWordCodec protocol = new DWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000017"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 4, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 4, " unexpected value ");
    }

    public static void test01SingedDWord02() {
        DWordCodec protocol = new DWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffffffe9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 4, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 4, " unexpected value ");
    }


}
