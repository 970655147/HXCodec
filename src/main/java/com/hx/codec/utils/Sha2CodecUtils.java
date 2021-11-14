package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;
import org.apache.commons.codec.digest.Sha2Crypt;

/**
 * Sha2CodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:32
 */
public final class Sha2CodecUtils {
    private Sha2CodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static String sha256Crypt(byte[] keyBytes) {
        return Sha2Crypt.sha256Crypt(keyBytes);
    }

    public static String sha256Crypt(byte[] keyBytes, String salt) {
        return Sha2Crypt.sha256Crypt(keyBytes, salt);
    }

    public static String sha256Crypt(String keyBytes) {
        return Sha2Crypt.sha256Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha256Crypt(String keyBytes, String salt) {
        return Sha2Crypt.sha256Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET), salt);
    }

    public static String sha512Crypt(byte[] keyBytes) {
        return Sha2Crypt.sha512Crypt(keyBytes);
    }

    public static String sha512Crypt(byte[] keyBytes, String salt) {
        return Sha2Crypt.sha512Crypt(keyBytes, salt);
    }

    public static String sha512Crypt(String keyBytes) {
        return Sha2Crypt.sha512Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha512Crypt(String keyBytes, String salt) {
        return Sha2Crypt.sha512Crypt(keyBytes.getBytes(CodecConstants.DEFAULT_CHARSET), salt);
    }
}
