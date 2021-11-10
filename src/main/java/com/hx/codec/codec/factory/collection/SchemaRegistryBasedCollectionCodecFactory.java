package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.SchemaRegistryBasedCollectionCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.registry.CodecRegistry;
import com.hx.codec.utils.AssertUtils;

/**
 * GenericMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class SchemaRegistryBasedCollectionCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();

        try {
            CodecRegistry codecRegistry = context.getFieldSchema().getCodecRegistry();
            return new SchemaRegistryBasedCollectionCodec(codecRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " error while create SchemaRegistryBasedCollectionCodecFactory ");
            return null;
        }
    }

}
