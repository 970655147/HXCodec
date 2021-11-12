package com.hx.codec.tests;

import com.hx.codec.codec.array.ByteArrayCodec;
import com.hx.codec.codec.array.ByteArrayWithEleLenCodec;
import com.hx.codec.codec.array.ByteArrayWithLenCodec;
import com.hx.codec.codec.common.ByteCodec;
import com.hx.codec.codec.common.UnsignedByteCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Test01ByteProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test01ByteCodec extends Test00BaseTests {

    // ------------------------------------------ byte methods ------------------------------------------

    @Test
    public void test01SingedByte() {
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

    @Test
    public void test01SingedByte02() {
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

    @Test
    public void test02UnsingedByte() {
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

    @Test
    public void test02UnsingedByte02() {
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

    // ------------------------------------------ byte array methods ------------------------------------------

    @Test
    public void test21ByteArray() {
        int fixedLength = 0x10;
        ByteArrayCodec protocol = new ByteArrayCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("01020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == entity.length, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == entity.length, " unexpected value ");
    }

    @Test
    public void test22ByteArrayWithEleLen() {
        int eleLength = 0x5;
        ByteArrayWithEleLenCodec protocol = new ByteArrayWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0102030500"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == eleLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == eleLength, " unexpected value ");
    }

    @Test
    public void test22ByteArrayWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        ByteArrayWithEleLenCodec protocol = new ByteArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0001020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == eleLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == eleLength, " unexpected value ");
    }

    @Test
    public void test22ByteArrayWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        ByteArrayWithEleLenCodec protocol = new ByteArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e01020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == eleLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == eleLength, " unexpected value ");
    }

    @Test
    public void test23ByteArrayWithLen() {
        int fixedLength = 0x10;
        ByteArrayWithLenCodec protocol = new ByteArrayWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000401020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == entity.length + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == entity.length + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test23ByteArrayWithLen02LenByteType() {
        int fixedLength = 0x10;
        ByteArrayWithLenCodec protocol = new ByteArrayWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000000000000401020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == entity.length + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == entity.length + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

}
