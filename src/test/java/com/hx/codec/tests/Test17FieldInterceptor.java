package com.hx.codec.tests;

import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.model.GnssDataItem;
import com.hx.codec.tests.model.Jt809Header;
import com.hx.codec.tests.model.UpConnectReqWithVerifyFIeldInterceptor;
import com.hx.codec.tests.model.UpExgMsgRealLocation;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Test17FieldInterceptor
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 10:20
 */
public class Test17FieldInterceptor extends Test00BaseTests {

    @Test
    public void test01VerifyFieldValueFieldInterceptor() {
        GenericBeanSchema<UpConnectReqWithVerifyFIeldInterceptor> beanSchema = new GenericBeanSchema<>(UpConnectReqWithVerifyFIeldInterceptor.class, 2019);
        GenericBeanCodec<UpConnectReqWithVerifyFIeldInterceptor> codec = new GenericBeanCodec<>(beanSchema);

        UpConnectReqWithVerifyFIeldInterceptor entity = new UpConnectReqWithVerifyFIeldInterceptor();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        UpConnectReqWithVerifyFIeldInterceptor decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.getMsgGnsscenterid()), " unexpected value ");

    }


    @Test
    public void test02SubDataLengthFieldInterceptor() {
        GenericBeanSchema<Jt809Header> headerBeanSchema = new GenericBeanSchema<>(Jt809Header.class, 2019);
        GenericBeanCodec<Jt809Header> headerCodec = new GenericBeanCodec<>(headerBeanSchema);
        GenericBeanSchema<UpExgMsgRealLocation> entityBeanSchema = new GenericBeanSchema<>(UpExgMsgRealLocation.class, 2019);
        GenericBeanCodec<UpExgMsgRealLocation> entityCodec = new GenericBeanCodec<>(entityBeanSchema);
        GenericBeanSchema<GnssDataItem> gnssDataItemBeanSchema = new GenericBeanSchema<>(GnssDataItem.class, 2019);
        GenericBeanCodec<GnssDataItem> gnssDataItemCodec = new GenericBeanCodec<>(gnssDataItemBeanSchema);

        Jt809Header header = new Jt809Header();
        header.setMsgLength(98);
        header.setMsgSn(0x1);
        header.setMsgId(0x2);
        header.setMsgGNSSCenterId(0x3);
        header.setVersionFlag(new Integer[]{5, 0, 0});
        header.setEncryptFlag(0x0);
        header.setEncryptKey(0x5);
        header.setTime(0x6);
        header.setMsgId(0x4100);

        UpExgMsgRealLocation entity = new UpExgMsgRealLocation();
        entity.setVehicleNo("1");
        entity.setVehicleColor(0x01);
        GnssDataItem gnssDataItem = new GnssDataItem(3, new Integer[]{0x1, 0x02, 0x03, 0x04}, new Integer[]{0x1, 0x02, 0x03},
                4, 5, 6, 7, 8, 9, 10, 11, 12);
        entity.setGnssDataItem(gnssDataItem);
        entity.setSubDataType(0x4102);
        entity.setSubDataLength(0);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        headerCodec.encode(header, encodedBuf);
        entityCodec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        Jt809Header decodedHeader = headerCodec.decode(encodedBuf);
        UpExgMsgRealLocation decodedEntity = entityCodec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("000000620000000141000000000305000000000000050000000000000006310000000000000000000000000000000000000000014102000000240301020304010203000000040000000500060007000000080009000a0000000b0000000c"), " unexpected value ");
        AssertUtils.state(entity.getSubDataLength().equals(0), " unexpected value ");
        AssertUtils.state(decodedEntity.getSubDataLength().equals(gnssDataItemBeanSchema.getLength()), " unexpected value ");

    }

}
