package com.hx.codec.codec.factory.collection;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.collection.SchemaRegistryBasedCollectionWithLenCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.registry.CodecRegistry;
import com.hx.codec.constants.ByteType;
import com.hx.codec.utils.AssertUtils;

/**
 * GenericMapCodecFactory
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 11:45
 */
public class SchemaRegistryBasedCollectionWithLenCodecFactory implements AbstractCodecFactory {

    @Override
    public AbstractCodec create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteType lenByteType = fieldAnno.lengthByteType();

        try {
            CodecRegistry codecRegistry = context.getFieldSchema().getCodecRegistry();
            return new SchemaRegistryBasedCollectionWithLenCodec(
                    codecRegistry,
                    lenByteType);
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " error while create SchemaRegistryBasedCollectionWithLenCodecFactory ");
            return null;
        }
    }

}
