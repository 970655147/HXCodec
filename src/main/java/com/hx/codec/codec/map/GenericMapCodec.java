package com.hx.codec.codec.map;

import com.hx.codec.codec.AbstractCodec;
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
public class GenericMapCodec<K, V> extends AbstractCodec<Map<K, V>, Map<K, V>> {

    private AbstractCodec<K, K> keyCodec;
    private AbstractCodec<V, V> valueCodec;

    public GenericMapCodec(AbstractCodec<K, K> keyCodec, AbstractCodec<V, V> valueCodec) {
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
    }

    @Override
    public void encode(Map<K, V> entity, ByteBuf buf) {
        for (Map.Entry<K, V> entry : entity.entrySet()) {
            keyCodec.encode(entry.getKey(), buf);
            valueCodec.encode(entry.getValue(), buf);
        }
    }

    @Override
    public Map<K, V> decode(ByteBuf buf) {
        Map<K, V> result = new LinkedHashMap<>();
        while (buf.readableBytes() > 0) {
            K key = keyCodec.decode(buf);
            V value = valueCodec.decode(buf);
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
        return keyCodec.length() + valueCodec.length();
    }

}
