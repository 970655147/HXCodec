package com.hx.codec.tests.codec;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.map.GenericMapCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.tests.model.UpConnectReq;
import io.netty.buffer.ByteBuf;

import java.util.Map;

/**
 * MyConfigMapCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 17:41
 */
public class MyConfigMapCodec extends AbstractCodec<Map<UpConnectReq, UpConnectReq>, Map<UpConnectReq, UpConnectReq>> {

    private GenericMapCodec<UpConnectReq, UpConnectReq> mapCodec = new GenericMapCodec<>(
            new GenericBeanCodec<>(new GenericBeanSchema<>(UpConnectReq.class, 2019)),
            new GenericBeanCodec<>(new GenericBeanSchema<>(UpConnectReq.class, 2019))
    );

    @Override
    public void encode(Map<UpConnectReq, UpConnectReq> entity, ByteBuf buf) {
        mapCodec.encode(entity, buf);
    }

    @Override
    public Map<UpConnectReq, UpConnectReq> decode(ByteBuf buf) {
        return mapCodec.decode(buf);
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
