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
     * beforeProcess
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void beforeProcess();

    /**
     * afterProcess
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 17:32
     */
    void afterProcess();
}
