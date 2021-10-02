package com.hx.codec.encoder.array;

import com.hx.codec.encoder.AbstractEncoder;
import com.hx.codec.encoder.common.DWordEncoder;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.util.Arrays;

import static com.hx.codec.constants.CodecConstants.DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING;
import static com.hx.codec.constants.CodecConstants.DEFAULT_BYTE_ORDER;

/**
 * ByteArrayEncoder
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 14:45
 */
public class DWordArrayWithFixedLenEncoder extends AbstractEncoder<Integer[]> {

    private DWordEncoder encoder;
    private int eleLength;

    public DWordArrayWithFixedLenEncoder(ByteOrder byteOrder, int eleLength) {
        super(byteOrder);
        this.encoder = new DWordEncoder(byteOrder);
        this.eleLength = eleLength;
    }

    public DWordArrayWithFixedLenEncoder(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

    @Override
    public void encode(Integer[] entity, ByteBuf buf) {
        AssertUtils.state(entity.length <= eleLength, "unexpected int[] length");
        for (int ele : entity) {
            encoder.encode(ele, buf);
        }

        byte[] paddingBytes = new byte[(eleLength - entity.length) * 4];
        Arrays.fill(paddingBytes, DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING);
        buf.writeBytes(paddingBytes);
    }

}
