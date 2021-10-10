package com.hx.codec.schema;

/**
 * SchemaRegistry
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 09:59
 */
public interface SchemaRegistry<T> {

    /**
     * registry
     *
     * @param key        key
     * @param beanSchema beanSchema
     * @return boolean
     * @author Jerry.X.He
     * @date 2021-10-10 10:00
     */
    boolean registry(T key, GenericBeanSchema beanSchema);

    /**
     * lookUp
     *
     * @param key key
     * @return GenericBeanSchema
     * @author Jerry.X.He
     * @date 2021-10-10 10:00
     */
    GenericBeanSchema lookUp(T key);

}
