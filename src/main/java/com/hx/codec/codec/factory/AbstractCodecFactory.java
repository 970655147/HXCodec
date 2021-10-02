package com.hx.codec.codec.factory;

import com.hx.codec.codec.AbstractCodec;

/**
 * AbstractCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:54
 */
public interface AbstractCodecFactory<IN, OUT> {

    /**
     * create
     *
     * @return AbstractCodec
     * @author Jerry.X.He
     * @date 2021/9/28 9:55
     */
    AbstractCodec<IN, OUT> create(CodecFactoryContext context);

}
