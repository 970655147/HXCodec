package com.hx.codec.codec.factory.map;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.map.GenericMapWithLenCodec;
import com.hx.codec.constants.ByteType;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import org.apache.logging.log4j.util.Strings;

import static com.hx.codec.constants.CodecConstants.KEY_KEY_TYPE;
import static com.hx.codec.constants.CodecConstants.KEY_VALUE_TYPE;

/**
 * GenericMapWithLenCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class GenericMapWithLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        java.lang.reflect.Field field = context.getField();
        Class keyType = CodecUtils.getActualTypeArgument(field, 0);
        Class valueType = CodecUtils.getActualTypeArgument(field, 1);
        ByteType lenByteType = fieldAnno.lengthByteType();

        if (CodecUtils.isNotBlank(fieldAnno.args())) {
            JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
            String keyTypeFromArgs = args.getString(KEY_KEY_TYPE);
            String valueTypeFromArgs = args.getString(KEY_VALUE_TYPE);
            AbstractCodec keyCodec = null, valueCodec = null;
            if (CodecUtils.isNotBlank(keyTypeFromArgs)) {
                keyCodec = CodecUtils.createCodecForMapCodecFactory(keyTypeFromArgs, keyType, context);
            }
            if (CodecUtils.isNotBlank(valueTypeFromArgs)) {
                valueCodec = CodecUtils.createCodecForMapCodecFactory(valueTypeFromArgs, valueType, context);
            }
            if (keyCodec != null && valueCodec != null) {
                return new GenericMapWithLenCodec(keyCodec, valueCodec, lenByteType);
            }
        }

        return new GenericMapWithLenCodec(
                new GenericBeanCodec<>(new GenericBeanSchema<>(keyType, context.getVersion())),
                new GenericBeanCodec<>(new GenericBeanSchema<>(valueType, context.getVersion())),
                lenByteType
        );
    }

}
