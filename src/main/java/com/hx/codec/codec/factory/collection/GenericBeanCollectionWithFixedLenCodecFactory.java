package com.hx.codec.codec.factory.collection;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.GenericBeanCollectionWithEleLenCodec;
import com.hx.codec.codec.collection.GenericBeanCollectionWithFixedLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.CodecUtils;
import com.hx.codec.utils.JSONUtils;
import org.apache.logging.log4j.util.Strings;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * GenericBeanArrayCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-03 12:06
 */
public class GenericBeanCollectionWithFixedLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int fixedLength = fieldAnno.lengthInBytes();
        java.lang.reflect.Field field = context.getField();
        Class eleType = CodecUtils.getActualTypeArgument(field);
        JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_PADDING_BYTE);
        boolean paddingFirst = JSONUtils.getBooleanOrDefault(args, KEY_PADDING_FIRST, DEFAULT_PADDING_FIRST);
        return new GenericBeanCollectionWithFixedLenCodec(new GenericBeanSchema<>(eleType, context.getVersion()), byteOrder, fixedLength, paddingByte, paddingFirst);
    }
}
