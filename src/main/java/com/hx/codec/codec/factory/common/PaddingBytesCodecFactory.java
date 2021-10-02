package com.hx.codec.codec.factory.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.common.PaddingBytesCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.utils.JSONUtils;

import static com.hx.codec.constants.CodecConstants.DEFAULT_PADDING_BYTES_ARRAY;
import static com.hx.codec.constants.CodecConstants.KEY_PADDING_BYTES;

/**
 * ByteCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 10:31
 */
public class PaddingBytesCodecFactory implements AbstractCodecFactory<Object, Object> {

    @Override
    public AbstractCodec<Object, Object> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        JSONObject args = JSONObject.parseObject(fieldAnno.args());
        JSONArray paddingBytesArr = JSONUtils.getJSONArrayOrDefault(args, KEY_PADDING_BYTES, DEFAULT_PADDING_BYTES_ARRAY);
        byte[] paddingBytes = new byte[paddingBytesArr.size()];
        for (int i = 0; i < paddingBytesArr.size(); i++) {
            paddingBytes[i] = paddingBytesArr.getByte(i);
        }

        int fixedLength = fieldAnno.lengthInBytes();
        return new PaddingBytesCodec(paddingBytes, fixedLength);
    }

}
