package com.hx.codec.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSONUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/28 10:17
 */
public class JSONUtils {

    /**
     * getByteOrDefault
     *
     * @return byte
     * @author Jerry.X.He
     * @date 2021/9/28 10:18
     */
    public static Byte getByteOrDefault(JSONObject obj, String key, Byte defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Byte result = obj.getByte(key);
        return result == null ? defaultValue : result;
    }

    public static Short getShortOrDefault(JSONObject obj, String key, Short defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Short result = obj.getShort(key);
        return result == null ? defaultValue : result;
    }

    public static Boolean getBooleanOrDefault(JSONObject obj, String key, Boolean defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Boolean result = obj.getBoolean(key);
        return result == null ? defaultValue : result;
    }

    public static Integer getIntegerOrDefault(JSONObject obj, String key, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Integer result = obj.getInteger(key);
        return result == null ? defaultValue : result;
    }

    public static Long getLongOrDefault(JSONObject obj, String key, Long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Long result = obj.getLong(key);
        return result == null ? defaultValue : result;
    }

    public static Float getFloatOrDefault(JSONObject obj, String key, Float defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Float result = obj.getFloat(key);
        return result == null ? defaultValue : result;
    }

    public static Double getDoubleOrDefault(JSONObject obj, String key, Double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        Double result = obj.getDouble(key);
        return result == null ? defaultValue : result;
    }

    public static String getStringOrDefault(JSONObject obj, String key, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String result = obj.getString(key);
        return result == null ? defaultValue : result;
    }

    public static JSONObject getJSONObjectOrDefault(JSONObject obj, String key, JSONObject defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        JSONObject result = obj.getJSONObject(key);
        return result == null ? defaultValue : result;
    }

    public static JSONArray getJSONArrayOrDefault(JSONObject obj, String key, JSONArray defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        JSONArray result = obj.getJSONArray(key);
        return result == null ? defaultValue : result;
    }

}
