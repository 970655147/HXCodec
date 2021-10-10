package com.hx.codec.tests.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.DataType;
import com.hx.codec.tests.codec.factory.MyConfigMapCodecFactory;
import com.hx.codec.tests.codec.factory.MyStringCodecFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * EntityWithMap
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 12:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityWithCodecFactory {

    private String strField;
    private Map<UpConnectReq, UpConnectReq> configMap;

    @Field(dataType = DataType.CHARSET_ENCODING_WITH_LEN_STRING, name = "strField", lengthByteType = ByteType.BYTE, sort = 1, desc = "strField", version = {2011, 2019}
            , args = "{\"paddingFirst\":\"false\"}", codecFactoryClazz = MyStringCodecFactory.class)
    public String getStrField() {
        return strField;
    }

    public void setStrField(String strField) {
        this.strField = strField;
    }

    @Field(dataType = DataType.GENERIC_MAP_WITH_LEN, name = "configMap", lengthByteType = ByteType.BYTE, sort = 2, desc = "configMap", version = {2011, 2019}
            , codecFactoryClazz = MyConfigMapCodecFactory.class)
    public Map<UpConnectReq, UpConnectReq> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<UpConnectReq, UpConnectReq> configMap) {
        this.configMap = configMap;
    }
}
