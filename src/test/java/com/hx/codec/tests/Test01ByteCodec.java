package com.hx.codec.tests;

import com.hx.codec.codec.array.ByteArrayCodec;
import com.hx.codec.codec.array.ByteArrayWithEleLenCodec;
import com.hx.codec.codec.array.ByteArrayWithFixedLenCodec;
import com.hx.codec.codec.array.ByteArrayWithLenCodec;
import com.hx.codec.codec.collection.ByteCollectionCodec;
import com.hx.codec.codec.collection.ByteCollectionWithEleLenCodec;
import com.hx.codec.codec.collection.ByteCollectionWithFixedLenCodec;
import com.hx.codec.codec.collection.ByteCollectionWithLenCodec;
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
import java.util.List;

import static com.hx.codec.constants.CodecConstants.BYTE_UNIT;

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
        AssertUtils.state(buf.readerIndex() == (entity.length * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * BYTE_UNIT), " unexpected value ");
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
        AssertUtils.state(buf.readerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
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
        AssertUtils.state(buf.readerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
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
        AssertUtils.state(buf.writerIndex() == ((eleLength * BYTE_UNIT) * BYTE_UNIT), " unexpected value ");
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
        AssertUtils.state(buf.readerIndex() == (entity.length * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
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
        AssertUtils.state(buf.readerIndex() == (entity.length * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test24ByteArrayWithFixedLen() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteArrayWithFixedLenCodec protocol = new ByteArrayWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0102030500"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24ByteArrayWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteArrayWithFixedLenCodec protocol = new ByteArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0001020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test24ByteArrayWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteArrayWithFixedLenCodec protocol = new ByteArrayWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e01020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
    }

    // ------------------------------------------ byte collection methods ------------------------------------------

    @Test
    public void test31ByteCollection() {
        int fixedLength = 0x10;
        ByteCollectionCodec protocol = new ByteCollectionCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("01020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * BYTE_UNIT), " unexpected value ");
    }

    @Test
    public void test32ByteCollectionWithEleLen() {
        int eleLength = 0x5;
        ByteCollectionWithEleLenCodec protocol = new ByteCollectionWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0102030500"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
    }

    @Test
    public void test32ByteCollectionWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        ByteCollectionWithEleLenCodec protocol = new ByteCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0001020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
    }

    @Test
    public void test32ByteCollectionWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        ByteCollectionWithEleLenCodec protocol = new ByteCollectionWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e01020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * BYTE_UNIT), " unexpected value ");
    }

    @Test
    public void test33ByteCollectionWithLen() {
        int fixedLength = 0x10;
        ByteCollectionWithLenCodec protocol = new ByteCollectionWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000401020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test33ByteCollectionWithLen02LenByteType() {
        int fixedLength = 0x10;
        ByteCollectionWithLenCodec protocol = new ByteCollectionWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000000000000401020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() * BYTE_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

    @Test
    public void test34ByteCollectionWithFixedLen() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteCollectionWithFixedLenCodec protocol = new ByteCollectionWithFixedLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0102030500"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34ByteCollectionWithFixedLen02PaddingFirst() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteCollectionWithFixedLenCodec protocol = new ByteCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0001020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }

    @Test
    public void test34ByteCollectionWithFixedLen03PaddingByte() {
        int eleLength = 0x5 * BYTE_UNIT;
        ByteCollectionWithFixedLenCodec protocol = new ByteCollectionWithFixedLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        List<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        List<Integer> decoded = (List<Integer>) protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e01020305"), " unexpected value ");
        AssertUtils.state(listEquals(decoded, entity), " unexpected value ");
    }
    
    // ------------------------------------------ assist methods ------------------------------------------

    /**
     * listEquals
     *
     * @param list1 list1
     * @param list2 list2
     * @return boolean
     * @author Jerry.X.He
     * @date 2021-11-12 22:56
     */
    public static <T> boolean listEquals(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
