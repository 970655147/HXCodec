package com.hx.codec.codec;


import com.hx.codec.decoder.AbstractDecoder;
import com.hx.codec.encoder.AbstractEncoder;
import io.netty.buffer.ByteBuf;

/**
 * AbstractProtocol
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:04
 */
public abstract class AbstractCodec<IN, OUT> {

    /**
     * encoder & decoder
     */
    protected AbstractEncoder<OUT> encoder;
    protected AbstractDecoder<IN> decoder;

    /**
     * encoder
     *
     * @return ByteBuffer
     * @author Jerry.X.He
     * @date 2021/9/23 10:04
     */
    public abstract void encode(OUT entity, ByteBuf buf);

    /**
     * decode
     *
     * @return IN
     * @author Jerry.X.He
     * @date 2021/9/23 10:05
     */
    public abstract IN decode(ByteBuf buf);

    /**
     * isFixedLength
     *
     * @return boolean
     * @author Jerry.X.He
     * @date 2021/9/28 9:13
     */
    public abstract boolean isFixedLength();

    /**
     * length
     *
     * @return int
     * @author Jerry.X.He
     * @date 2021/9/28 9:14
     */
    public abstract int length();

}
