package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;

/**
 * UrlCodecUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-11-14 11:33
 */
public final class UrlCodecUtils {
    private static URLCodec URL_CODEC = new URLCodec();

    private UrlCodecUtils() {
        AssertUtils.state(false, "can't be instantiate !");
    }

    public static byte[] encode(byte[] bytes) {
        return URL_CODEC.encode(bytes);
    }

    public static byte[] decode(byte[] bytes) {
        try {
            return URLCodec.decodeUrl(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encode(String str, String charset) {
        if (StringUtil.isNullOrEmpty(charset)) {
            try {
                return URL_CODEC.encode(str, charset);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public static String encode(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            try {
                return new String(URL_CODEC.encode(str.getBytes(CodecConstants.DEFAULT_CHARSET)), CodecConstants.DEFAULT_CHARSET);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public static String decode(String str, String charset) {
        if (StringUtil.isNullOrEmpty(str)) {
            try {
                return URL_CODEC.decode(str, charset);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public static String decode(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            try {
                return new String(URL_CODEC.decode(str.getBytes(CodecConstants.DEFAULT_CHARSET)), CodecConstants.DEFAULT_CHARSET);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    public static Object encode(Object obj) throws EncoderException {
        if (obj == null) {
            return null;
        } else if (obj instanceof byte[]) {
            return URL_CODEC.encode((byte[]) ((byte[]) obj));
        } else if (obj instanceof String) {
            return URL_CODEC.encode((String) obj);
        } else {
            throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be URL encoded");
        }
    }

    public static Object decode(Object obj) throws DecoderException {
        if (obj == null) {
            return null;
        } else if (obj instanceof byte[]) {
            return URL_CODEC.decode((byte[]) ((byte[]) obj));
        } else if (obj instanceof String) {
            return URL_CODEC.decode((String) obj);
        } else {
            throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be URL decoded");
        }
    }
}