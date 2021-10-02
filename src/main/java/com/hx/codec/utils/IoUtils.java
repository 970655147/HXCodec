package com.hx.codec.utils;

import java.io.*;

import static com.hx.codec.constants.Constants.CHARSET_UTF8;

/**
 * SockUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/13 13:59
 */
public class IoUtils {

    // disable constructor
    private IoUtils() {
        throw new RuntimeException(" can't be instantiate ");
    }

    /**
     * 获取给定的 inputStream 中的内容
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/4/13 11:45
     */
    public static byte[] readBytesFromInputStream(InputStream is, String charset) {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // do not use is.available
            int bufferLen = -1;
            while ((bufferLen = is.read(buffer)) > 0) {
                baos.write(buffer, 0, bufferLen);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String readStringFromInputStream(InputStream is, String charset) {
        byte[] buffer = new byte[1024];
        StringBuilder sb = new StringBuilder();

        try {
            int bufferLen = -1;
            while ((bufferLen = is.read(buffer)) > 0) {
                sb.append(new String(buffer, 0, bufferLen, charset));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向 outputStream 写出数据
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/4/14 9:53
     */
    public static void writeToOutputStream(OutputStream outputStream, String message) {
        try {
            outputStream.write(message.getBytes(CHARSET_UTF8));
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(File file, String message) {
        try (OutputStream outputStream = new FileOutputStream(file, true)) {
            writeToOutputStream(outputStream, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String file, String message) {
        writeToFile(new File(file), message);
    }

    /**
     * sleep N ms
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/4/13 16:28
     */
    public static void sleep(long sleepInMs) {
        try {
            Thread.sleep(sleepInMs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copyStream
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/4/16 14:35
     */
    public static void copyStream(InputStream is, OutputStream os) {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);

        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

