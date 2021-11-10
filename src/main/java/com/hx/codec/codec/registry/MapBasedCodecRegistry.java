package com.hx.codec.codec.registry;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.registry.CodecRegistry;
import io.netty.buffer.ByteBuf;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MapBasedCodecRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 10:00
 */
public class MapBasedCodecRegistry<T> implements CodecRegistry<T> {

    // key2Codec
    protected Map<T, AbstractCodec> key2Codec = new LinkedHashMap<>();

    @Override
    public boolean registry(T key, AbstractCodec codec) {
        key2Codec.put(key, codec);
        return true;
    }

    @Override
    public AbstractCodec lookUp(T key) {
        return key2Codec.get(key);
    }

    @Override
    public T readKeyFrom(ByteBuf buf) {
        return null;
    }

}
