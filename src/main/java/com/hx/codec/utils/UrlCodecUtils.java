package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UrlCodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:33
 */
public final class UrlCodecUtils {

    private UrlCodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static byte[] encode(byte[] bytes) {
        try {
            return URLEncoder
                    .encode(new String(bytes, CodecConstants.DEFAULT_CHARSET), CodecConstants.DEFAULT_CHARSET.name())
                    .getBytes(CodecConstants.DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decode(byte[] bytes) {
        try {
            return URLDecoder
                    .decode(new String(bytes, CodecConstants.DEFAULT_CHARSET), CodecConstants.DEFAULT_CHARSET.name())
                    .getBytes(CodecConstants.DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encode(String str, String charset) {
        try {
            return URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encode(String str) {
        return encode(str, CodecConstants.DEFAULT_CHARSET.name());
    }

    public static String decode(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String str) {
        return decode(str, CodecConstants.DEFAULT_CHARSET.name());
    }

}