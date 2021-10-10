package com.hx.codec.tests;

import com.hx.codec.codec.collection.ByteCollectionWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.Collection;

/**
 * Test07ByteArrayCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:17
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test10ByteCollectionWithLenCodec extends Test00BaseTests {

    @Test
    public void test01ByteCollection() {
        int fixedLength = 0x10;
        ByteCollectionWithLenCodec protocol = new ByteCollectionWithLenCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        Collection<Integer> entity = Arrays.asList(0x01, 0x02, 0x03, 0x05);
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        Collection<Integer> decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("0000000401020305"), " unexpected value ");
        AssertUtils.state(Arrays.equals(decoded.toArray(new Integer[0]), entity.toArray(new Integer[0])), " unexpected value ");
        AssertUtils.state(buf.readerIndex() == (entity.size() + CodecUtils.lenBytes(ByteType.DWORD)), " unexpected value ");
        AssertUtils.state(buf.writerIndex() == (entity.size() + CodecUtils.lenBytes(ByteType.DWORD)), " unexpected value ");
    }

}
