package com.hx.codec.tests.codec.registry;

import com.hx.codec.codec.common.ByteCodec;
import com.hx.codec.codec.common.DWordCodec;
import com.hx.codec.codec.common.QWordCodec;
import com.hx.codec.codec.common.WordCodec;
import com.hx.codec.codec.registry.MapBasedCodecRegistry;
import com.hx.codec.codec.string.Bcd8421StringWithLenCodec;
import com.hx.codec.codec.string.CharsetEncodingStringWithFixedLenCodec;
import com.hx.codec.constants.ByteType;

/**
 * MyCodecRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-10 20:10
 */
public class MyCodecRegistry extends MapBasedCodecRegistry<String> {

    public MyCodecRegistry() {
        key2Codec.put("byte", new ByteCodec());
        key2Codec.put("word", new WordCodec());
        key2Codec.put("dword", new DWordCodec());
        key2Codec.put("qword", new QWordCodec());
        key2Codec.put("bcd8421WithLen", new Bcd8421StringWithLenCodec(ByteType.BYTE));
        key2Codec.put("charsetEncodingWithFixedLen", new CharsetEncodingStringWithFixedLenCodec(20));
    }

}
