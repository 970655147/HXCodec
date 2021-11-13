package com.hx.codec.codec.factory.collection;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.UnsignedByteCollectionWithEleLenCodec;
import com.hx.codec.codec.collection.UnsignedByteCollectionWithFixedLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.utils.JSONUtils;
import org.apache.logging.log4j.util.Strings;

import java.nio.ByteOrder;
import java.util.Collection;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * ByteCollectionCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 17:18
 */
public class UnsignedByteCollectionWithFixedLenCodecFactory implements AbstractCodecFactory<Collection<Integer>, Collection<Integer>> {

    @Override
    public AbstractCodec<Collection<Integer>, Collection<Integer>> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        int fixedLength = fieldAnno.lengthInBytes();
        JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_PADDING_BYTE);
        boolean paddingFirst = JSONUtils.getBooleanOrDefault(args, KEY_PADDING_FIRST, DEFAULT_PADDING_FIRST);
        return new UnsignedByteCollectionWithFixedLenCodec(byteOrder, fixedLength, paddingByte, paddingFirst);
    }

}
