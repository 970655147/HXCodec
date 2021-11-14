package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * DesCodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:29
 */
public final class DesCodecUtils {
    private DesCodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static byte[] desEncrypt(byte[] bytes, byte[] key) {
        return des(bytes, key, true);
    }

    public static byte[] desDecrypt(byte[] bytes, byte[] key) {
        return des(bytes, key, false);
    }

    public static byte[] desEncrypt(String str, String key) {
        return des(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), true);
    }

    public static byte[] desDecrypt(String str, String key) {
        return des(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), false);
    }

    public static byte[] tripleDesEncrypt(byte[] bytes, byte[] key) {
        return tripleDes(bytes, key, true);
    }

    public static byte[] tripleDesDecrypt(byte[] bytes, byte[] key) {
        return tripleDes(bytes, key, false);
    }

    public static byte[] tripleDesEncrypt(String str, String key) {
        return tripleDes(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), true);
    }

    public static byte[] tripleDesDecrypt(String str, String key) {
        return tripleDes(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), false);
    }

    public static byte[] tripleDesECBEncrypt(byte[] bytes, byte[] key) {
        return tripleDesECB(bytes, key, true);
    }

    public static byte[] tripleDesECBDecrypt(byte[] bytes, byte[] key) {
        return tripleDesECB(bytes, key, false);
    }

    public static byte[] tripleDesECBEncrypt(String str, String key) {
        return tripleDesECB(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), true);
    }

    public static byte[] tripleDesECBDecrypt(String str, String key) {
        return tripleDesECB(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), false);
    }

    public static byte[] tripleDesCBCEncrypt(byte[] bytes, byte[] key) {
        return tripleDesCBC(bytes, key, true);
    }

    public static byte[] tripleDesCBCDecrypt(byte[] bytes, byte[] key) {
        return tripleDesCBC(bytes, key, false);
    }

    public static byte[] tripleDesCBCEncrypt(String str, String key) {
        return tripleDesCBC(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), true);
    }

    public static byte[] tripleDesCBCDecrypt(String str, String key) {
        return tripleDesCBC(str.getBytes(CodecConstants.DEFAULT_CHARSET), key.getBytes(CodecConstants.DEFAULT_CHARSET), false);
    }

    private static byte[] des(byte[] bytes, byte[] key, boolean encoding) {
        AssertUtils.state(bytes != null, "'bytes' can't be null !");
        AssertUtils.state(key != null, "'key' can't be null !");

        try {
            String alogrithm = "DES";
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alogrithm);
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance(alogrithm);
            if (encoding) {
                cipher.init(1, securekey, random);
            } else {
                cipher.init(2, securekey, random);
            }

            return cipher.doFinal(bytes);
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    private static byte[] tripleDes(byte[] bytes, byte[] key, boolean encoding) {
        AssertUtils.state(bytes != null, "'bytes' can't be null !");
        AssertUtils.state(key != null, "'key' can't be null !");

        try {
            String alogrithm = "DESede";
            SecretKey deskey = new SecretKeySpec(key, alogrithm);
            Cipher cipher = Cipher.getInstance(alogrithm);
            if (encoding) {
                cipher.init(1, deskey);
            } else {
                cipher.init(2, deskey);
            }

            return cipher.doFinal(bytes);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    private static byte[] tripleDesECB(byte[] bytes, byte[] key, boolean encoding) {
        AssertUtils.state(bytes != null, "'bytes' can't be null !");
        AssertUtils.state(key != null, "'key' can't be null !");

        try {
            String alogrithm = "DESede";
            SecretKey deskey = new SecretKeySpec(key, alogrithm);
            Cipher cipher = Cipher.getInstance(alogrithm + "/ECB/PKCS5Padding");
            if (encoding) {
                cipher.init(1, deskey);
            } else {
                cipher.init(2, deskey);
            }

            return cipher.doFinal(bytes);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }

    private static byte[] tripleDesCBC(byte[] bytes, byte[] key, boolean encoding) {
        AssertUtils.state(bytes != null, "'bytes' can't be null !");
        AssertUtils.state(key != null, "'key' can't be null !");

        try {
            String alogrithm = "DESede";
            SecretKey deskey = new SecretKeySpec(key, alogrithm);
            Cipher cipher = Cipher.getInstance(alogrithm + "/CBC/PKCS5Padding");
            if (encoding) {
                cipher.init(1, deskey);
            } else {
                cipher.init(2, deskey);
            }

            return cipher.doFinal(bytes);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }
}
