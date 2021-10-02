package com.hx.codec.tests;

import com.hx.codec.codec.string.Bcd8421StringCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * Test05StringProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 11:53
 */
public class Test06BcdStringCodec extends Test00BaseTests {

    public static void main(String[] args) {

        test01String();
        test01String02();

    }

    // test01String
    public static void test01String() {
        int fixedLength = 0x10;
        Bcd8421StringCodec protocol = new Bcd8421StringCodec(fixedLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13211111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("13211111111f0f0f0f0f0f0f0f0f0f0f"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    public static void test01String02() {
        int fixedLength = 0x06;
        Bcd8421StringCodec protocol = new Bcd8421StringCodec(fixedLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13298765077";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("13298765077f"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

}
