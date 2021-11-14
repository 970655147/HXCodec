package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;
import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;

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

    public static boolean isArrayByteBase64(byte[] arrayOctet) {
        return Base64.isArrayByteBase64(arrayOctet);
    }

    public static boolean isBase64(byte octet) {
        return Base64.isBase64(octet);
    }

    public static boolean isBase64(String base64) {
        return Base64.isBase64(base64.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static boolean isBase64(byte[] arrayOctet) {
        return Base64.isBase64(arrayOctet);
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return Base64.encodeBase64(binaryData);
    }

    public static String encodeBase64String(byte[] binaryData) {
        return Base64.encodeBase64String(binaryData);
    }

    public static byte[] encodeBase64URLSafe(byte[] binaryData) {
        return Base64.encodeBase64URLSafe(binaryData);
    }

    public static String encodeBase64URLSafeString(byte[] binaryData) {
        return Base64.encodeBase64URLSafeString(binaryData);
    }

    public static byte[] encodeBase64Chunked(byte[] binaryData) {
        return Base64.encodeBase64Chunked(binaryData);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        return Base64.encodeBase64(binaryData, isChunked);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe) {
        return Base64.encodeBase64(binaryData, isChunked, urlSafe);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked, boolean urlSafe, int maxResultSize) {
        return Base64.encodeBase64(binaryData, isChunked, urlSafe, maxResultSize);
    }

    public static byte[] decodeBase64(String base64String) {
        return Base64.decodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        return Base64.decodeBase64(base64Data);
    }

    public static byte[] encodeInteger(BigInteger bigInt) {
        return Base64.encodeInteger(bigInt);
    }

    public static BigInteger decodeInteger(byte[] pArray) {
        return Base64.decodeInteger(pArray);
    }

    public static byte[] encodeBase64(String base64String) {
        return Base64.encodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String encodeBase64String(String base64String) {
        return Base64.encodeBase64String(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] encodeBase64URLSafe(String base64String) {
        return Base64.encodeBase64URLSafe(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String encodeBase64URLSafeString(String base64String) {
        return Base64.encodeBase64URLSafeString(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] encodeBase64Chunked(String base64String) {
        return Base64.encodeBase64Chunked(base64String.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] encodeBase64(String base64String, boolean isChunked) {
        return Base64.encodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET), isChunked);
    }

    public static byte[] encodeBase64(String base64String, boolean isChunked, boolean urlSafe) {
        return Base64.encodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET), isChunked, urlSafe);
    }

    public static byte[] encodeBase64(String base64String, boolean isChunked, boolean urlSafe, int maxResultSize) {
        return Base64.encodeBase64(base64String.getBytes(CodecConstants.DEFAULT_CHARSET), isChunked, urlSafe, maxResultSize);
    }
}
