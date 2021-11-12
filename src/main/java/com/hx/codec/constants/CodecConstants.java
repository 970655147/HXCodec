package com.hx.codec.constants;

import com.alibaba.fastjson.JSONArray;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * CodecConstants
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:01
 */
public class CodecConstants {

    // disable constructor
    private CodecConstants() {
        throw new RuntimeException(" can't be instantiate ");
    }

    // defaults
    public static ByteOrder DEFAULT_BYTE_ORDER = ByteOrder.BIG_ENDIAN;
    public static ByteType DEFAULT_LEN_BYTE_TYPE = ByteType.DWORD;
    public static Charset DEFAULT_CHARSET = Charset.forName("gbk");
    public static boolean DEFAULT_INCLUDE_LEN = false;

    // defaults
    public static byte DEFAULT_PADDING_BYTE = 0x00;
    public static byte[] DEFAULT_PADDING_BYTES = new byte[]{0x00};
    public static JSONArray DEFAULT_PADDING_BYTES_ARRAY = new JSONArray();
    public static boolean DEFAULT_PADDING_FIRST = false;
    public static byte DEFAULT_BCD8421_PADDING = 0xf;
    public static byte DEFAULT_ARRAY_WITH_FIXED_LEN_PADDING = 0x00;

    // keys
    public static String KEY_PADDING_BYTE = "paddingByte";
    public static String KEY_PADDING_BYTES = "paddingBytes";
    public static String KEY_PADDING_FIRST = "paddingFirst";
    public static String KEY_FIXED_LENGTH = "fixedLength";
    public static String KEY_CHARSET = "charset";
    public static String KEY_KEY_TYPE = "keyType";
    public static String KEY_VALUE_TYPE = "valueType";

    // byteUnit
    public static int BYTE_UNIT = 1;
    public static int WORD_UNIT = 2;
    public static int DWORD_UNIT = 4;
    public static int QWORD_UNIT = 8;

    // initializer
    static {
        DEFAULT_PADDING_BYTES_ARRAY.add(DEFAULT_PADDING_BYTE);
    }

}
