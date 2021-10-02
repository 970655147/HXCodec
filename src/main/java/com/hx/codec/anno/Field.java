package com.hx.codec.anno;

import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.DataType;
import com.hx.codec.interceptor.FieldInterceptor;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Field
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 16:48
 */
@Repeatable(FieldRepeat.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Field {

    /**
     * name
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:10
     */
    String name();

    /**
     * sort
     *
     * @return int
     * @author Jerry.X.He
     * @date 2021/9/28 11:45
     */
    int sort();

    /**
     * bigEndian
     *
     * @return
     * @author Jerry.X.He
     * @date 2021/9/28 9:59
     */
    boolean bigEndian() default true;

    /**
     * dataType
     *
     * @return DataType
     * @author Jerry.X.He
     * @date 2021/9/23 17:22
     */
    DataType dataType();

    /**
     * lengthInBytes
     *
     * @return int
     * @author Jerry.X.He
     * @date 2021/9/23 17:23
     */
    int lengthInBytes() default -1;

    /**
     * eleLength
     *
     * @return int
     * @author Jerry.X.He
     * @date 2021/9/28 16:36
     */
    int eleLength() default 0;

    /**
     * lengthByteType
     *
     * @return ByteType
     * @author Jerry.X.He
     * @date 2021/9/23 17:24
     */
    ByteType lengthByteType() default ByteType.NONE;

    /**
     * charset
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:27
     */
    String charset() default "utf8";

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
     * interceptorClazz
     *
     * @return Class
     * @author Jerry.X.He
     * @date 2021/9/23 17:30
     */
    Class<? extends FieldInterceptor> interceptorClazz() default FieldInterceptor.class;

    /**
     * desc
     *
     * @return String
     * @author Jerry.X.He
     * @date 2021/9/23 17:21
     */
    int[] version() default {0, 1};

}
