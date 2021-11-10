package com.hx.codec.codec.factory.map;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.map.SchemaRegistryBasedMapCodec;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.codec.registry.CodecRegistry;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;

import static com.hx.codec.constants.CodecConstants.KEY_KEY_TYPE;

/**
 * GenericMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class SchemaRegistryBasedMapCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        java.lang.reflect.Field field = context.getField();
        Class keyType = CodecUtils.getActualTypeArgument(field, 0);

        try {
            CodecRegistry codecRegistry = context.getFieldSchema().getCodecRegistry();

            if (CodecUtils.isNotBlank(fieldAnno.args())) {
                JSONObject args = JSONObject.parseObject(fieldAnno.args());
                String keyTypeFromArgs = args.getString(KEY_KEY_TYPE);
                AbstractCodec keyCodec = null;
                if (CodecUtils.isNotBlank(keyTypeFromArgs)) {
                    keyCodec = CodecUtils.createCodecForMapCodecFactory(keyTypeFromArgs, keyType, context);
                }
                if (keyCodec != null) {
                    return new SchemaRegistryBasedMapCodec(keyCodec, codecRegistry);
                }
            }

            return new SchemaRegistryBasedMapCodec(
                    new GenericBeanCodec<>(new GenericBeanSchema<>(keyType, context.getVersion())),
                    codecRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " error while create SchemaRegistryBasedMapCodec ");
            return null;
        }
    }

}
