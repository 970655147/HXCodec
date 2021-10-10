package com.hx.codec.schema;

import com.hx.codec.anno.Field;
import com.hx.codec.anno.FieldRepeat;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.utils.AssertUtils;
import com.hx.codec.utils.CodecUtils;
import lombok.Data;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * GenericBeanSchema
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:48
 */
@Data
public class GenericBeanSchema<T> {

    // clazz
    private Class<T> clazz;
    // version
    private int version;
    // fieldSchemaList
    private List<GenericFieldSchema> fieldSchemaList;
    // isFixedLength
    private boolean isFixedLength;
    // length
    private int length;

    public GenericBeanSchema(Class<T> clazz, int version) {
        this.clazz = clazz;
        this.version = version;
        init();
    }

    /**
     * lookupFieldSchema
     *
     * @return com.hx.codec.schema.GenericFieldSchema
     * @author Jerry.X.He
     * @date 2021/9/28 11:42
     */
    public GenericFieldSchema lookupFieldSchema(String fieldName) {
        for (GenericFieldSchema fieldSchema : fieldSchemaList) {
            if (fieldSchema.getFieldName().equals(fieldName)) {
                return fieldSchema;
            }
        }
        return null;
    }

    // ------------------------------------------ assist methods ------------------------------------------

    /**
     * init
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/27 17:25
     */
    private void init() {
        try {
            Map<String, GenericFieldSchema> name2FieldSchema = collectAllFieldSchema();
            initFieldSchema(name2FieldSchema);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * collectAllFieldSchema
     *
     * @return java.util.Map<java.lang.String, com.hx.codec.schema.GenericFieldSchema>
     * @author Jerry.X.He
     * @date 2021/9/28 14:03
     */
    private Map<String, GenericFieldSchema> collectAllFieldSchema() throws Exception {
        Map<String, GenericFieldSchema> name2FieldSchema = new LinkedHashMap<>();
        // read @Field from readerMethod
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : properties) {
            Method readMethod = property.getReadMethod();
            if (readMethod == null) {
                continue;
            }
            if (!readMethod.isAnnotationPresent(Field.class) && !readMethod.isAnnotationPresent(FieldRepeat.class)) {
                continue;
            }

            String fieldName = property.getName();
            java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
            Field fieldAnno = readMethod.getDeclaredAnnotation(Field.class);
            FieldRepeat fieldRepeatAnno = readMethod.getDeclaredAnnotation(FieldRepeat.class);
            Field fieldAnnoToUse = getFieldAnnoByVersion(fieldAnno, fieldRepeatAnno, version);
            if (fieldAnnoToUse != null) {
                name2FieldSchema.put(field.getName(), createFieldSchema(clazz, field, fieldAnnoToUse));
            }
        }

        // read @Field from field
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            if (!field.isAnnotationPresent(Field.class) || !field.isAnnotationPresent(FieldRepeat.class)) {
                continue;
            }

            Field fieldAnno = field.getDeclaredAnnotation(Field.class);
            FieldRepeat fieldRepeatAnno = field.getDeclaredAnnotation(FieldRepeat.class);
            Field fieldAnnoToUse = getFieldAnnoByVersion(fieldAnno, fieldRepeatAnno, version);
            if (fieldAnnoToUse != null) {
                name2FieldSchema.put(field.getName(), createFieldSchema(clazz, field, fieldAnnoToUse));
            }
        }
        return name2FieldSchema;
    }

    /**
     * initFieldSchema
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/28 14:04
     */
    private void initFieldSchema(Map<String, GenericFieldSchema> name2FieldSchema) throws Exception {
        // init fieldSchemaList
        isFixedLength = true;
        fieldSchemaList = new ArrayList<>();
        for (Map.Entry<String, GenericFieldSchema> entry : name2FieldSchema.entrySet()) {
            GenericFieldSchema fieldSchema = entry.getValue();
            fieldSchemaList.add(fieldSchema);

            length += fieldSchema.getCodec().length();
            if (!fieldSchema.getCodec().isFixedLength()) {
                isFixedLength = false;
            }
        }
        fieldSchemaList.sort((e1, e2) -> e1.getFieldAnno().sort() - e2.getFieldAnno().sort());

        // calc offset
        int offset = 0;
        for (GenericFieldSchema fieldSchema : fieldSchemaList) {
            fieldSchema.setOffset(offset);
            offset += fieldSchema.getCodec().length();

            fieldSchema.init();
        }
    }

    /**
     * getFieldAnnoByVersion
     *
     * @return com.hx.codec.anno.Field
     * @author Jerry.X.He
     * @date 2021/9/28 11:02
     */
    private Field getFieldAnnoByVersion(Field fieldAnno, FieldRepeat fieldRepeatAnno, int version) {
        if (fieldAnno != null) {
            if (Arrays.binarySearch(fieldAnno.version(), version) >= 0) {
                return fieldAnno;
            }
        }

        if (fieldRepeatAnno != null) {
            for (Field fieldAnnoEle : fieldRepeatAnno.value()) {
                if (Arrays.binarySearch(fieldAnnoEle.version(), version) >= 0) {
                    return fieldAnnoEle;
                }
            }
        }
        return null;
    }

    /**
     * createFieldSchema
     *
     * @return com.hx.codec.schema.GenericFieldSchema
     * @author Jerry.X.He
     * @date 2021/9/27 17:56
     */
    private GenericFieldSchema createFieldSchema(Class<T> clazz, java.lang.reflect.Field field, Field fieldAnno) {
        GenericFieldSchema result = new GenericFieldSchema();
        result.setFieldName(field.getName());
        result.setFieldType(field.getType());
        result.setFieldAnno(fieldAnno);
        result.setField(field);
        result.setGetterMethod(findGetterMethod(clazz, field));
        result.setSetterMethod(findSetterMethod(clazz, field));
        result.init();

        // use customCodeFactory first or else use default configuration
        AbstractCodecFactory codecFactory = result.getCodecFactory();
        if (codecFactory == null) {
            codecFactory = CodecUtils.lookupCodecFactoryByDataType(fieldAnno.dataType());
        }
        CodecFactoryContext codecFactoryContext = new CodecFactoryContext(field, fieldAnno, result, version);
        AbstractCodec codec = codecFactory.create(codecFactoryContext);
        result.setCodec(codec);
        return result;
    }

    /**
     * findGetterMethod
     *
     * @return java.lang.reflect.Method
     * @author Jerry.X.He
     * @date 2021/9/28 13:49
     */
    private Method findGetterMethod(Class<T> clazz, java.lang.reflect.Field field) {
        List<String> getterPrefix = Arrays.asList("get", "is", "has");
        try {
            for (String prefix : getterPrefix) {
                String methodName = prefix + CodecUtils.upperCaseFirstChar(field.getName());
                Method method = clazz.getMethod(methodName);
                return method;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " could not find getter for class : " + clazz.getName() + ", field : " + field.getName());
            return null;
        }
    }

    /**
     * findSetterMethod
     *
     * @return java.lang.reflect.Method
     * @author Jerry.X.He
     * @date 2021/9/28 13:49
     */
    private Method findSetterMethod(Class<T> clazz, java.lang.reflect.Field field) {
        List<String> getterPrefix = Arrays.asList("set", "is", "has");
        try {
            for (String prefix : getterPrefix) {
                String methodName = prefix + CodecUtils.upperCaseFirstChar(field.getName());
                Method method = clazz.getMethod(methodName, field.getType());
                return method;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            AssertUtils.state(false, " could not find setter for class : " + clazz.getName() + ", field : " + field.getName());
            return null;
        }
    }

}
