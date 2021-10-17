package com.hx.codec.interceptor.state;

import com.hx.codec.interceptor.FieldInterceptContext;
import com.hx.codec.interceptor.FieldInterceptor;

/**
 * LastFieldContextInterceptor
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-17 11:03
 */
public class LastFieldContextInterceptor implements FieldInterceptor {

    // lastContext
    public static FieldInterceptContext LAST_CONTEXT;

    @Override
    public void beforeEncode(FieldInterceptContext context) throws Exception {
        LAST_CONTEXT = context.copy();
    }

    @Override
    public void afterEncode(FieldInterceptContext context) throws Exception {

    }

    @Override
    public void beforeDecode(FieldInterceptContext context) throws Exception {

    }

    @Override
    public void afterDecode(FieldInterceptContext context) throws Exception {

    }

}
