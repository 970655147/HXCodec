package com.hx.codec.codec.map;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.SchemaRegistry;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GenericMapCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:25
 */
public class SchemaRegistryBasedMapWithExactlyLenCodec<K> extends AbstractCodec<Map<K, Object>, Map<K, Object>> {

    private AbstractCodec<K, K> keyCodec;
    private SchemaRegistry<K> schemaRegistry;
    private int eleLength;

    public SchemaRegistryBasedMapWithExactlyLenCodec(AbstractCodec<K, K> keyCodec, SchemaRegistry<K> schemaRegistry, int eleLength) {
        this.keyCodec = keyCodec;
        this.schemaRegistry = schemaRegistry;
        this.eleLength = eleLength;
    }

    @Override
    public void encode(Map<K, Object> entity, ByteBuf buf) {
        AssertUtils.state(entity.size() == eleLength, " unexpected entity length ");
        for (Map.Entry<K, Object> entry : entity.entrySet()) {
            GenericBeanSchema valueSchema = schemaRegistry.lookUp(entry.getKey());
            GenericBeanCodec valueCodec = new GenericBeanCodec(valueSchema);

            keyCodec.encode(entry.getKey(), buf);
            valueCodec.encode(entry.getKey(), buf);

        }
    }

    @Override
    public Map<K, Object> decode(ByteBuf buf) {
        int size = eleLength;
        Map<K, Object> result = new LinkedHashMap<>(size);
        for (int i = 0; i < size; i++) {
            K key = keyCodec.decode(buf);
            GenericBeanSchema valueSchema = schemaRegistry.lookUp(key);
            GenericBeanCodec valueCodec = new GenericBeanCodec(valueSchema);

            Object value = valueCodec.decode(buf);
            result.put(key, value);
        }
        return result;
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return keyCodec.length() * 2;
    }

}
