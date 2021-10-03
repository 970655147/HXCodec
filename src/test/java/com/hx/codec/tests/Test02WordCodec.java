package com.hx.codec.tests;

import com.hx.codec.codec.common.UnsignedWordCodec;
import com.hx.codec.codec.common.WordCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test01WordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test02WordCodec extends Test00BaseTests {

    @Test
    public void test01SingedWord() {
        WordCodec protocol = new WordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0017"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 2, " unexpected value ");
    }

    @Test
    public void test01SingedWord02() {
        WordCodec protocol = new WordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffe9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 2, " unexpected value ");
    }

    @Test
    public void test02UnsingedWord() {
        UnsignedWordCodec protocol = new UnsignedWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0017"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 2, " unexpected value ");
    }

    @Test
    public void test02UnsingedWord02() {
        UnsignedWordCodec protocol = new UnsignedWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffe9"), " unexpected value ");
        AssertUtils.state(decoded == (256 + entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 2, " unexpected value ");
    }


}
