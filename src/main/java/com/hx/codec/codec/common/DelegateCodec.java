package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import io.netty.buffer.ByteBuf;

/**
 * DelegateCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-10 15:48
 */
public class DelegateCodec<IN, OUT> extends AbstractCodec<IN, OUT> {

    /**
     * delegate
     */
    private AbstractCodec<IN, OUT> delegate;

    public DelegateCodec(AbstractCodec<IN, OUT> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void encode(OUT entity, ByteBuf buf) {
        delegate.encode(entity, buf);
    }

    @Override
    public IN decode(ByteBuf buf) {
        return delegate.decode(buf);
    }

    @Override
    public boolean isFixedLength() {
        return delegate.isFixedLength();
    }

    @Override
    public int length() {
        return delegate.length();
    }

}
