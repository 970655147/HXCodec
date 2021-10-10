package com.hx.codec.codec.map;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
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
public class GenericMapWithExactlyLenCodec<K, V> extends AbstractCodec<Map<K, V>, Map<K, V>> {

    private AbstractCodec<K, K> keyCodec;
    private AbstractCodec<V, V> valueCodec;

    private int eleLength;

    public GenericMapWithExactlyLenCodec(AbstractCodec<K, K> keyCodec, AbstractCodec<V, V> valueCodec, int eleLength) {
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
        this.eleLength = eleLength;
    }

    @Override
    public void encode(Map<K, V> entity, ByteBuf buf) {
        AssertUtils.state(entity.size() == eleLength, " unexpected entity length ");
        for (Map.Entry<K, V> entry : entity.entrySet()) {
            keyCodec.encode(entry.getKey(), buf);
            valueCodec.encode(entry.getValue(), buf);
        }
    }

    @Override
    public Map<K, V> decode(ByteBuf buf) {
        int size = eleLength;
        Map<K, V> result = new LinkedHashMap<>(size);
        for (int i = 0; i < size; i++) {
            K key = keyCodec.decode(buf);
            V value = valueCodec.decode(buf);
            result.put(key, value);
        }
        return result;
    }

    @Override
    public boolean isFixedLength() {
        return keyCodec.isFixedLength() && valueCodec.isFixedLength();
    }

    @Override
    public int length() {
        return (keyCodec.length() + valueCodec.length()) * eleLength;
    }

}
