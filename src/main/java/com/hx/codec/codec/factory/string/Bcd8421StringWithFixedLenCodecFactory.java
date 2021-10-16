package com.hx.codec.codec.factory.string;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.string.Bcd8421StringWithFixedLenCodec;
import com.hx.codec.utils.JSONUtils;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.KEY_PADDING_BYTE;

/**
 * Bcd8421StringWithFixedLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:57
 */
public class Bcd8421StringWithFixedLenCodecFactory implements AbstractCodecFactory<String, String> {

    @Override
    public AbstractCodec<String, String> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        JSONObject args = JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_BCD8421_PADDING);
        int fixedLength = fieldAnno.lengthInBytes();
        return new Bcd8421StringWithFixedLenCodec(byteOrder, fixedLength, paddingByte);
    }

}
