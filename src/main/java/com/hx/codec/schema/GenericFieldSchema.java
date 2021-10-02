package com.hx.codec.schema;

import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
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

    public GenericFieldSchema() {
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
