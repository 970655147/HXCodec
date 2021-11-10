package com.hx.codec.codec.registry;

import com.hx.codec.codec.AbstractCodec;
import io.netty.buffer.ByteBuf;

/**
 * CodecRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 09:59
 */
public interface CodecRegistry<T> {

    /**
     * registry
     *
     * @param key        key
     * @param codec codec
     * @return boolean
     * @author Jerry.X.He
     * @date 2021-10-10 10:00
     */
    boolean registry(T key, AbstractCodec codec);

    /**
     * lookUp
     *
     * @param key key
     * @return GenericBeanSchema
     * @author Jerry.X.He
     * @date 2021-10-10 10:00
     */
    AbstractCodec lookUp(T key);

    /**
     * readKeyFrom
     *
     * @param buf buf
     * @return
     * @author Jerry.X.He
     * @date 2021-11-10 19:53
     */
    T readKeyFrom(ByteBuf buf);

}
