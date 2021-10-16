package com.hx.codec.tests;

import com.hx.codec.codec.string.Bcd8421StringWithExactlyLenCodec;
import com.hx.codec.codec.string.Bcd8421StringWithFixedLenCodec;
import com.hx.codec.codec.string.Bcd8421StringWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test05StringProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 11:53
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test06BcdStringCodec extends Test00BaseTests {

    @Test
    public void test01StringWithFixedLen() {
        int fixedLength = 0x10;
        Bcd8421StringWithFixedLenCodec protocol = new Bcd8421StringWithFixedLenCodec(fixedLength);
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

    @Test
    public void test01StringWithFixedLen02() {
        int fixedLength = 0x06;
        Bcd8421StringWithFixedLenCodec protocol = new Bcd8421StringWithFixedLenCodec(fixedLength);
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

    @Test
    public void test02StringWithExactlyLen() {
        int eleLength = 0x06;
        Bcd8421StringWithExactlyLenCodec protocol = new Bcd8421StringWithExactlyLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "132111111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("132111111111"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == eleLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == eleLength, " unexpected value ");
    }

    @Test
    public void test02StringWithExactlyLen02() {
        int eleLength = 0x06;
        Bcd8421StringWithExactlyLenCodec protocol = new Bcd8421StringWithExactlyLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "132987650771";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("132987650771"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == eleLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == eleLength, " unexpected value ");
    }

    @Test
    public void test03StringWithLen() {
        int fixedLength = 0x06;
        Bcd8421StringWithLenCodec protocol = new Bcd8421StringWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13211111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000000000000613211111111f"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test03StringWithLen02() {
        int fixedLength = 0x06;
        Bcd8421StringWithLenCodec protocol = new Bcd8421StringWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "132987650771";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000006132987650771"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }


}
