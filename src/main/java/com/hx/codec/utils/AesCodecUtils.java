package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AesCodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:29
 */
public final class AesCodecUtils {
    private AesCodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static byte[] aesEncrypt(byte[] bytes, byte[] key) {
        return aes(bytes, key, true);
    }

    public static byte[] aesDecrypt(byte[] bytes, byte[] key) {
        return aes(bytes, key, false);
    }

    public static byte[] aesEncrypt(String str, String key) {
        return aes(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), true);
    }

    public static byte[] aesDecrypt(String str, String key) {
        return aes(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), false);
    }

    private static byte[] aes(byte[] bytes, byte[] key, boolean encoding) {
        AssertUtils.state(bytes != null, "'bytes' can't be null !");
        AssertUtils.state(key != null, "'key' can't be null !");

        try {
            String alogrithm = "AES";
            SecretKey securekey = new SecretKeySpec(key, alogrithm);
            Cipher cipher = Cipher.getInstance(alogrithm);
            if (encoding) {
                cipher.init(1, securekey);
            } else {
                cipher.init(2, securekey);
            }

            return cipher.doFinal(bytes);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }
}

