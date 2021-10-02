package com.hx.codec.utils;

import com.hx.codec.constants.Constants;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static com.hx.codec.constants.Constants.CHARSET_UTF8;

/**
 * SockUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/4/16 10:28
 */
public class SocketUtils {

    // disable constructor
    private SocketUtils() {
        throw new RuntimeException(" can't be instantiate ");
    }

    /**
     * 获取给定的 Socket 中的内容
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/4/13 11:45
     */
    public static String readFromSocket(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            return IoUtils.readStringFromInputStream(inputStream, Constants.STRING_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向 socket 写出数据
     *
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021/4/14 9:53
     */
    public static void writeToSocket(Socket socket, String message) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes(CHARSET_UTF8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToSocket(Socket socket, byte[] bytes) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 刷出 socket 待写的数据
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/4/14 10:17
     */
    public static void flushSocket(Socket socket) {
        try {
            socket.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

