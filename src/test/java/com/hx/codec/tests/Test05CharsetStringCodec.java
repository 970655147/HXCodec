package com.hx.codec.tests;

import com.hx.codec.codec.string.CharsetEncodingStringWithFixedLenCodec;
import com.hx.codec.codec.string.CharsetEncodingStringWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.constants.Constants;
import com.hx.codec.utils.AssertUtils;
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
public class Test05CharsetStringCodec extends Test00BaseTests {

    @Test
    public void test01String() {
        int fixedLength = 0x10;
        CharsetEncodingStringWithFixedLenCodec protocol = new CharsetEncodingStringWithFixedLenCodec(fixedLength);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "cafebabe";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("63616665626162650000000000000000"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01String02() {
        byte paddingByte = 0x07;
        int fixedLength = 0x10;
        CharsetEncodingStringWithFixedLenCodec protocol = new CharsetEncodingStringWithFixedLenCodec(paddingByte, fixedLength, Constants.CHARSET_UTF8);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "cafebabe";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("63616665626162650707070707070707"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01String03() {
        byte paddingByte = 0x07;
        int fixedLength = 0x10;
        CharsetEncodingStringWithFixedLenCodec protocol = new CharsetEncodingStringWithFixedLenCodec(paddingByte, true, fixedLength, Constants.CHARSET_UTF8);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "cafebabe";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("07070707070707076361666562616265"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test01String04() {
        byte paddingByte = 0x07;
        int fixedLength = 0x10;
        CharsetEncodingStringWithFixedLenCodec protocol = new CharsetEncodingStringWithFixedLenCodec(paddingByte, true, fixedLength, Constants.CHARSET_UTF8);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "你好";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("07070707070707070707e4bda0e5a5bd"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == fixedLength, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == fixedLength, " unexpected value ");
    }

    @Test
    public void test02String01() {
        byte paddingByte = 0x07;
        int fixedLength = 0x10;
        CharsetEncodingStringWithLenCodec protocol = new CharsetEncodingStringWithLenCodec(ByteType.BYTE);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "你好";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("04c4e3bac3"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.getBytes(CodecConstants.DEFAULT_CHARSET).length + 1), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.getBytes(CodecConstants.DEFAULT_CHARSET).length + 1), " unexpected value ");
    }

    @Test
    public void test02String02() {
        byte paddingByte = 0x07;
        int fixedLength = 0x10;
        CharsetEncodingStringWithLenCodec protocol = new CharsetEncodingStringWithLenCodec(ByteType.QWORD);
        ByteBuf buf = Unpooled.buffer(0x10);
        String entity = "你好";
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        String decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000000000004c4e3bac3"), " unexpected value ");
        AssertUtils.state(decoded.equals(entity), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.getBytes(CodecConstants.DEFAULT_CHARSET).length + 8), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.getBytes(CodecConstants.DEFAULT_CHARSET).length + 8), " unexpected value ");
    }


}
