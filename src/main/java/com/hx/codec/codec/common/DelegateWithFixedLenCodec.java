package com.hx.codec.codec.common;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTE;
import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_FIRST;

/**
 * DelegateWithFixedLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-11-12 20:25
 */
public class DelegateWithFixedLenCodec<IN, OUT> extends AbstractCodec<IN, OUT> {

    /**
     * delegate
     */
    private AbstractCodec<IN, OUT> delegate;
    // padding related
    private byte paddingByte;
    // paddingFirst
    private boolean paddingFirst;
    // byteUnit
    private int byteUnit;
    // length
    private int fixedLength;

    public DelegateWithFixedLenCodec(AbstractCodec<IN, OUT> delegate, byte paddingByte, boolean paddingFirst, int byteUnit, int fixedLength) {
        this.delegate = delegate;
        this.paddingByte = paddingByte;
        this.paddingFirst = paddingFirst;
        this.byteUnit = byteUnit;
        this.fixedLength = fixedLength;
    }

    public DelegateWithFixedLenCodec(AbstractCodec<IN, OUT> delegate, byte paddingByte, boolean paddingFirst, int fixedLength) {
        this(delegate, paddingByte, paddingFirst, 1, fixedLength);
    }

    public DelegateWithFixedLenCodec(AbstractCodec<IN, OUT> delegate, byte paddingByte, int fixedLength) {
        this(delegate, paddingByte, DEFAULT_PADDING_FIRST, fixedLength);
    }

    public DelegateWithFixedLenCodec(AbstractCodec<IN, OUT> delegate, int fixedLength) {
        this(delegate, DEFAULT_PADDING_BYTE, fixedLength);
    }

    @Override
    public void encode(OUT entity, ByteBuf buf) {
        // write entity
        ByteBuf entityBytes = Unpooled.buffer(delegate.length());
        int writerIndex = entityBytes.writerIndex();
        delegate.encode(entity, entityBytes);
        int writtenLen = entityBytes.writerIndex() - writerIndex;

        // calc padding
        AssertUtils.state(writtenLen <= fixedLength, "unexpected string length");
        int paddingLength = fixedLength - writtenLen;
        // cache for this
        byte[] paddingBytes = new byte[paddingLength];
        Arrays.fill(paddingBytes, paddingByte);

        // apply padding & entityBytes
        if (paddingFirst) {
            buf.writeBytes(paddingBytes);
        }
        buf.writeBytes(entityBytes);
        if (!paddingFirst) {
            buf.writeBytes(paddingBytes);
        }
    }

    @Override
    public IN decode(ByteBuf buf) {
        byte[] entityBytesWithPadding = new byte[fixedLength];
        buf.readBytes(entityBytesWithPadding);

        int startIdx = 0, endIdx = entityBytesWithPadding.length - 1;
        for (startIdx = 0; entityBytesWithPadding[startIdx] == paddingByte && startIdx < endIdx; startIdx++) ;
        for (endIdx = entityBytesWithPadding.length - 1; endIdx >= startIdx && entityBytesWithPadding[endIdx] == paddingByte; endIdx--)
            ;
        if (byteUnit > 1) {
            startIdx = startIdx - (startIdx % byteUnit);
            endIdx = endIdx + ((endIdx % byteUnit == 0) ? 0 : (byteUnit - (endIdx % byteUnit))) - 1;
        }

        ByteBuf entityBytes = Unpooled.wrappedBuffer(entityBytesWithPadding, startIdx, (endIdx - startIdx + 1));
        return delegate.decode(entityBytes);
    }

    @Override
    public boolean isFixedLength() {
        return true;
    }

    @Override
    public int length() {
        return fixedLength;
    }

}
