package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;

import java.util.Base64;

/**
 * Base64CodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:28
 */
public final class Base64CodecUtils {
    private Base64CodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return Base64.getEncoder().encode(binaryData);
    }

    public static String encodeBase64String(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }

    public static byte[] encodeBase64(String base64String) {
        return encodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String encodeBase64String(String base64String) {
        return encodeBase64String(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    // ------------------------------------------ assist methods ------------------------------------------

    public static byte[] decodeBase64(byte[] base64Data) {
        return Base64.getDecoder().decode(base64Data);
    }

    public static String decodeBase64String(byte[] base64Data) {
        return new String(Base64.getDecoder().decode(base64Data));
    }

    public static byte[] decodeBase64(String base64String) {
        return Base64.getDecoder().decode(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String decodeBase64String(String base64String) {
        return new String(decodeBase64(base64String));
    }

}
