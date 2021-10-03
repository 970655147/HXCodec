package com.hx.codec.utils;

import io.netty.buffer.ByteBuf;

/**
 * DigestUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/8 16:39
 */
public class DigestUtils {

    // disable constructor
    private DigestUtils() {
        throw new RuntimeException(" can't be instantiate ");
    }

    /**
     * bcc
     *
     * @return byte
     * @author Jerry.X.He
     * @date 2021/9/8 16:40
     */
    public static byte bcc(ByteBuf sourceBuf) {
        ByteBuf byteBuf = sourceBuf.copy();
        byte cs = 0;
        while (byteBuf.isReadable())
            cs ^= byteBuf.readByte();
        return cs;
    }

    /**
     * crc16_CCITT
     *
     * @return short
     * @author Jerry.X.He
     * @date 2021/9/8 16:41
     */
    public static short crc16_CCITT(ByteBuf sourceBuf) {
        int crc = 0xFFFF;
        ByteBuf byteBuf = sourceBuf.copy();
        while (byteBuf.isReadable()) {
            byte b = byteBuf.readByte();
            crc = ((crc >>> 8) | (crc << 8)) & 0xffff;
            crc ^= (b & 0xff);//byte to int, trunc sign
            crc ^= ((crc & 0xff) >> 4);
            crc ^= (crc << 12) & 0xffff;
            crc ^= ((crc & 0xFF) << 5) & 0xffff;
        }
        crc &= 0xffff;
        return (short) crc;
    }

}

