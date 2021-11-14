package com.hx.codec.utils;

import com.hx.codec.constants.CodecConstants;
import io.netty.buffer.ByteBuf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/**
 * DigestUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/8 16:39
 */
public class DigestUtils {

    //CRC_16_X_16_15_2_1_TABLES
    private static final int[] CRC_16_X_16_15_2_1_TABLES = {
            0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241, 0xC601, 0x06C0, 0x0780, 0xC741,
            0x0500, 0xC5C1, 0xC481, 0x0440, 0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81, 0x0E40, 0x0A00, 0xCAC1, 0xCB81, 0x0B40,
            0xC901, 0x09C0, 0x0880, 0xC841, 0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81, 0x1A40, 0x1E00, 0xDEC1, 0xDF81, 0x1F40,
            0xDD01, 0x1DC0, 0x1C80, 0xDC41, 0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641, 0xD201, 0x12C0, 0x1380, 0xD341,
            0x1100, 0xD1C1, 0xD081, 0x1040, 0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240, 0x3600, 0xF6C1, 0xF781, 0x3740,
            0xF501, 0x35C0, 0x3480, 0xF441, 0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80, 0xFE41, 0xFA01, 0x3AC0, 0x3B80, 0xFB41,
            0x3900, 0xF9C1, 0xF881, 0x3840, 0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80, 0xEA41, 0xEE01, 0x2EC0, 0x2F80, 0xEF41,
            0x2D00, 0xEDC1, 0xEC81, 0x2C40, 0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640, 0x2200, 0xE2C1, 0xE381, 0x2340,
            0xE101, 0x21C0, 0x2080, 0xE041, 0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240, 0x6600, 0xA6C1, 0xA781, 0x6740,
            0xA501, 0x65C0, 0x6480, 0xA441, 0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41, 0xAA01, 0x6AC0, 0x6B80, 0xAB41,
            0x6900, 0xA9C1, 0xA881, 0x6840, 0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80, 0xBA41, 0xBE01, 0x7EC0, 0x7F80, 0xBF41,
            0x7D00, 0xBDC1, 0xBC81, 0x7C40, 0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640, 0x7200, 0xB2C1, 0xB381, 0x7340,
            0xB101, 0x71C0, 0x7080, 0xB041, 0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241, 0x9601, 0x56C0, 0x5780, 0x9741,
            0x5500, 0x95C1, 0x9481, 0x5440, 0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40, 0x5A00, 0x9AC1, 0x9B81, 0x5B40,
            0x9901, 0x59C0, 0x5880, 0x9841, 0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81, 0x4A40, 0x4E00, 0x8EC1, 0x8F81, 0x4F40,
            0x8D01, 0x4DC0, 0x4C80, 0x8C41, 0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4680, 0x8641, 0x8201, 0x42C0, 0x4380, 0x8341,
            0x4100, 0x81C1, 0x8081, 0x4040
    };

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

    /**
     * crc16_X16_15_2_1
     *
     * @param sourceBuf sourceBuf
     * @return byte[]
     * @author Jerry.X.He
     * @date 2021-10-16 11:02
     */
    public static short crc16_X16_15_2_1(ByteBuf sourceBuf) {
        int crc = 0x0000;
        ByteBuf byteBuf = sourceBuf.copy();
        while (byteBuf.isReadable()) {
            byte b = byteBuf.readByte();
            crc = (crc >>> 8) ^ CRC_16_X_16_15_2_1_TABLES[((crc ^ b) & 0xff)];
        }
        return (short) (((0xff & crc) << 8) | ((0xff00 & crc) >> 8));
//        return new byte[] { (byte) (0xff & crc), (byte) ((0xff00 & crc) >> 8) };
    }

    /**
     * crc16_X16_15_2_1_calc
     * 1000 0000 0000 0101 还要按照高低位颠倒以后得到1010 0000 0000 0001 即 A001
     *
     * @param sourceBuf sourceBuf
     * @return byte[]
     * @author Jerry.X.He
     * @date 2021-10-16 11:02
     */
    public static short crc16_X16_15_2_1_calc(ByteBuf sourceBuf) {
        int crc = 0xffff;
        ByteBuf byteBuf = sourceBuf.copy();
        while (byteBuf.isReadable()) {
            byte b = byteBuf.readByte();
            if (b < 0) {
                b += 256;
            }
            crc ^= (b & 0xff);
            for (int i = 0; i < 8; i++) {
                boolean isLastBitOne = ((crc & 0x01) == 0x01);
                crc >>>= 1;
                if (isLastBitOne) {
                    crc ^= 0xA001;
                }
            }
        }
        return (short) (((0xff & crc) << 8) | ((0xff00 & crc) >> 8));
    }

    // ------------------------------------------ DigestUtils methods ------------------------------------------

    public static byte[] digest(MessageDigest messageDigest, byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(messageDigest, data);
    }

    public static byte[] digest(MessageDigest messageDigest, ByteBuffer data) {
        return org.apache.commons.codec.digest.DigestUtils.digest(messageDigest, data);
    }

    public static byte[] digest(MessageDigest messageDigest, File data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.digest(messageDigest, data);
    }

    public static byte[] digest(MessageDigest messageDigest, InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.digest(messageDigest, data);
    }

    public static MessageDigest getDigest(String algorithm) {
        return org.apache.commons.codec.digest.DigestUtils.getDigest(algorithm);
    }

    public static MessageDigest getDigest(String algorithm, MessageDigest defaultMessageDigest) {
        return org.apache.commons.codec.digest.DigestUtils.getDigest(algorithm, defaultMessageDigest);
    }

    public static MessageDigest getMd2Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getMd2Digest();
    }

    public static MessageDigest getMd5Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getMd5Digest();
    }

    public static MessageDigest getSha1Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getSha1Digest();
    }

    public static MessageDigest getSha256Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getSha256Digest();
    }

    public static MessageDigest getSha384Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getSha384Digest();
    }

    public static MessageDigest getSha512Digest() {
        return org.apache.commons.codec.digest.DigestUtils.getSha512Digest();
    }

    public static MessageDigest getShaDigest() {
        return org.apache.commons.codec.digest.DigestUtils.getShaDigest();
    }

    public static byte[] md2(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.md2(data);
    }

    public static byte[] md2(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.md2(data);
    }

    public static byte[] md2(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md2(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String md2Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.md2Hex(data);
    }

    public static String md2Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.md2Hex(data);
    }

    public static String md2Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md2Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] md5(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.md5(data);
    }

    public static byte[] md5(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.md5(data);
    }

    public static byte[] md5(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md5(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String md5Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
    }

    public static String md5Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
    }

    public static String md5Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] sha(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha(data);
    }

    public static byte[] sha(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha(data);
    }

    public static byte[] sha(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] sha1(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha1(data);
    }

    public static byte[] sha1(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha1(data);
    }

    public static byte[] sha1(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha1(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha1Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(data);
    }

    public static String sha1Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(data);
    }

    public static String sha1Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] sha256(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha256(data);
    }

    public static byte[] sha256(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha256(data);
    }

    public static byte[] sha256(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha256(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha256Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(data);
    }

    public static String sha256Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(data);
    }

    public static String sha256Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] sha384(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha384(data);
    }

    public static byte[] sha384(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha384(data);
    }

    public static byte[] sha384(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha384(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha384Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha384Hex(data);
    }

    public static String sha384Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha384Hex(data);
    }

    public static String sha384Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha384Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static byte[] sha512(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha512(data);
    }

    public static byte[] sha512(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha512(data);
    }

    public static byte[] sha512(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha512(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String sha512Hex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(data);
    }

    public static String sha512Hex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(data);
    }

    public static String sha512Hex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.sha512Hex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static String shaHex(byte[] data) {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data);
    }

    public static String shaHex(InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data);
    }

    public static String shaHex(String data) {
        return org.apache.commons.codec.digest.DigestUtils.shaHex(data.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, byte[] valueToDigest) {
        return org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest, valueToDigest);
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, ByteBuffer valueToDigest) {
        return org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest, valueToDigest);
    }

    public static MessageDigest updateDigest(MessageDigest digest, File data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.updateDigest(digest, data);
    }

    public static MessageDigest updateDigest(MessageDigest digest, InputStream data) throws IOException {
        return org.apache.commons.codec.digest.DigestUtils.updateDigest(digest, data);
    }

    public static MessageDigest updateDigest(MessageDigest messageDigest, String valueToDigest) {
        return org.apache.commons.codec.digest.DigestUtils.updateDigest(messageDigest, valueToDigest.getBytes(CodecConstants.DEFAULT_CHARSET));
    }

    public static boolean isAvailable(String messageDigestAlgorithm) {
        return org.apache.commons.codec.digest.DigestUtils.isAvailable(messageDigestAlgorithm);
    }

}

