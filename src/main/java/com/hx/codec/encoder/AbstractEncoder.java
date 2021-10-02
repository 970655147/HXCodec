package com.hx.codec.encoder;

import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * AbstractEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:08
 */
public abstract class AbstractEncoder<T> {

    // byte order
    protected ByteOrder byteOrder;

    public AbstractEncoder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public AbstractEncoder() {
        this(DEFAULT_BYTE_ORDER);
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
     * encoder
     *
     * @return ByteBuffer
     * @author Jerry.X.He
     * @date 2021/9/23 10:04
     */
    public abstract void encode(T entity, ByteBuf buf);

}
