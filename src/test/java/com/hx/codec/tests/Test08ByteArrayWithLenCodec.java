package com.hx.codec.tests;

import com.hx.codec.codec.array.ByteArrayWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.CodecUtils;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.util.Arrays;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
public class Test08ByteArrayWithLenCodec extends Test00BaseTests {

    @Test
    public void test01ByteArray() {
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

}
