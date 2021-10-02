package com.hx.codec.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Properties;

/**
 * PropertyUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/13 11:08
 */
public class PropertyUtils {

    /**
     * 从给定的文件, inputStream, reader 读取 properties
     *
     * @return java.util.Properties
     * @author Jerry.X.He
     * @date 2021/4/13 11:13
     */
    public static Properties loadProperties(String path) {
        Properties result = new Properties();
        try {
            result.load(new FileInputStream(new File(path)));
        } catch (Exception e) {
            throw new RuntimeException(" load properties from path : " + path + " failed ");
        }
        return result;
    }

    public static Properties loadProperties(File file) {
        Properties result = new Properties();
        try {
            result.load(new FileInputStream(file));
        } catch (Exception e) {
            throw new RuntimeException(" load properties from file : " + file.getAbsolutePath() + " failed ");
        }
        return result;
    }

    public static Properties loadProperties(InputStream inputStream) {
        Properties result = new Properties();
        try {
            result.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(" load properties from inputStream failed ");
        }
        return result;
    }

    public static Properties loadProperties(Reader reader) {
        Properties result = new Properties();
        try {
            result.load(reader);
        } catch (Exception e) {
            throw new RuntimeException(" load properties from reader failed ");
        }
        return result;
    }

    /**
     * properties 确保 key 存在, 并且格式正常
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/4/14 10:59
     */
    public static void requiredProperties(Map map, String key) {
        Object value = map.get(key);
        if (value == null) {
            throw new RuntimeException(String.format(" the key %s is required ", key));
        }
    }

    public static void requiredIntProperties(Map map, String key) {
        Object value = map.get(key);
        if (value == null) {
            throw new RuntimeException(String.format(" the int property key %s is required ", key));
        }

        try {
            Integer.valueOf(String.valueOf(value));
        } catch (Exception e) {
            throw new RuntimeException(String.format(" the int property key %s is not valid ", key));
        }
    }

    public static void requiredLongProperties(Map map, String key) {
        Object value = map.get(key);
        if (value == null) {
            throw new RuntimeException(String.format(" the long property key %s is required ", key));
        }

        try {
            Long.valueOf(String.valueOf(value));
        } catch (Exception e) {
            throw new RuntimeException(String.format(" the long property key %s is not valid ", key));
        }
    }

}
