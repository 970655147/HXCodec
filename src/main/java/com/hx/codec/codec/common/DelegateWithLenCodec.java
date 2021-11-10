package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * DelegateWithLenCodec
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-10 15:48
 */
public class DelegateWithLenCodec<IN, OUT> extends AbstractCodec<IN, OUT> {

    /**
     * delegate
     */
    private AbstractCodec<IN, OUT> delegate;
    /**
     * lenByteType
     */
    private ByteType lenByteType;
    /**
     * includeLen
     */
    private boolean includeLen;

    public DelegateWithLenCodec(AbstractCodec<IN, OUT> delegate, ByteType lenByteType, boolean includeLen) {
        this.delegate = delegate;
        this.lenByteType = lenByteType;
        this.includeLen = includeLen;
    }

    public DelegateWithLenCodec(AbstractCodec<IN, OUT> delegate, ByteType lenByteType) {
        this(delegate, lenByteType, DEFAULT_INCLUDE_LEN);
    }

    public DelegateWithLenCodec(AbstractCodec<IN, OUT> delegate) {
        this(delegate, DEFAULT_LEN_BYTE_TYPE, DEFAULT_INCLUDE_LEN);
    }

    @Override
    public void encode(OUT entity, ByteBuf buf) {
        int writerLenIdx = buf.writerIndex();
        CodecUtils.writeLen(lenByteType, DEFAULT_BYTE_ORDER, 0, buf);

        int writerIndex = buf.writerIndex();
        delegate.encode(entity, buf);
        int writtenLen = buf.writerIndex() - writerIndex;
        if (includeLen) {
            writtenLen += CodecUtils.lenBytes(lenByteType);
        }
        CodecUtils.setInt(buf, CodecUtils.lenBytes(lenByteType), writerLenIdx, writtenLen);
    }

    @Override
    public IN decode(ByteBuf buf) {
        int len = (int) CodecUtils.readLen(lenByteType, DEFAULT_BYTE_ORDER, buf);
        if (includeLen) {
            len -= CodecUtils.lenBytes(lenByteType);
        }

        ByteBuf decodeBuf = buf.slice(buf.readerIndex(), len);
        buf.skipBytes(len);
        return delegate.decode(decodeBuf);
    }

    @Override
    public boolean isFixedLength() {
        return delegate.isFixedLength();
    }

    @Override
    public int length() {
        return delegate.length() + CodecUtils.lenBytes(lenByteType);
    }

}
