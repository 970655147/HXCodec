package com.hx.codec.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * EntityRepeat
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-11-13 19:32
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface EntityRepeat {

    /**
     * values
     *
     * @return
     * @author Jerry.X.He
     * @date 2021/9/28 11:20
     */
    Entity[] value();

}
