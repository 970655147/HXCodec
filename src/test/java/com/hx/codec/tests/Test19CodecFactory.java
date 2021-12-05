package com.hx.codec.tests;

import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.codec.MyConfigMapCodec;
import com.hx.codec.tests.codec.MyStringCodec;
import com.hx.codec.tests.codec.factory.MyConfigMapCodecFactory;
import com.hx.codec.tests.codec.factory.MyStringCodecFactory;
import com.hx.codec.tests.model.EntityWithCodecFactory;
import com.hx.codec.tests.model.EntityWithType;
import com.hx.codec.tests.model.EntityWithTypeList;
import com.hx.codec.tests.model.UpConnectReq;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test19CodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 17:30
 */
public class Test19CodecFactory extends Test00BaseTests {

    @Test
    public void test01CustomCodecFactory() {

        GenericBeanSchema<EntityWithCodecFactory> schema = new GenericBeanSchema<>(EntityWithCodecFactory.class, 2019);
        GenericBeanCodec<EntityWithCodecFactory> codec = new GenericBeanCodec<>(schema);

        UpConnectReq upConnectReq = new UpConnectReq();
        upConnectReq.setDummyField("dummyField");
        upConnectReq.setUserid(0x1234);
        upConnectReq.setPassword("pass");
        upConnectReq.setDownLinkIp("127.0.0.2");
        upConnectReq.setDownLinkPort(0x7788);
        upConnectReq.setMsgGnsscenterid(0x1111);

        EntityWithCodecFactory entity = new EntityWithCodecFactory();
        entity.setStrField("123");
        Map<UpConnectReq, UpConnectReq> configMap = new LinkedHashMap<>();
        configMap.put(upConnectReq, upConnectReq);
        entity.setConfigMap(configMap);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf);
        EntityWithCodecFactory decodedEntity = codec.decode(encodedBuf);
        UpConnectReq decodedUpConnectReqKey = decodedEntity.getConfigMap().keySet().iterator().next();
        UpConnectReq decodedUpConnectReqValue = decodedEntity.getConfigMap().get(decodedEntity.getConfigMap().keySet().iterator().next());

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodecFactory() != null, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodecFactory() != null, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodecFactory() instanceof MyStringCodecFactory, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodecFactory() instanceof MyConfigMapCodecFactory, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodec() instanceof MyStringCodec, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodec() instanceof MyConfigMapCodec, " unexpected value ");

        AssertUtils.state(entity.getStrField().equals(decodedEntity.getStrField()), " unexpected value ");
        AssertUtils.state(decodedEntity.getConfigMap().size() == 1, " unexpected value ");
        AssertUtils.state(decodedUpConnectReqValue != null, " unexpected value ");
        AssertUtils.state(upConnectReq.getUserid().equals(decodedUpConnectReqValue.getUserid()), " unexpected value ");
        AssertUtils.state(upConnectReq.getPassword().equals(decodedUpConnectReqValue.getPassword()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkIp().equals(decodedUpConnectReqValue.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkPort().equals(decodedUpConnectReqValue.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(upConnectReq.getMsgGnsscenterid().equals(decodedUpConnectReqValue.getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(decodedUpConnectReqKey != null, " unexpected value ");
        AssertUtils.state(upConnectReq.getUserid().equals(decodedUpConnectReqKey.getUserid()), " unexpected value ");
        AssertUtils.state(upConnectReq.getPassword().equals(decodedUpConnectReqKey.getPassword()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkIp().equals(decodedUpConnectReqKey.getDownLinkIp()), " unexpected value ");
        AssertUtils.state(upConnectReq.getDownLinkPort().equals(decodedUpConnectReqKey.getDownLinkPort()), " unexpected value ");
        AssertUtils.state(upConnectReq.getMsgGnsscenterid().equals(decodedUpConnectReqKey.getMsgGnsscenterid()), " unexpected value ");

        AssertUtils.state(encodedBufHexStr.equals("0a3132333078307831323307070707070707070707000012347061737300000000000011113132372e302e302e320000000000000000000000000000000000000000000000778807070707070707070707000012347061737300000000000011113132372e302e302e3200000000000000000000000000000000000000000000007788"), " unexpected value ");

    }

}
