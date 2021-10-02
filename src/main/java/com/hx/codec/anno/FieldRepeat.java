package com.hx.codec.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * FieldRepeatable
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:48
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface FieldRepeat {

    /**
     * values
     *
     * @return
     * @author Jerry.X.He
     * @date 2021/9/28 11:20
     */
    Field[] value();

}
