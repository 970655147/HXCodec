package com.hx.codec.codec.factory.common;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.PaddingByteCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.utils.JSONUtils;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTE;
import static com.hx.codec.constants.CodecConstants.KEY_PADDING_BYTE;

/**
 * ByteCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 10:31
 */
public class PaddingByteCodecFactory implements AbstractCodecFactory<Object, Object> {

    @Override
    public AbstractCodec<Object, Object> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        JSONObject args = JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_PADDING_BYTE);
        int fixedLength = fieldAnno.lengthInBytes();
        return new PaddingByteCodec(paddingByte, fixedLength);
    }

}
