package com.hx.codec.tests.codec.factory;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.tests.codec.MyConfigMapCodec;

/**
 * MyConfigMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 17:41
 */
public class MyConfigMapCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        return new MyConfigMapCodec();
    }

}
