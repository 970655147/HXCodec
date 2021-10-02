package com.hx.codec.codec.factory.string;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.string.CharsetEncodingStringWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.CodecUtils;
import com.hx.codec.utils.JSONUtils;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import static com.hx.codec.constants.CodecConstants.DEFAULT_CHARSET;
import static com.hx.codec.constants.CodecConstants.KEY_CHARSET;

/**
 * CharsetEncodingStringWithLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:57
 */
public class CharsetEncodingStringWithLenCodecFactory implements AbstractCodecFactory<String, String> {

    @Override
    public AbstractCodec<String, String> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        ByteType lengthByteType = fieldAnno.lengthByteType();
        JSONObject args = JSONObject.parseObject(fieldAnno.args());
        String charsetStr = JSONUtils.getStringOrDefault(args, KEY_CHARSET, DEFAULT_CHARSET.name());
        Charset charset = CodecUtils.charsetForName(charsetStr);
        return new CharsetEncodingStringWithLenCodec(byteOrder, lengthByteType, charset);
    }

}
