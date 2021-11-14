package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;
import org.apache.commons.codec.digest.Md5Crypt;

/**
 * Md5CodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:36
 */
public final class Md5CodecUtils {
    private Md5CodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static String apr1Crypt(byte[] keyBytes) {
        return Md5Crypt.apr1Crypt(keyBytes);
    }

    public static String apr1Crypt(byte[] keyBytes, String salt) {
        return Md5Crypt.apr1Crypt(keyBytes, salt);
    }

    public static String apr1Crypt(String keyBytes) {
        return Md5Crypt.apr1Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String apr1Crypt(String keyBytes, String salt) {
        return Md5Crypt.apr1Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET), salt);
    }

    public static String md5Crypt(byte[] keyBytes) {
        return Md5Crypt.md5Crypt(keyBytes);
    }

    public static String md5Crypt(byte[] keyBytes, String salt) {
        return Md5Crypt.md5Crypt(keyBytes, salt);
    }

    public static String md5Crypt(byte[] keyBytes, String salt, String prefix) {
        return Md5Crypt.md5Crypt(keyBytes, salt, prefix);
    }

    public static String md5Crypt(String keyBytes, String salt, String prefix) {
        return Md5Crypt.md5Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET), salt, prefix);
    }

    public static String md5Crypt(String keyBytes) {
        return Md5Crypt.md5Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String md5Crypt(String keyBytes, String salt) {
        return Md5Crypt.md5Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET), salt);
    }
}
