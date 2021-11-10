package com.hx.codec.tests.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.DataType;
import com.hx.codec.tests.codec.registry.EntityWithTypeCodecRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class EntityWithTypeList {

    // dword, string
    private List<EntityWithType> list;

    @Field(name = "list", sort = 1, dataType = DataType.SCHEMA_REGISTRY_BASED_COLLECTION_WITH_LEN, lengthByteType = ByteType.BYTE, desc = "list", version = {2011, 2019},
            codecRegistryClazz = EntityWithTypeCodecRegistry.class)
    public List<EntityWithType> getList() {
        return list;
    }

    public void setList(List<EntityWithType> list) {
        this.list = list;
    }
}
