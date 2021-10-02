package com.hx.codec.tests;

import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.model.GnssDataItem;
import com.hx.codec.model.Jt809Header;
import com.hx.codec.model.UpConnectReq;
import com.hx.codec.model.UpExgMsgRealLocation;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

/**
 * Test14GenericBeanCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 14:31
 */
public class Test14GenericBeanCodec extends Test00BaseTests {

    @Test
    public void test01UpConnectReq() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCodec<UpConnectReq> codec = new GenericBeanCodec<>(beanSchema);

        UpConnectReq entity = new UpConnectReq();
        entity.setDummyField("dummyField");
        entity.setUserid(0x1234);
        entity.setPassword("pass");
        entity.setDownLinkIp("127.0.0.2");
        entity.setDownLinkPort(0x7788);
        entity.setMsgGnsscenterid(0x1111);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        UpConnectReq decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("07070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid() == decodedEntity.getUserid(), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort() == decodedEntity.getDownLinkPort(), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid() == decodedEntity.getMsgGnsscenterid(), " unexpected value ");

    }

    @Test
    public void test02UpExgMsgRealLocation() {
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
        entity.setSubDataLength(gnssDataItemCodec.length());

        ByteBuf encodedBuf = Unpooled.buffer(128);
        headerCodec.encode(header, encodedBuf);
        entityCodec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        Jt809Header decodedHeader = headerCodec.decode(encodedBuf);
        UpExgMsgRealLocation decodedEntity = entityCodec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("000000620000000141000000000305000000000000050000000000000006310000000000000000000000000000000000000000014102000000240301020304010203000000040000000500060007000000080009000a0000000b0000000c"), " unexpected value ");
        AssertUtils.state(header.getMsgLength() == decodedHeader.getMsgLength(), " unexpected value ");
        AssertUtils.state(header.getMsgSn() == decodedHeader.getMsgSn(), " unexpected value ");
        AssertUtils.state(header.getMsgId() == decodedHeader.getMsgId(), " unexpected value ");
        AssertUtils.state(header.getMsgGNSSCenterId() == decodedHeader.getMsgGNSSCenterId(), " unexpected value ");
        AssertUtils.state(header.getEncryptFlag() == decodedHeader.getEncryptFlag(), " unexpected value ");
        AssertUtils.state(header.getEncryptKey() == decodedHeader.getEncryptKey(), " unexpected value ");
        AssertUtils.state(entity.getVehicleNo().equals(decodedEntity.getVehicleNo()), " unexpected value ");
        AssertUtils.state(entity.getVehicleColor() == decodedEntity.getVehicleColor(), " unexpected value ");
        AssertUtils.state(entity.getSubDataType() == decodedEntity.getSubDataType(), " unexpected value ");
        AssertUtils.state(entity.getSubDataLength() == decodedEntity.getSubDataLength(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getEncrypt() == decodedEntity.getGnssDataItem().getEncrypt(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLon() == decodedEntity.getGnssDataItem().getLon(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLat() == decodedEntity.getGnssDataItem().getLat(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec1() == decodedEntity.getGnssDataItem().getVec1(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec2() == decodedEntity.getGnssDataItem().getVec2(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec3() == decodedEntity.getGnssDataItem().getVec3(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getDirection() == decodedEntity.getGnssDataItem().getDirection(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAltitude() == decodedEntity.getGnssDataItem().getAltitude(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getState() == decodedEntity.getGnssDataItem().getState(), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAlarm() == decodedEntity.getGnssDataItem().getAlarm(), " unexpected value ");

    }

}
