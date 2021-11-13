package com.hx.codec.anno;

import com.hx.codec.codec.factory.AbstractCodecFactory;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Entity
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-11-13 19:32
 */
@Repeatable(EntityRepeat.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface Entity {

    /**
     * name
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:10
     */
    String name();

    /**
     * desc
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:21
     */
    String desc() default "";

    /**
     * args
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:28
     */
    String args() default "";

    /**
     * codecFactoryClazz
     *
     * @return Class
     * @author Jerry.X.He
     * @date 2021-10-10 17:02
     */
    Class<? extends AbstractCodecFactory> codecFactoryClazz() default AbstractCodecFactory.class;

    /**
     * desc
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:21
     */
    int[] version() default {0, 1};

}
