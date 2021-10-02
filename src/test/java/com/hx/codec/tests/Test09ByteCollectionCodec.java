package com.hx.codec.tests;

import com.hx.codec.codec.collection.ByteCollectionCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
public class Test09ByteCollectionCodec extends Test00BaseTests {

    @Test
    public void test01ByteCollection() {
        int fixedLength = 0x10;
        ByteCollectionCodec protocol = new ByteCollectionCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Collection<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Collection<Integer> decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("01020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded.toArray(new Integer[0]), entity.toArray(new Integer[0])), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == entity.size(), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == entity.size(), " unexpected value ");
    }

}
