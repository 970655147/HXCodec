package com.hx.codec.tests.codec.registry;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.registry.MapBasedCodecRegistry;
import com.hx.codec.tests.codec.EntityWithTypeCodec;
import com.hx.codec.tests.model.EntityWithType;
import io.netty.buffer.ByteBuf;

/**
 * EntityWithTypeCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-11-10 20:47
 */
public class EntityWithTypeCodecRegistry extends MapBasedCodecRegistry<EntityWithType> {

    @Override
    public boolean registry(EntityWithType key, AbstractCodec codec) {
        return super.registry(key, codec);
    }

    @Override
    public AbstractCodec lookUp(EntityWithType key) {
        return new EntityWithTypeCodec();
    }

    @Override
    public EntityWithType readKeyFrom(ByteBuf buf) {
        return super.readKeyFrom(buf);
    }
}
