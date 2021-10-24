package com.hx.codec.tests;

import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.DigestUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Test20DigestUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-16 11:05
 */
public class Test20DigestUtils extends Test00BaseTests {

    @Test
    public void test01CRC16_X16_15_2_1() {
        ByteBuf byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{0x00, 0x08, (byte) 0x84, 0x00, 0x00, 0x00, 0x05, 0x50});
        short crc16 = DigestUtils.crc16_X16_15_2_1(byteBuf);
        String crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});

        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("94e8"), "unexpected value");

        byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{(byte) 0xff, 0x09, (byte) 0x82, 0x11, 0x01, 0x0a, 0x01, 0x02, 0x03});
        crc16 = DigestUtils.crc16_X16_15_2_1(byteBuf);
        crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});
        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("0a6e"), "unexpected value");

    }

    @Test
    public void test01CRC16_X16_15_2_1_02() {

        ByteBuf byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{(byte) 0x7e, 0x00, (byte) 0x05, 0x60, 0x31, 0x32, 0x33});
        int crc16 = DigestUtils.crc16_X16_15_2_1(byteBuf);
        String crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});
        LOGGER.info(" crcHexStr : {} ", crcHexStr);
//        AssertUtils.state(crcHexStr.equals("0a6e"), "unexpected value");

        byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{(byte) 0x7e, 0x00, (byte) 0x05, 0x60, 0x31, 0x32, 0x33});
        crc16 = DigestUtils.crc16_X16_15_2_1_calc(byteBuf);
        crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});
        LOGGER.info(" crcHexStr : {} ", crcHexStr);
//        AssertUtils.state(crcHexStr.equals("0a6e"), "unexpected value");

    }

    @Test
    public void test02CRC16_CCITT() {
        ByteBuf byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{0x00, 0x08, (byte) 0x84, 0x00, 0x00, 0x00, 0x05, 0x50});
        short crc16 = DigestUtils.crc16_CCITT(byteBuf);
        String crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});

        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("d512"), "unexpected value");

        byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{(byte) 0xff, 0x09, (byte) 0x82, 0x11, 0x01, 0x0a, 0x01, 0x02, 0x03});
        crc16 = DigestUtils.crc16_CCITT(byteBuf);
        crcHexStr = ByteBufUtil.hexDump(new byte[]{(byte) ((crc16 & 0xff00) >> 8), (byte) (crc16 & 0x00ff)});
        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("fab3"), "unexpected value");

    }


    @Test
    public void test03Bcc() {
        ByteBuf byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{0x00, 0x08, (byte) 0x84, 0x00, 0x00, 0x00, 0x05, 0x50});
        byte bcc = DigestUtils.bcc(byteBuf);
        String crcHexStr = ByteBufUtil.hexDump(new byte[]{bcc});

        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("d9"), "unexpected value");

        byteBuf = Unpooled.buffer(0x20);
        byteBuf.writeBytes(new byte[]{(byte) 0xff, 0x09, (byte) 0x82, 0x11, 0x01, 0x0a, 0x01, 0x02, 0x03});
        bcc = DigestUtils.bcc(byteBuf);
        crcHexStr = ByteBufUtil.hexDump(new byte[]{bcc});
        LOGGER.info(" crcHexStr : {} ", crcHexStr);
        AssertUtils.state(crcHexStr.equals("6e"), "unexpected value");

    }

}
