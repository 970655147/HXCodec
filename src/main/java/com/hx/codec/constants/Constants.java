package com.hx.codec.constants;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

/**
 * Constants
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/14 14:44
 */
public class Constants {

    // disable constructor
    private Constants() {
        throw new RuntimeException(" can't be instantiate ");
    }

    // utf8
    public static String STRING_UTF8 = "utf8";
    public static String STRING_GBK = "gbk";
    public static Charset CHARSET_UTF8 = Charset.forName("utf8");
    public static Charset CHARSET_GBK = Charset.forName("gbk");

    // yyyy-MM-dd HH:mm:ss
    public static SimpleDateFormat YMD_HMS_DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // yyyy-MM-dd
    public static SimpleDateFormat YMD_DF = new SimpleDateFormat("yyyy-MM-dd");

}
