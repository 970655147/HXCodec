package com.hx.codec.decoder;

import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

/**
 * AbstractDecoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:09
 */
public abstract class AbstractDecoder<T> {

    // byte order
    protected ByteOrder byteOrder;

    public AbstractDecoder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public AbstractDecoder() {
        this(ByteOrder.BIG_ENDIAN);
    }

    /**
     * isBigEndian
     *
     * @return boolean
     * @author Jerry.X.He
     * @date 2021/9/23 10:39
     */
    public boolean isBigEndian() {
        return byteOrder == ByteOrder.BIG_ENDIAN;
    }


    /**
     * decode
     *
     * @return IN
     * @author Jerry.X.He
     * @date 2021/9/23 10:05
     */
    public abstract T decode(ByteBuf buf);

}
