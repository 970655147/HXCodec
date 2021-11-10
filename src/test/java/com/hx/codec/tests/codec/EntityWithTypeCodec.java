package com.hx.codec.tests.codec;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.DWordCodec;
import com.hx.codec.codec.string.CharsetEncodingStringWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.tests.model.EntityWithType;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EntityWithTypeCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-11-10 20:47
 */
public class EntityWithTypeCodec extends AbstractCodec<EntityWithType, EntityWithType> {

    private AbstractCodec typeCodec = new CharsetEncodingStringWithLenCodec(ByteType.BYTE, CodecUtils.charsetForName("utf16"));
    private Map<String, AbstractCodec> type2Codec = new LinkedHashMap<>();

    public EntityWithTypeCodec() {
        type2Codec.put("dword", new DWordCodec());
        type2Codec.put("string", new CharsetEncodingStringWithLenCodec(ByteType.BYTE, CodecUtils.charsetForName("gbk")));
    }

    @Override
    public void encode(EntityWithType entity, ByteBuf buf) {
        String type = entity.getType();
        AbstractCodec entityCodec = type2Codec.get(type);

        typeCodec.encode(entity.getType(), buf);
        entityCodec.encode(entity.getEntity(), buf);
    }

    @Override
    public EntityWithType decode(ByteBuf buf) {
        String type = String.valueOf(typeCodec.decode(buf));
        AbstractCodec entityCodec = type2Codec.get(type);

        Object entity = entityCodec.decode(buf);
        return new EntityWithType(type, entity);
    }

    @Override
    public boolean isFixedLength() {
        return false;
    }

    @Override
    public int length() {
        return 0;
    }

}
