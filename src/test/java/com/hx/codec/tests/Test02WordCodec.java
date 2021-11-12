package com.hx.codec.tests;

import com.hx.codec.codec.array.WordArrayCodec;
import com.hx.codec.codec.array.WordArrayWithEleLenCodec;
import com.hx.codec.codec.array.WordArrayWithLenCodec;
import com.hx.codec.codec.common.UnsignedWordCodec;
import com.hx.codec.codec.common.WordCodec;
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

import static com.hx.codec.constants.CodecConstants.WORD_UNIT;

/**
 * Test01WordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test02WordCodec extends Test00BaseTests {

    // ------------------------------------------ word methods ------------------------------------------

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
        AssertUtils.state(decoded == (65536 + entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 2, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 2, " unexpected value ");
    }

    // ------------------------------------------ word array methods ------------------------------------------

    @Test
    public void test21WordArray() {
        int fixedLength = 0x10;
        WordArrayCodec protocol = new WordArrayCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0001000200030005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * WORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * WORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22WordArrayWithEleLen() {
        int eleLength = 0x5;
        WordArrayWithEleLenCodec protocol = new WordArrayWithEleLenCodec(eleLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00010002000300050000"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22WordArrayWithEleLen02PaddingFirst() {
        int eleLength = 0x5;
        WordArrayWithEleLenCodec protocol = new WordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x00, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000001000200030005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
    }

    @Test
    public void test22WordArrayWithEleLen03PaddingByte() {
        int eleLength = 0x5;
        WordArrayWithEleLenCodec protocol = new WordArrayWithEleLenCodec(ByteOrder.BIG_ENDIAN, eleLength, (byte) 0x7e, true);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("7e7e0001000200030005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (eleLength * WORD_UNIT), " unexpected value ");
    }

    @Test
    public void test23WordArrayWithLen() {
        int fixedLength = 0x10;
        WordArrayWithLenCodec protocol = new WordArrayWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("000000040001000200030005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * WORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * WORD_UNIT) + CodecUtils.lenBytes(ByteType.DWORD), " unexpected value ");
    }


    @Test
    public void test23WordArrayWithLen02LenByteType() {
        int fixedLength = 0x10;
        WordArrayWithLenCodec protocol = new WordArrayWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        Integer[] entity = new Integer[]{0x01, 0x02, 0x03, 0x05};
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Integer[] decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("00000000000000040001000200030005"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded, entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.length * WORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.length * WORD_UNIT) + CodecUtils.lenBytes(ByteType.QWORD), " unexpected value ");
    }

}
