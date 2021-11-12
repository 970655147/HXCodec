package com.hx.codec.codec.factory.string;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.string.Bcd8421StringCodec;
import com.hx.codec.utils.JSONUtils;
import org.apache.logging.log4j.util.Strings;

import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.*;

/**
 * Bcd8421StringCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:57
 */
public class Bcd8421StringCodecFactory implements AbstractCodecFactory<String, String> {

    @Override
    public AbstractCodec<String, String> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_BCD8421_PADDING);
        boolean paddingFirst = JSONUtils.getBooleanOrDefault(args, KEY_PADDING_FIRST, DEFAULT_PADDING_FIRST);
        return new Bcd8421StringCodec(byteOrder, paddingByte, paddingFirst);
    }

}
