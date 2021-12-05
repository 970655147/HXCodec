package com.hx.codec.tests;

import com.hx.codec.codec.array.QWordArrayCodec;
import com.hx.codec.codec.array.QWordArrayWithEleLenCodec;
import com.hx.codec.codec.array.QWordArrayWithFixedLenCodec;
import com.hx.codec.codec.array.QWordArrayWithLenCodec;
import com.hx.codec.codec.collection.QWordCollectionCodec;
import com.hx.codec.codec.collection.QWordCollectionWithEleLenCodec;
import com.hx.codec.codec.collection.QWordCollectionWithFixedLenCodec;
import com.hx.codec.codec.collection.QWordCollectionWithLenCodec;
import com.hx.codec.codec.common.QWordCodec;
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
import java.util.List;

import static com.hx.codec.constants.CodecConstants.QWORD_UNIT;
import static com.hx.codec.tests.Test01ByteCodec.listEquals;

/**
 * Test01QWordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test04QWordCodec extends Test00BaseTests {

    // ------------------------------------------ qword methods ------------------------------------------

    @Test
    public void test01SingedQWord() {
        QWordCodec protocol = new QWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        long entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        long decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000017"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 8, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 8, " unexpected value ");
    }

    @Test
    public void test01SingedQWord02() {
        QWordCodec protocol = new QWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        long entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        long decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffffffffffffffe9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 8, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 8, " unexpected value ");
    }

    // ------------------------------------------ qword array methods ------------------------------------------

    @Test
    public void test21QWordArray() {
        int fixedLength = 0x10;
        QWordArrayCodec protocol = new QWordArrayCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22QWordArrayWithEleLen() {
        int eleLength = 0x5;
        QWordArrayWithEleLenCodec protocol = new QWordArrayWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000010000000000000002000000000000000300000000000000050000000000000000"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22QWordArrayWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        QWordArrayWithEleLenCodec protocol = new QWordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22QWordArrayWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        QWordArrayWithEleLenCodec protocol = new QWordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e7e7e7e7e0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test23QWordArrayWithLen() {
        int fixedLength = 0x10;
        QWordArrayWithLenCodec protocol = new QWordArrayWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000040000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test23QWordArrayWithLen02LenByteType() {
        int fixedLength = 0x10;
        QWordArrayWithLenCodec protocol = new QWordArrayWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000040000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test24QWordArrayWithFixedLen() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordArrayWithFixedLenCodec protocol = new QWordArrayWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000010000000000000002000000000000000300000000000000050000000000000000"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24QWordArrayWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordArrayWithFixedLenCodec protocol = new QWordArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24QWordArrayWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordArrayWithFixedLenCodec protocol = new QWordArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Long[] entity = new Long[]{0x01L, 0x02L, 0x03L, 0x05L};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Long[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e7e7e7e7e0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }
    
    // ------------------------------------------ qword collection methods ------------------------------------------

    @Test
    public void test31QWordCollection() {
        int fixedLength = 0x10;
        QWordCollectionCodec protocol = new QWordCollectionCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32QWordCollectionWithEleLen() {
        int eleLength = 0x5;
        QWordCollectionWithEleLenCodec protocol = new QWordCollectionWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000010000000000000002000000000000000300000000000000050000000000000000"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32QWordCollectionWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        QWordCollectionWithEleLenCodec protocol = new QWordCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32QWordCollectionWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        QWordCollectionWithEleLenCodec protocol = new QWordCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e7e7e7e7e0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * QWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test33QWordCollectionWithLen() {
        int fixedLength = 0x10;
        QWordCollectionWithLenCodec protocol = new QWordCollectionWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000040000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test33QWordCollectionWithLen02LenByteType() {
        int fixedLength = 0x10;
        QWordCollectionWithLenCodec protocol = new QWordCollectionWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000040000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * QWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test34QWordCollectionWithFixedLen() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordCollectionWithFixedLenCodec protocol = new QWordCollectionWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000010000000000000002000000000000000300000000000000050000000000000000"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34QWordCollectionWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordCollectionWithFixedLenCodec protocol = new QWordCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000000000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34QWordCollectionWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * QWORD_UNIT;
        QWordCollectionWithFixedLenCodec protocol = new QWordCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Long> entity = Arrays.asList(0x01L, 0x02L, 0x03L, 0x05L);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Long> decoded = (List<Long>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e7e7e7e7e0000000000000001000000000000000200000000000000030000000000000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

}
