package com.hx.codec.tests;

import com.hx.codec.codec.common.QWordCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test01QWordProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:25
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test04QWordCodec extends Test00BaseTests {

    // Test01QWordProtocol
    public void main(String[] args) {

        test01SingedQWord();
        test01SingedQWord02();

    }

    @Test
    public void test01SingedQWord() {
        QWordCodec protocol = new QWordCodec();
        ByteBuf buf = Unpooled.buffer(0x10);
        long entity = 23;
        protocol.encode(entity, buf);
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
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
        String encodedHexStr = ByteBufUtil.hexDump(buf.copy());
        long decoded = protocol.decode(buf);

        LOGGER.info(" encodedHexStr : {} ", encodedHexStr);
        AssertUtils.state(encodedHexStr.equals("ffffffffffffffe9"), " unexpected value ");
        AssertUtils.state(decoded == entity, " unexpected value ");
        AssertUtils.state(buf.readerIndex() == 8, " unexpected value ");
        AssertUtils.state(buf.writerIndex() == 8, " unexpected value ");
    }


}
