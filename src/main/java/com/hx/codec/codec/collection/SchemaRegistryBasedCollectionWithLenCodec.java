package com.hx.codec.codec.collection;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.registry.CodecRegistry;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.CodecConstants;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
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
public class SchemaRegistryBasedCollectionWithLenCodec<T> extends AbstractCodec<Collection<T>, Collection<T>> {

    private CodecRegistry<T> codecRegistry;
    private ByteType lenByteType;

    public SchemaRegistryBasedCollectionWithLenCodec(CodecRegistry<T> codecRegistry, ByteType lenByteType) {
        this.codecRegistry = codecRegistry;
        this.lenByteType = lenByteType;
    }


    @Override
    public void encode(Collection<T> entity, ByteBuf buf) {
        ByteBufUtils.writeLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, entity.size(), buf);
        for (T ele : entity) {
            AbstractCodec valueCodec = codecRegistry.lookUp(ele);
            valueCodec.encode(ele, buf);
        }
    }

    @Override
    public Collection<T> decode(ByteBuf buf) {
        int size = (int) ByteBufUtils.readLen(lenByteType, CodecConstants.DEFAULT_BYTE_ORDER, buf);
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
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
