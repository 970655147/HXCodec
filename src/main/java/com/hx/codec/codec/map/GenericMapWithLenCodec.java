package com.hx.codec.codec.map;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.CodecConstants;
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
public class GenericMapWithLenCodec<K, V> extends AbstractCodec<Map<K, V>, Map<K, V>> {

    private AbstractCodec<K, K> keyCodec;
    private AbstractCodec<V, V> valueCodec;

    private ByteType lenByteType;

    public GenericMapWithLenCodec(AbstractCodec<K, K> keyCodec, AbstractCodec<V, V> valueCodec, ByteType lenByteType) {
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
        this.lenByteType = lenByteType;
    }

    @Override
    public void encode(Map<K, V> entity, ByteBuf buf) {
        CodecUtils.writeLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, entity.size(), buf);
        for (Map.Entry<K, V> entry : entity.entrySet()) {
            keyCodec.encode(entry.getKey(), buf);
            valueCodec.encode(entry.getValue(), buf);
        }
    }

    @Override
    public Map<K, V> decode(ByteBuf buf) {
        int size = (int) CodecUtils.readLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, buf);
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
        return false;
    }

    @Override
    public int length() {
        return keyCodec.length() + valueCodec.length();
    }

}
