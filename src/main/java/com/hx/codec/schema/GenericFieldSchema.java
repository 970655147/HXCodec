package com.hx.codec.schema;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.interceptor.FieldInterceptor;
import com.hx.codec.utils.AssertUtils;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GenericFieldSchema
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/24 17:36
 */
@Data
public class GenericFieldSchema {

    // fieldName
    private String fieldName;
    // fieldType
    private Class fieldType;
    // @Field
    private Field fieldAnno;
    // field
    private java.lang.reflect.Field field;
    // getter
    private Method getterMethod;
    // setter
    private Method setterMethod;
    // codec
    private AbstractCodec codec;

    // offset - computed
    private int offset;
    private FieldInterceptor fieldInterceptor;
    private SchemaRegistry schemaRegistry;

    public GenericFieldSchema() {
    }

    /**
     * init
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:06
     */
    public void init() {
        try {
            fieldInterceptor = fieldAnno.fieldInterceptorClazz() == FieldInterceptor.class ?
                    null : fieldAnno.fieldInterceptorClazz().newInstance();
            schemaRegistry = fieldAnno.schemaRegistryClazz() == SchemaRegistry.class ?
                    null : fieldAnno.schemaRegistryClazz().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " error while init GenericFieldSchema : " + this.toString());
        }
    }

    @Override
    public String toString() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("fieldName", fieldName);
        result.put("fieldType", fieldType.getSimpleName());
        result.put("codec", codec.getClass().getSimpleName());
        result.put("isFixedLength", codec.isFixedLength());
        result.put("offset", offset);
        result.put("length", codec.length());
        return result.toString();
    }

}
