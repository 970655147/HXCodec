package com.hx.codec.tests;

import com.hx.codec.codec.collection.*;
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

import java.util.Arrays;
import java.util.List;

/**
 * Test15GenericBeanArrayCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 12:12
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test16GenericBeanCollectionCodec extends Test00BaseTests {

    @Test
    public void test01UpConnectReq() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCollectionCodec<UpConnectReq> codec = new GenericBeanCollectionCodec<>(beanSchema);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(Arrays.asList(entity, entity), encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        List<UpConnectReq> decodedEntity = (List<UpConnectReq>) codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(0).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(0).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(0).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(0).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(0).getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(1).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(1).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(1).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(1).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(1).getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test02UpConnectReqWithLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCollectionWithLenCodec<UpConnectReq> codec = new GenericBeanCollectionWithLenCodec<>(beanSchema, ByteType.DWORD);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(Arrays.asList(entity, entity), encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        List<UpConnectReq> decodedEntity = (List<UpConnectReq>) codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("0000000207070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(0).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(0).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(0).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(0).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(0).getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(1).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(1).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(1).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(1).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(1).getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test03UpConnectReqWithEleLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCollectionWithEleLenCodec<UpConnectReq> codec = new GenericBeanCollectionWithEleLenCodec<>(beanSchema, 3);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(Arrays.asList(entity, entity), encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        List<UpConnectReq> decodedEntity = (List<UpConnectReq>) codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), " unexpected value ");
        AssertUtils.state(decodedEntity.size() == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(0).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(0).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(0).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(0).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(0).getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(1).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(1).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(1).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(1).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(1).getMsgGnsscenterid()), " unexpected value ");
    }

    @Test
    public void test04UpConnectReqWithExactlyLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCollectionWithExactlyLenCodec<UpConnectReq> codec = new GenericBeanCollectionWithExactlyLenCodec<>(beanSchema, 2);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(Arrays.asList(entity, entity), encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        List<UpConnectReq> decodedEntity = (List<UpConnectReq>) codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(decodedEntity.size() == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(0).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(0).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(0).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(0).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(0).getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(1).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(1).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(1).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(1).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(1).getMsgGnsscenterid()), " unexpected value ");
    }


    @Test
    public void test05UpConnectReqWithFixedLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCollectionWithFixedLenCodec<UpConnectReq> codec = new GenericBeanCollectionWithFixedLenCodec<>(beanSchema, 3 * beanSchema.getLength());

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(Arrays.asList(entity, entity), encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        List<UpConnectReq> decodedEntity = (List<UpConnectReq>) codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"), " unexpected value ");
        AssertUtils.state(decodedEntity.size() == 2, " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(0).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(0).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(0).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(0).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(0).getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(entity.getUserid().equals(decodedEntity.get(1).getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.get(1).getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.get(1).getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.get(1).getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.get(1).getMsgGnsscenterid()), " unexpected value ");
    }

}
