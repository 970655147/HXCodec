package com.hx.codec.codec.factory;

import com.hx.codec.anno.Field;
import com.hx.codec.schema.GenericFieldSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CodecFactoryContext
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 9:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CodecFactoryContext {

    private java.lang.reflect.Field field;
    private Field fieldAnno;
    private GenericFieldSchema fieldSchema;
    private int version;

}
