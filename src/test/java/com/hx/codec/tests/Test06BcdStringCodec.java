package com.hx.codec.tests;

import com.hx.codec.codec.string.*;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

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
        AssertUtils.state(encodedHexStr.equals("13211111111fffffffffffffffffffff"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01StringWithFixedLen02() {
        int fixedLength = 0x06;
        Bcd8421StringWithFixedLenCodec protocol = new Bcd8421StringWithFixedLenCodec(fixedLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "1329876507";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("1329876507ff"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01StringWithFixedLen03PaddingFirst() {
        int fixedLength = 0x10;
        Bcd8421StringWithFixedLenCodec protocol = new Bcd8421StringWithFixedLenCodec(DEFAULT_BYTE_ORDER, fixedLength, DEFAULT_BCD8421_PADDING, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13211111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("fffffffffffffffffffff13211111111"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01StringWithFixedLen04PaddingFirst() {
        int fixedLength = 0x06;
        Bcd8421StringWithFixedLenCodec protocol = new Bcd8421StringWithFixedLenCodec(DEFAULT_BYTE_ORDER, fixedLength, DEFAULT_BCD8421_PADDING, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "1329876507";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ff1329876507"), " unexpected value ");
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

    @Test
    public void test04BigDecimalWithLen() {
        int fixedLength = 0x06;
        Bcd8421BigDecimalWithOneByteLenCodec protocol = new Bcd8421BigDecimalWithOneByteLenCodec((byte) 0x0);
        ByteBuf buf = Unpooled.buffer(0x10);
        BigDecimal entity = new BigDecimal("-123456.456123");
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        BigDecimal decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("36ff123456456123"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength + 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength + 2, " unexpected value ");
    }

    @Test
    public void test04BigDecimalWithLen02() {
        int fixedLength = 0x06;
        Bcd8421BigDecimalWithOneByteLenCodec protocol = new Bcd8421BigDecimalWithOneByteLenCodec((byte) 0x0);
        ByteBuf buf = Unpooled.buffer(0x10);
        BigDecimal entity = new BigDecimal("123456.45612");
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        BigDecimal decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("3500012345645612"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength + 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength + 2, " unexpected value ");
    }


    @Test
    public void test05BcdString() {
        Bcd8421StringCodec protocol = new Bcd8421StringCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13211111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("13211111111f"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
    }

    @Test
    public void test05BcdString02() {
        Bcd8421StringCodec protocol = new Bcd8421StringCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "1329876507";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("1329876507"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
    }


    @Test
    public void test05BcdString03PaddingFirst() {
        Bcd8421StringCodec protocol = new Bcd8421StringCodec(DEFAULT_BYTE_ORDER, (byte) 0xe, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "13211111111";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("e13211111111"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
    }

    @Test
    public void test05BcdString04PaddingFirst() {
        Bcd8421StringCodec protocol = new Bcd8421StringCodec(DEFAULT_BYTE_ORDER, (byte) 0xe, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "1329876507";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("1329876507"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
    }

}
