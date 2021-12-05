package com.hx.codec.utils;

import com.hx.codec.constants.ByteType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Pooled;

import java.nio.ByteOrder;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * ByteBufUtils
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-12-05 10:32
 */
public class ByteBufUtils {

    /**
     * writeLen
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 15:03
     */
    public static void writeLen(ByteType byteType, ByteOrder byteOrder, int len, ByteBuf buf) {
        if (byteType == ByteType.BYTE) {
            AssertUtils.state(len <= CodecUtils.maxLenCouldHold(byteType), " length overflow : " + len);
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                buf.writeByte(len);
            } else {
                buf.writeByte(len);
            }
        } else if (byteType == ByteType.WORD) {
            AssertUtils.state(len <= CodecUtils.maxLenCouldHold(byteType), " length overflow : " + len);
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                buf.writeShort(len);
            } else {
                buf.writeShortLE(len);
            }
        } else if (byteType == ByteType.DWORD) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                buf.writeInt(len);
            } else {
                buf.writeIntLE(len);
            }
        } else if (byteType == ByteType.QWORD) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                buf.writeLong(len);
            } else {
                buf.writeLong(len);
            }
        } else {
            AssertUtils.state(false, " unknown byteType : " + byteType);
        }
    }

    /**
     * writeLen
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 15:03
     */
    public static long readLen(ByteType byteType, ByteOrder byteOrder, ByteBuf buf) {
        if (byteType == ByteType.BYTE) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return buf.readByte();
            } else {
                return buf.readByte();
            }
        } else if (byteType == ByteType.WORD) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return buf.readShort();
            } else {
                return buf.readShortLE();
            }
        } else if (byteType == ByteType.DWORD) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return buf.readInt();
            } else {
                return buf.readIntLE();
            }
        } else if (byteType == ByteType.QWORD) {
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                return buf.readLong();
            } else {
                return buf.readLongLE();
            }
        } else {
            AssertUtils.state(false, " unknown byteType : " + byteType);
            return -1;
        }
    }

    /**
     * $desc
     *
     * @param output        output
     * @param lengthInBytes lengthInBytes
     * @param index         index
     * @return int
     * @author Jerry.X.He
     * @date 2021-10-10 10:51
     */
    public static int getInt(ByteBuf output, int lengthInBytes, int index) {
        int value;
        switch (lengthInBytes) {
            case 1:
                value = output.getUnsignedByte(index);
                break;
            case 2:
                value = output.getUnsignedShort(index);
                break;
            case 3:
                value = output.getUnsignedMedium(index);
                break;
            case 4:
                value = output.getInt(index);
                break;
            default:
                throw new RuntimeException("unsupported lengthInBytes: " + lengthInBytes + " (expected: 1, 2, 3, 4)");
        }
        return value;
    }

    /**
     * setInt
     *
     * @param output        output
     * @param lengthInBytes lengthInBytes
     * @param index         index
     * @param value         value
     * @return void
     * @author Jerry.X.He
     * @date 2021-10-03 12:59
     */
    public static void setInt(ByteBuf output, int lengthInBytes, int index, int value) {
        switch (lengthInBytes) {
            case 1:
                output.setByte(index, value);
                break;
            case 2:
                output.setShort(index, value);
                break;
            case 3:
                output.setMedium(index, value);
                break;
            case 4:
                output.setInt(index, value);
                break;
            default:
                throw new RuntimeException("unsupported lengthInBytes: " + lengthInBytes + " (expected: 1, 2, 3, 4)");
        }
    }

    /**
     * Returns the number of bytes between the readerIndex of the haystack and
     * the first needle found in the haystack.  -1 is returned if no needle is
     * found in the haystack.
     */
    public static int indexOf(ByteBuf haystack, ByteBuf needle, int startIndex) {
        startIndex = Math.max(startIndex, haystack.readerIndex());
        for (int i = startIndex; i < haystack.writerIndex(); i++) {
            int haystackIndex = i;
            int needleIndex;
            for (needleIndex = 0; needleIndex < needle.capacity(); needleIndex++) {
                if (haystack.getByte(haystackIndex) != needle.getByte(needleIndex)) {
                    break;
                } else {
                    haystackIndex++;
                    if (haystackIndex == haystack.writerIndex() &&
                            needleIndex != needle.capacity() - 1) {
                        return -1;
                    }
                }
            }

            if (needleIndex == needle.capacity()) {
                // Found the needle from the haystack!
                return i - haystack.readerIndex();
            }
        }
        return -1;
    }

    public static int indexOf(ByteBuf haystack, ByteBuf needle) {
        return indexOf(haystack, needle, 0);
    }

    public static int indexOf(ByteBuf haystack, byte[] needle, int startIndex) {
        return ByteBufUtils.doWith(Pooled.wrappedBuffer(needle), needleBuf -> {
            return indexOf(haystack, needleBuf, startIndex);
        });
    }

    public static int indexOf(ByteBuf haystack, byte[] needle) {
        return indexOf(haystack, needle, 0);
    }


    /**
     * doWith
     *
     * @param buf  buf
     * @param func func
     * @return T
     * @author Jerry.X.He
     * @date 2021-12-05 10:42
     */
    public static <T> T doWith(ByteBuf buf, Function<ByteBuf, T> func) {
        try {
            return func.apply(buf);
        } catch (Exception e) {
            throw e;
        } finally {
            buf.release();
        }
    }

    public static void doWith(ByteBuf buf, Consumer<ByteBuf> func) {
        try {
            func.accept(buf);
        } catch (Exception e) {
            throw e;
        } finally {
            buf.release();
        }
    }

    public static <T> T doWith(ByteBuf buf1, ByteBuf buf2, BiFunction<ByteBuf, ByteBuf, T> func) {
        try {
            return func.apply(buf1, buf2);
        } catch (Exception e) {
            throw e;
        } finally {
            buf1.release();
            if (buf2 != buf1) {
                buf2.release();
            }
        }
    }

    public static void doWith(ByteBuf buf1, ByteBuf buf2, BiConsumer<ByteBuf, ByteBuf> func) {
        try {
            func.accept(buf1, buf2);
        } catch (Exception e) {
            throw e;
        } finally {
            buf1.release();
            if (buf2 != buf1) {
                buf2.release();
            }
        }
    }

}
