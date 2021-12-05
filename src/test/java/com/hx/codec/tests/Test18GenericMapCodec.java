package com.hx.codec.tests;

import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.map.GenericMapCodec;
import com.hx.codec.codec.string.CharsetEncodingStringWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.model.EntityWithMap;
import com.hx.codec.tests.model.UpConnectReq;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test14GenericBeanCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 14:31
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class Test18GenericMapCodec extends Test00BaseTests {

    @Test
    public void test01StringAndEntityMap() {
        CharsetEncodingStringWithLenCodec keyCodec = new CharsetEncodingStringWithLenCodec(ByteType.BYTE);
        GenericBeanSchema<UpConnectReq> valueSchema = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanCodec<UpConnectReq> valueCodec = new GenericBeanCodec<>(valueSchema);
        GenericMapCodec<String, UpConnectReq> codec = new GenericMapCodec<>(keyCodec, valueCodec);

        UpConnectReq upConnectReq = new UpConnectReq();
        upConnectReq.setDummyField("dummyField");
        upConnectReq.setUserid(0x1234);
        upConnectReq.setPassword("pass");
        upConnectReq.setDownLinkIp("127.0.0.2");
        upConnectReq.setDownLinkPort(0x7788);
        upConnectReq.setMsgGnsscenterid(0x1111);

        Map<String, UpConnectReq> entity = new LinkedHashMap<>();
        String mapKey = "xyz";
        entity.put(mapKey, upConnectReq);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        Map<String, UpConnectReq> decodedEntity = codec.decode(encodedBuf);
        UpConnectReq decodedUpConnectReq = decodedEntity.get(mapKey);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("0378797a07070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");
        AssertUtils.state(decodedEntity.size() == 1, " unexpected value ");
        AssertUtils.state(decodedUpConnectReq != null, " unexpected value ");
        AssertUtils.state(upConnectReq.getUserid().equals(decodedUpConnectReq.getUserid()), " unexpected value ");
        AssertUtils.state(upConnectReq.getPassword().equals(decodedUpConnectReq.getPassword()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkIp().equals(decodedUpConnectReq.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkPort().equals(decodedUpConnectReq.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(upConnectReq.getMsgGnsscenterid().equals(decodedUpConnectReq.getMsgGnsscenterid()), " unexpected value ");

    }


    @Test
    public void test02EntityWithMap() {
        GenericBeanSchema<EntityWithMap> schema = new GenericBeanSchema<>(EntityWithMap.class, 2019);
        GenericBeanCodec<EntityWithMap> codec = new GenericBeanCodec<>(schema);

        UpConnectReq upConnectReq = new UpConnectReq();
        upConnectReq.setDummyField("dummyField");
        upConnectReq.setUserid(0x1234);
        upConnectReq.setPassword("pass");
        upConnectReq.setDownLinkIp("127.0.0.2");
        upConnectReq.setDownLinkPort(0x7788);
        upConnectReq.setMsgGnsscenterid(0x1111);

        EntityWithMap entity = new EntityWithMap();
        entity.setStrField("123");
        Map<String, UpConnectReq> configMap = new LinkedHashMap<>();
        String mapKey = "xyz";
        configMap.put(mapKey, upConnectReq);
        entity.setConfigMap(configMap);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        EntityWithMap decodedEntity = codec.decode(encodedBuf);
        UpConnectReq decodedUpConnectReq = decodedEntity.getConfigMap().get(mapKey);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(entity.getStrField().equals(decodedEntity.getStrField()), " unexpected value ");
        AssertUtils.state(decodedEntity.getConfigMap().size() == 1, " unexpected value ");
        AssertUtils.state(decodedUpConnectReq != null, " unexpected value ");
        AssertUtils.state(upConnectReq.getUserid().equals(decodedUpConnectReq.getUserid()), " unexpected value ");
        AssertUtils.state(upConnectReq.getPassword().equals(decodedUpConnectReq.getPassword()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkIp().equals(decodedUpConnectReq.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkPort().equals(decodedUpConnectReq.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(upConnectReq.getMsgGnsscenterid().equals(decodedUpConnectReq.getMsgGnsscenterid()), " unexpected value ");

    }

}
