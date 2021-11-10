package com.hx.codec.tests;

import com.alibaba.fastjson.JSON;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.map.SchemaRegistryBasedMapWithLenCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.codec.MyStringCodec;
import com.hx.codec.tests.codec.factory.MyStringCodecFactory;
import com.hx.codec.tests.codec.registry.MyCodecRegistry;
import com.hx.codec.tests.model.EntityWithCodecRegistry;
import com.hx.codec.tests.model.EntityWithType;
import com.hx.codec.tests.model.EntityWithTypeList;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test22CodecRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-10 20:14
 */
public class Test22CodecRegistry extends Test00BaseTests {

    @Test
    public void test01CustomCodecRegistry() {

        GenericBeanSchema<EntityWithCodecRegistry> schema = new GenericBeanSchema<>(EntityWithCodecRegistry.class, 2019);
        GenericBeanCodec<EntityWithCodecRegistry> codec = new GenericBeanCodec<>(schema);

        EntityWithCodecRegistry entity = new EntityWithCodecRegistry();
        entity.setStrField("123");
        Map<String, Object> configMap = new LinkedHashMap<>();
        configMap.put("byte", 0x12);
        configMap.put("dword", 0x34567890);
        configMap.put("bcd8421WithLen", "13211111111");
        configMap.put("charsetEncodingWithFixedLen", "13211111111");
        entity.setConfigMap(configMap);

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        EntityWithCodecRegistry decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodecFactory() != null, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodecRegistry() != null, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodecFactory() instanceof MyStringCodecFactory, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodecRegistry() instanceof MyCodecRegistry, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("strField").getCodec() instanceof MyStringCodec, " unexpected value ");
        AssertUtils.state(schema.lookupFieldSchema("configMap").getCodec() instanceof SchemaRegistryBasedMapWithLenCodec, " unexpected value ");

        AssertUtils.state(encodedBufHexStr.equals("0a31323330783078313233040462797465120564776f7264345678900e62636438343231576974684c656e0613211111111f1b63686172736574456e636f64696e675769746846697865644c656e3133323131313131313131000000000000000000"), " unexpected value ");
        AssertUtils.state(JSON.toJSONString(entity).equals(JSON.toJSONString(decodedEntity)), " unexpected value ");

    }

    @Test
    public void test02ListWithCustomCodecFacotry() {

        GenericBeanSchema<EntityWithTypeList> schema = new GenericBeanSchema<>(EntityWithTypeList.class, 2019);
        GenericBeanCodec<EntityWithTypeList> codec = new GenericBeanCodec<>(schema);

        EntityWithTypeList entity = new EntityWithTypeList();
        EntityWithType type1 = new EntityWithType("dword", 0x1234);
        EntityWithType type2 = new EntityWithType("string", "jerry.x.he");
        EntityWithType type3 = new EntityWithType("string", "jerry");
        entity.setList(Arrays.asList(type1, type2, type3));

        ByteBuf encodedBuf = Unpooled.buffer(128);
        codec.encode(entity, encodedBuf);
        String encodedBufHexStr = ByteBufUtil.hexDump(encodedBuf.copy());
        EntityWithTypeList decodedEntity = codec.decode(encodedBuf);

        LOGGER.info(" encodedBufHexStr : " + encodedBufHexStr);
        AssertUtils.state(encodedBufHexStr.equals("030cfeff00640077006f00720064000012340efeff0073007400720069006e00670a6a657272792e782e68650efeff0073007400720069006e0067056a65727279"), " unexpected value ");
        AssertUtils.state(JSON.toJSONString(entity).equals(JSON.toJSONString(decodedEntity)), " unexpected value ");

    }


}
