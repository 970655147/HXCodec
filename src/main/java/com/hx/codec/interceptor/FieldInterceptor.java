package com.hx.codec.interceptor;

/**
 * FieldInterceptor
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:32
 */
public interface FieldInterceptor {

    /**
     * beforeEncode
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void beforeEncode(FieldInterceptContext context) throws Exception;

    /**
     * afterEncode
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void afterEncode(FieldInterceptContext context) throws Exception;

    /**
     * beforeDecode
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void beforeDecode(FieldInterceptContext context) throws Exception;

    /**
     * afterEncode
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void afterDecode(FieldInterceptContext context) throws Exception;

}
