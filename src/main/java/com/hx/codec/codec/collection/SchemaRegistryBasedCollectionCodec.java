package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.registry.CodecRegistry;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * GenericBeanCollectionCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-03 11:47
 */
public class SchemaRegistryBasedCollectionCodec<T> extends AbstractCodec<Collection<T>, Collection<T>> {

    private CodecRegistry<T> codecRegistry;

    public SchemaRegistryBasedCollectionCodec(CodecRegistry<T> codecRegistry) {
        this.codecRegistry = codecRegistry;
    }


    @Override
    public void encode(Collection<T> entity, ByteBuf buf) {
        for (T ele : entity) {
            AbstractCodec valueCodec = codecRegistry.lookUp(ele);
            valueCodec.encode(ele, buf);
        }
    }

    @Override
    public Collection<T> decode(ByteBuf buf) {
        List<T> result = new ArrayList<>();
        while (buf.readableBytes() > 0) {
            T registryKey = codecRegistry.readKeyFrom(buf);
            AbstractCodec valueCodec = codecRegistry.lookUp(registryKey);
            result.add((T) valueCodec.decode(buf));
        }
        return result;
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 30;
    }
}
