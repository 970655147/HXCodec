package com.hx.codec.interceptor;

import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.schema.GenericFieldSchema;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * FieldInterceptContext
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 09:52
 */
@NoArgsConstructor
@Data
public class FieldInterceptContext {

    private GenericBeanSchema beanSchema;
    private Object subjectValue;
    private GenericFieldSchema fieldSchema;
    private Object fieldValue;
    private ByteBuf byteBuf;

    // context args
    private Object arg;
    private Object[] args;
    private Map<Object, Object> argsMap;

    /**
     * init
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-10 10:14
     */
    public void init() {
        if (argsMap == null) {
            argsMap = new LinkedHashMap<>();
        }
    }

    public FieldInterceptContext copy() {
        FieldInterceptContext result = new FieldInterceptContext();
        result.beanSchema = beanSchema;
        result.subjectValue = subjectValue;
        result.fieldSchema = fieldSchema;
        result.fieldValue = fieldValue;
        result.byteBuf = byteBuf;
        result.arg = arg;
        result.args = args;
        result.argsMap = argsMap;
        return result;
    }

}
