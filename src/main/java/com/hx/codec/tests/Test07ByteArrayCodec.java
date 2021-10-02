package com.hx.codec.tests;

import com.hx.codec.codec.array.ByteArrayCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
public class Test07ByteArrayCodec extends Test00BaseTests {

    // Test07ByteArrayCodec
    public static void main(String[] args) {

        test01ByteArray();

    }

    // test01ByteArray
    public static void test01ByteArray() {
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

}
