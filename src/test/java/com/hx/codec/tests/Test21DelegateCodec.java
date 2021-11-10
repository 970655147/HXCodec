package com.hx.codec.tests;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithLenCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.model.GnssDataItem;
import com.hx.codec.tests.model.Jt809Header;
import com.hx.codec.tests.model.UpConnectReq;
import com.hx.codec.tests.model.UpExgMsgRealLocation;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Test14GenericBeanCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 14:31
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test21DelegateCodec extends Test00BaseTests {

    @Test
    public void test01UpConnectReq() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        AbstractCodec<UpConnectReq, UpConnectReq> codec = new DelegateCodec<>(new GenericBeanCodec<>(beanSchema));

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
        AssertUtils.state(entity.getUserid().equals(decodedEntity.getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.getMsgGnsscenterid()), " unexpected value ");

    }

    @Test
    public void test01UpConnectReqWithLen() {
        GenericBeanSchema<UpConnectReq> beanSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        AbstractCodec<UpConnectReq, UpConnectReq> codec = new DelegateWithLenCodec<>(new GenericBeanCodec<>(beanSchema));

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
        AssertUtils.state(encodedBufHexStr.equals("0000003c07070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(entity.getUserid().equals(decodedEntity.getUserid()), " unexpected value ");
        AssertUtils.state(entity.getPassword().equals(decodedEntity.getPassword()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkIp().equals(decodedEntity.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(entity.getDownLinkPort().equals(decodedEntity.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(entity.getMsgGnsscenterid().equals(decodedEntity.getMsgGnsscenterid()), " unexpected value ");

    }

    @Test
    public void test02UpExgMsgRealLocation() {
        GenericBeanSchema<Jt809Header> headerBeanSchema = new GenericBeanSchema<>(Jt809Header.class, 2019);
        AbstractCodec<Jt809Header, Jt809Header> headerCodec = new DelegateCodec<>(new GenericBeanCodec<>(headerBeanSchema));
        GenericBeanSchema<UpExgMsgRealLocation> entityBeanSchema = new GenericBeanSchema<>(UpExgMsgRealLocation.class, 2019);
        AbstractCodec<UpExgMsgRealLocation, UpExgMsgRealLocation> entityCodec = new DelegateCodec<>(new GenericBeanCodec<>(entityBeanSchema));
        GenericBeanSchema<GnssDataItem> gnssDataItemBeanSchema = new GenericBeanSchema<>(GnssDataItem.class, 2019);
        AbstractCodec<GnssDataItem, GnssDataItem> gnssDataItemCodec = new DelegateCodec<>(new GenericBeanCodec<>(gnssDataItemBeanSchema));

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
        AssertUtils.state(header.getMsgLength().equals(decodedHeader.getMsgLength()), " unexpected value ");
        AssertUtils.state(header.getMsgSn().equals(decodedHeader.getMsgSn()), " unexpected value ");
        AssertUtils.state(header.getMsgId().equals(decodedHeader.getMsgId()), " unexpected value ");
        AssertUtils.state(header.getMsgGNSSCenterId().equals(decodedHeader.getMsgGNSSCenterId()), " unexpected value ");
        AssertUtils.state(header.getEncryptFlag().equals(decodedHeader.getEncryptFlag()), " unexpected value ");
        AssertUtils.state(header.getEncryptKey().equals(decodedHeader.getEncryptKey()), " unexpected value ");
        AssertUtils.state(entity.getVehicleNo().equals(decodedEntity.getVehicleNo()), " unexpected value ");
        AssertUtils.state(entity.getVehicleColor().equals(decodedEntity.getVehicleColor()), " unexpected value ");
        AssertUtils.state(entity.getSubDataType().equals(decodedEntity.getSubDataType()), " unexpected value ");
        AssertUtils.state(entity.getSubDataLength().equals(decodedEntity.getSubDataLength()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getEncrypt().equals(decodedEntity.getGnssDataItem().getEncrypt()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLon().equals(decodedEntity.getGnssDataItem().getLon()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLat().equals(decodedEntity.getGnssDataItem().getLat()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec1().equals(decodedEntity.getGnssDataItem().getVec1()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec2().equals(decodedEntity.getGnssDataItem().getVec2()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec3().equals(decodedEntity.getGnssDataItem().getVec3()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getDirection().equals(decodedEntity.getGnssDataItem().getDirection()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAltitude().equals(decodedEntity.getGnssDataItem().getAltitude()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getState().equals(decodedEntity.getGnssDataItem().getState()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAlarm().equals(decodedEntity.getGnssDataItem().getAlarm()), " unexpected value ");

    }

    @Test
    public void test02UpExgMsgRealLocationWithLen() {
        GenericBeanSchema<Jt809Header> headerBeanSchema = new GenericBeanSchema<>(Jt809Header.class, 2019);
        AbstractCodec<Jt809Header, Jt809Header> headerCodec = new DelegateWithLenCodec<>(new GenericBeanCodec<>(headerBeanSchema));
        GenericBeanSchema<UpExgMsgRealLocation> entityBeanSchema = new GenericBeanSchema<>(UpExgMsgRealLocation.class, 2019);
        AbstractCodec<UpExgMsgRealLocation, UpExgMsgRealLocation> entityCodec = new DelegateWithLenCodec<>(new GenericBeanCodec<>(entityBeanSchema));
        GenericBeanSchema<GnssDataItem> gnssDataItemBeanSchema = new GenericBeanSchema<>(GnssDataItem.class, 2019);
        AbstractCodec<GnssDataItem, GnssDataItem> gnssDataItemCodec = new GenericBeanCodec<>(gnssDataItemBeanSchema);
        AbstractCodec<GnssDataItem, GnssDataItem> gnssDataItemDelegateCodec = new DelegateWithLenCodec<>(gnssDataItemCodec);

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
        AssertUtils.state(encodedBufHexStr.equals("0000001e00000062000000014100000000030500000000000005000000000000000600000040310000000000000000000000000000000000000000014102000000240301020304010203000000040000000500060007000000080009000a0000000b0000000c"), " unexpected value ");
        AssertUtils.state(header.getMsgLength().equals(decodedHeader.getMsgLength()), " unexpected value ");
        AssertUtils.state(header.getMsgSn().equals(decodedHeader.getMsgSn()), " unexpected value ");
        AssertUtils.state(header.getMsgId().equals(decodedHeader.getMsgId()), " unexpected value ");
        AssertUtils.state(header.getMsgGNSSCenterId().equals(decodedHeader.getMsgGNSSCenterId()), " unexpected value ");
        AssertUtils.state(header.getEncryptFlag().equals(decodedHeader.getEncryptFlag()), " unexpected value ");
        AssertUtils.state(header.getEncryptKey().equals(decodedHeader.getEncryptKey()), " unexpected value ");
        AssertUtils.state(entity.getVehicleNo().equals(decodedEntity.getVehicleNo()), " unexpected value ");
        AssertUtils.state(entity.getVehicleColor().equals(decodedEntity.getVehicleColor()), " unexpected value ");
        AssertUtils.state(entity.getSubDataType().equals(decodedEntity.getSubDataType()), " unexpected value ");
        AssertUtils.state(entity.getSubDataLength().equals(decodedEntity.getSubDataLength()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getEncrypt().equals(decodedEntity.getGnssDataItem().getEncrypt()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLon().equals(decodedEntity.getGnssDataItem().getLon()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getLat().equals(decodedEntity.getGnssDataItem().getLat()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec1().equals(decodedEntity.getGnssDataItem().getVec1()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec2().equals(decodedEntity.getGnssDataItem().getVec2()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getVec3().equals(decodedEntity.getGnssDataItem().getVec3()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getDirection().equals(decodedEntity.getGnssDataItem().getDirection()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAltitude().equals(decodedEntity.getGnssDataItem().getAltitude()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getState().equals(decodedEntity.getGnssDataItem().getState()), " unexpected value ");
        AssertUtils.state(entity.getGnssDataItem().getAlarm().equals(decodedEntity.getGnssDataItem().getAlarm()), " unexpected value ");

    }

}
