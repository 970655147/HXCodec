package com.hx.codec.codec.array;

import com.hx.codec.codec.common.DelegateCodec;
import com.hx.codec.codec.common.DelegateWithFixedLenCodec;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * WordArrayWithEleLenCodec
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 10:22
 */
public class WordArrayWithEleLenCodec extends DelegateCodec<Integer[], Integer[]> {

    public WordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte, boolean paddingFirst) {
        super(new DelegateWithFixedLenCodec<>(new WordArrayCodec(byteOrder), paddingByte, paddingFirst, WORD_UNIT, eleLength * WORD_UNIT));
    }

    public WordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength, byte paddingByte) {
        this(byteOrder, eleLength, paddingByte, DEFAULT_PADDING_FIRST);
    }

    public WordArrayWithEleLenCodec(ByteOrder byteOrder, int eleLength) {
        this(byteOrder, eleLength, DEFAULT_PADDING_BYTE);
    }

    public WordArrayWithEleLenCodec(int eleLength) {
        this(DEFAULT_BYTE_ORDER, eleLength);
    }

}
