package com.hx.codec.tests;

import com.hx.codec.tests.model.UpConnectReq;
import com.hx.codec.schema.GenericBeanSchema;
import com.hx.codec.utils.AssertUtils;
import org.junit.Test;

/**
 * Test13RuntimeSchema
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 11:21
 */
public class Test13RuntimeSchema extends Test00BaseTests {

    @Test
    public void test01GenericBeanSchema() {

        GenericBeanSchema<UpConnectReq> beanSchemaVer2011 = new GenericBeanSchema<>(UpConnectReq.class, 2011);
        GenericBeanSchema<UpConnectReq> beanSchemaVer2019 = new GenericBeanSchema<>(UpConnectReq.class, 2019);
        GenericBeanSchema<UpConnectReq> beanSchemaVer2013 = new GenericBeanSchema<>(UpConnectReq.class, 2013);

        AssertUtils.state(beanSchemaVer2011.getFieldSchemaList().size() == 5, " unexpected fieldSchemaList");
        AssertUtils.state(beanSchemaVer2019.getFieldSchemaList().size() == 6, " unexpected fieldSchemaList");
        AssertUtils.state(beanSchemaVer2013.getFieldSchemaList().size() == 0, " unexpected fieldSchemaList");

    }

}
