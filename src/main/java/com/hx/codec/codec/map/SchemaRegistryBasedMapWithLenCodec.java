package com.hx.codec.codec.map;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.SchemaRegistry;
import com.hx.codec.utils.CodecUtils;
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
public class SchemaRegistryBasedMapWithLenCodec<K> extends AbstractCodec<Map<K, Object>, Map<K, Object>> {

    private AbstractCodec<K, K> keyCodec;
    private SchemaRegistry<K> schemaRegistry;
    private ByteType lenByteType;

    public SchemaRegistryBasedMapWithLenCodec(AbstractCodec<K, K> keyCodec, SchemaRegistry<K> schemaRegistry, ByteType lenByteType) {
        this.keyCodec = keyCodec;
        this.schemaRegistry = schemaRegistry;
        this.lenByteType = lenByteType;
    }

    @Override
    public void encode(Map<K, Object> entity, ByteBuf buf) {
        CodecUtils.writeLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, entity.size(), buf);
        for (Map.Entry<K, Object> entry : entity.entrySet()) {
            GenericBeanSchema valueSchema = schemaRegistry.lookUp(entry.getKey());
            GenericBeanCodec valueCodec = new GenericBeanCodec(valueSchema);

            keyCodec.encode(entry.getKey(), buf);
            valueCodec.encode(entry.getKey(), buf);

        }
    }

    @Override
    public Map<K, Object> decode(ByteBuf buf) {
        int size = (int) CodecUtils.readLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, buf);
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
