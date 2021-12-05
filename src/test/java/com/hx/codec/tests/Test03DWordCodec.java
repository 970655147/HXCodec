package com.hx.codec.tests;

import com.hx.codec.codec.array.DWordArrayCodec;
import com.hx.codec.codec.array.DWordArrayWithEleLenCodec;
import com.hx.codec.codec.array.DWordArrayWithFixedLenCodec;
import com.hx.codec.codec.array.DWordArrayWithLenCodec;
import com.hx.codec.codec.collection.DWordCollectionCodec;
import com.hx.codec.codec.collection.DWordCollectionWithEleLenCodec;
import com.hx.codec.codec.collection.DWordCollectionWithFixedLenCodec;
import com.hx.codec.codec.collection.DWordCollectionWithLenCodec;
import com.hx.codec.codec.common.DWordCodec;
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

import static com.hx.codec.constants.CodecConstants.DWORD_UNIT;
import static com.hx.codec.tests.Test01ByteCodec.listEquals;

/**
 * Test01DWordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test03DWordCodec extends Test00BaseTests {

    // ------------------------------------------ dword array methods ------------------------------------------

    @Test
    public void test01SingedDWord() {
        DWordCodec protocol = new DWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000017"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 4, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 4, " unexpected value ");
    }

    @Test
    public void test01SingedDWord02() {
        DWordCodec protocol = new DWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        int entity = -23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        int decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffffffe9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 4, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 4, " unexpected value ");
    }

    // ------------------------------------------ dword array methods ------------------------------------------

    @Test
    public void test21DWordArray() {
        int fixedLength = 0x10;
        DWordArrayCodec protocol = new DWordArrayCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22DWordArrayWithEleLen() {
        int eleLength = 0x5;
        DWordArrayWithEleLenCodec protocol = new DWordArrayWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000100000002000000030000000500000000"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22DWordArrayWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        DWordArrayWithEleLenCodec protocol = new DWordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22DWordArrayWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        DWordArrayWithEleLenCodec protocol = new DWordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test23DWordArrayWithLen() {
        int fixedLength = 0x10;
        DWordArrayWithLenCodec protocol = new DWordArrayWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000400000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test23DWordArrayWithLen02LenByteType() {
        int fixedLength = 0x10;
        DWordArrayWithLenCodec protocol = new DWordArrayWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000000000000400000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test24DWordArrayWithFixedLen() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordArrayWithFixedLenCodec protocol = new DWordArrayWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000100000002000000030000000500000000"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24DWordArrayWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordArrayWithFixedLenCodec protocol = new DWordArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24DWordArrayWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordArrayWithFixedLenCodec protocol = new DWordArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    // ------------------------------------------ dword collection methods ------------------------------------------

    @Test
    public void test31DWordCollection() {
        int fixedLength = 0x10;
        DWordCollectionCodec protocol = new DWordCollectionCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32DWordCollectionWithEleLen() {
        int eleLength = 0x5;
        DWordCollectionWithEleLenCodec protocol = new DWordCollectionWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000100000002000000030000000500000000"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32DWordCollectionWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        DWordCollectionWithEleLenCodec protocol = new DWordCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test32DWordCollectionWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        DWordCollectionWithEleLenCodec protocol = new DWordCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * DWORD_UNIT), " unexpected value ");
    }

    @Test
    public void test33DWordCollectionWithLen() {
        int fixedLength = 0x10;
        DWordCollectionWithLenCodec protocol = new DWordCollectionWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000400000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test33DWordCollectionWithLen02LenByteType() {
        int fixedLength = 0x10;
        DWordCollectionWithLenCodec protocol = new DWordCollectionWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000000000000400000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * DWORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test34DWordCollectionWithFixedLen() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordCollectionWithFixedLenCodec protocol = new DWordCollectionWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000100000002000000030000000500000000"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34DWordCollectionWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordCollectionWithFixedLenCodec protocol = new DWordCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34DWordCollectionWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * DWORD_UNIT;
        DWordCollectionWithFixedLenCodec protocol = new DWordCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf);
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e7e7e00000001000000020000000300000005"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }


}
