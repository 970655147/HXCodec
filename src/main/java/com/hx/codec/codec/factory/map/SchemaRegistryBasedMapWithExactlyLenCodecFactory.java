package com.hx.codec.codec.factory.map;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.map.SchemaRegistryBasedMapWithExactlyLenCodec;
import com.hx.codec.codec.registry.CodecRegistry;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import org.apache.logging.log4j.util.Strings;

import static com.hx.codec.constants.CodecConstants.KEY_KEY_TYPE;

/**
 * GenericMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class SchemaRegistryBasedMapWithExactlyLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        java.lang.reflect.Field field = context.getField();
        Class keyType = CodecUtils.getActualTypeArgument(field, 0);
        int eleLength = fieldAnno.eleLength();

        try {
            CodecRegistry codecRegistry = context.getFieldSchema().getCodecRegistry();

            if (CodecUtils.isNotBlank(fieldAnno.args())) {
                JSONObject args = Strings.isBlank(fieldAnno.args()) ? null : JSONObject.parseObject(fieldAnno.args());
                String keyTypeFromArgs = args.getString(KEY_KEY_TYPE);
                AbstractCodec keyCodec = null;
                if (CodecUtils.isNotBlank(keyTypeFromArgs)) {
                    keyCodec = CodecUtils.createCodecForMapCodecFactory(keyTypeFromArgs, keyType, context);
                }
                if (keyCodec != null) {
                    return new SchemaRegistryBasedMapWithExactlyLenCodec(keyCodec, codecRegistry, eleLength);
                }
            }

            return new SchemaRegistryBasedMapWithExactlyLenCodec(
                    new GenericBeanCodec<>(new GenericBeanSchema<>(keyType, context.getVersion())),
                    codecRegistry,
                    eleLength);
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " error while create SchemaRegistryBasedMapWithLenCodec ");
            return null;
        }
    }

}
