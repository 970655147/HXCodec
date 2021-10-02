package com.hx.codec.tests;

import com.hx.codec.codec.common.ByteCodec;
import com.hx.codec.codec.common.UnsignedByteCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

/**
 * Test01ByteProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
public class Test01ByteCodec extends Test00BaseTests {

    // Test01ByteProtocol
    public static void main(String[] args) {

        test01SingedByte();
        test01SingedByte02();

        test02UnsingedByte();
        test02UnsingedByte02();

    }

    // test01SingedByte
    public static void test01SingedByte() {
        ByteCodec protocol = new ByteCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("17"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 1, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 1, " unexpected value ");
    }

    public static void test01SingedByte02() {
        ByteCodec protocol = new ByteCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("e9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 1, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 1, " unexpected value ");
    }

    public static void test02UnsingedByte() {
        UnsignedByteCodec protocol = new UnsignedByteCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("17"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 1, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 1, " unexpected value ");
    }

    public static void test02UnsingedByte02() {
        UnsignedByteCodec protocol = new UnsignedByteCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("e9"), " unexpected value ");
        AssertUtils.state(decoded == (256 + entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 1, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 1, " unexpected value ");
    }


}
