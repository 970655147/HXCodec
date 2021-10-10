package com.hx.codec.schema;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MapBasedSchemaRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 10:00
 */
public class MapBasedSchemaRegistry<T> implements SchemaRegistry<T> {

    // key2Schema
    protected Map<T, GenericBeanSchema> key2Schema = new LinkedHashMap<>();

    @Override
    public boolean registry(T key, GenericBeanSchema beanSchema) {
        key2Schema.put(key, beanSchema);
        return true;
    }

    @Override
    public GenericBeanSchema lookUp(T key) {
        return key2Schema.get(key);
    }

}
