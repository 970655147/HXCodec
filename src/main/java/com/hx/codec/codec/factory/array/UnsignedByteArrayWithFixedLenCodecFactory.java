package com.hx.codec.codec.factory.array;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.array.UnsignedByteArrayWithEleLenCodec;
import com.hx.codec.codec.array.UnsignedByteArrayWithFixedLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.utils.JSONUtils;
import org.apache.logging.log4j.util.Strings;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * ByteArrayWithLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 16:33
 */
public class UnsignedByteArrayWithFixedLenCodecFactory implements AbstractCodecFactory<Integer[], Integer[]> {

    @Override
    public AbstractCodec<Integer[], Integer[]> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int fixedLength = fieldAnno.lengthInBytes();
        JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_PADDING_BYTE);
        boolean paddingFirst = JSONUtils.getBooleanOrDefault(args, KEY_PADDING_FIRST, DEFAULT_PADDING_FIRST);
        return new UnsignedByteArrayWithFixedLenCodec(byteOrder, fixedLength, paddingByte, paddingFirst);
    }
}