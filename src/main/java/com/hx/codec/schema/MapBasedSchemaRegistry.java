package com.hx.codec.schema;

import com.hx.codec.codec.AbstractCodec;

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

    // key2Codec
    protected Map<T, AbstractCodec> key2Codec = new LinkedHashMap<>();

    @Override
    public boolean registry(T key, AbstractCodec beanSchema) {
        key2Codec.put(key, beanSchema);
        return true;
    }

    @Override
    public AbstractCodec lookUp(T key) {
        return key2Codec.get(key);
    }

}
