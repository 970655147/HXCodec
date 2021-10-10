package com.hx.codec.tests.interceptor;

import com.alibaba.fastjson.JSON;
import com.hx.codec.interceptor.FieldInterceptContext;
import com.hx.codec.interceptor.FieldInterceptor;
import com.hx.codec.utils.AssertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * VerifyFieldValueFieldInterceptor
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 10:22
 */
public class VerifyFieldValueFieldInterceptor implements FieldInterceptor {

    public static final Logger LOGGER = LoggerFactory.getLogger(VerifyFieldValueFieldInterceptor.class);

    @Override
    public void beforeEncode(FieldInterceptContext context) throws Exception {
        Object entity = context.getSubjectValue();
        Object fieldValue = context.getFieldSchema().getGetterMethod().invoke(entity);
        LOGGER.info(" beforeEncode fieldName : {}, fieldValue : {} ", context.getFieldSchema().getFieldName(), JSON.toJSONString(context.getFieldValue()));
        AssertUtils.state(fieldValue.equals(context.getFieldValue()), " unexpected value ");
    }

    @Override
    public void afterEncode(FieldInterceptContext context) throws Exception {
        Object entity = context.getSubjectValue();
        Object fieldValue = context.getFieldSchema().getGetterMethod().invoke(entity);
        LOGGER.info(" afterEncode fieldName : {}, fieldValue : {} ", context.getFieldSchema().getFieldName(), JSON.toJSONString(context.getFieldValue()));
        AssertUtils.state(fieldValue.equals(context.getFieldValue()), " unexpected value ");
    }

    @Override
    public void beforeDecode(FieldInterceptContext context) throws Exception {
        Object entity = context.getSubjectValue();
        Object fieldValue = context.getFieldSchema().getGetterMethod().invoke(entity);
        LOGGER.info(" beforeDecode fieldName : {}, fieldValue : {} ", context.getFieldSchema().getFieldName(), JSON.toJSONString(context.getFieldValue()));
        AssertUtils.state(fieldValue == null, " unexpected value ");
        AssertUtils.state(context.getFieldValue() == null, " unexpected value ");
    }

    @Override
    public void afterDecode(FieldInterceptContext context) throws Exception {
        Object entity = context.getSubjectValue();
        Object fieldValue = context.getFieldSchema().getGetterMethod().invoke(entity);
        LOGGER.info(" afterDecode fieldName : {}, fieldValue : {} ", context.getFieldSchema().getFieldName(), JSON.toJSONString(context.getFieldValue()));

        if (context.getFieldSchema().getFieldName().equals("dummyField")) {
            return;
        }
        AssertUtils.state(fieldValue.equals(context.getFieldValue()), " unexpected value ");
    }

}
