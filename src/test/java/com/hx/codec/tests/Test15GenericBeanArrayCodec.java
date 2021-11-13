package com.hx.codec.tests;

import com.hx.codec.codec.array.*;
import com.hx.codec.constants.ByteType;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.model.UpConnectReq;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test15GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 12:12
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test15GenericBeanArrayCodec extends Test00BaseTests {

    @Test
    public void test01UpConnectReq() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanArrayCodec<UpConnectReq> codec = new GenericBeanArrayCodec<>(beanSchema);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(new UpConnectReq[]{
                entity, entity
        }, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq[] decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity[0].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[0].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[0].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[0].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[0].getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity[1].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[1].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[1].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[1].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[1].getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test02UpConnectReqWithLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanArrayWithLenCodec<UpConnectReq> codec = new GenericBeanArrayWithLenCodec<>(beanSchema, ByteType.DWORD);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(new UpConnectReq[]{
                entity, entity
        }, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq[] decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("0000000207070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity[0].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[0].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[0].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[0].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[0].getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity[1].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[1].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[1].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[1].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[1].getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test03UpConnectReqWithEleLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanArrayWithEleLenCodec<UpConnectReq> codec = new GenericBeanArrayWithEleLenCodec<>(beanSchema, 3);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(new UpConnectReq[]{
                entity, entity
        }, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq[] decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), " unexpected value ");
        AssertUtils.state(decodedEntity.length == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity[0].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[0].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[0].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[0].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[0].getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity[1].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[1].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[1].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[1].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[1].getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test04UpConnectReqWithExactlyLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanArrayWithExactlyLenCodec<UpConnectReq> codec = new GenericBeanArrayWithExactlyLenCodec<>(beanSchema, 2);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(new UpConnectReq[]{
                entity, entity
        }, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq[] decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(decodedEntity.length == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity[0].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[0].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[0].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[0].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[0].getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity[1].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[1].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[1].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[1].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[1].getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test05UpConnectReqWithFixedLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanArrayWithFixedLenCodec<UpConnectReq> codec = new GenericBeanArrayWithFixedLenCodec<>(beanSchema, 3 * beanSchema.getLength());

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(new UpConnectReq[]{
                entity, entity
        }, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq[] decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), " unexpected value ");
        AssertUtils.state(decodedEntity.length == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity[0].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[0].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[0].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[0].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[0].getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity[1].getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity[1].getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity[1].getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity[1].getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity[1].getMsgGnsscenterid()), " unexpected value ");
    }

}
