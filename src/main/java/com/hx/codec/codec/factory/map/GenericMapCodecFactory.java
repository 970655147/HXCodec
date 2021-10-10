package com.hx.codec.codec.factory.map;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.map.GenericMapCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.CodecUtils;

import static com.hx.codec.constants.CodecConstants.KEY_KEY_TYPE;
import static com.hx.codec.constants.CodecConstants.KEY_VALUE_TYPE;

/**
 * GenericMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class GenericMapCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        java.lang.reflect.Field field = context.getField();
        Class keyType = CodecUtils.getActualTypeArgument(field, 0);
        Class valueType = CodecUtils.getActualTypeArgument(field, 1);

        if (CodecUtils.isNotBlank(fieldAnno.args())) {
            JSONObject args = JSONObject.parseObject(fieldAnno.args());
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
                return new GenericMapCodec(keyCodec, valueCodec);
            }
        }

        return new GenericMapCodec(
                new GenericBeanCodec<>(new GenericBeanSchema<>(keyType, context.getVersion())),
                new GenericBeanCodec<>(new GenericBeanSchema<>(valueType, context.getVersion()))
        );
    }

}
