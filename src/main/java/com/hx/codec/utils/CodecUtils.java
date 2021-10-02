package com.hx.codec.utils;

import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.array.*;
import com.hx.codec.codec.factory.collection.*;
import com.hx.codec.codec.factory.common.*;
import com.hx.codec.codec.factory.entity.GenericBeanCodecFactory;
import com.hx.codec.codec.factory.string.Bcd8421StringCodecFactory;
import com.hx.codec.codec.factory.string.CharsetEncodingStringCodecFactory;
import com.hx.codec.codec.factory.string.CharsetEncodingStringWithFixedLenCodecFactory;
import com.hx.codec.codec.factory.string.CharsetEncodingStringWithLenCodecFactory;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.DataType;
import com.hx.codec.utils.AssertUtils;
import io.netty.buffer.ByteBuf;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.hx.codec.constants.CodecConstants.DEFAULT_CHARSET;

/**
 * CodecUtils
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 15:11
 */
public class CodecUtils {

    /**
     * dataType -> codec
     */
    private static Map<DataType, AbstractCodecFactory> DATA_TYPE_2_CODEC = new LinkedHashMap<>();

    static {
        DATA_TYPE_2_CODEC.put(DataType.BYTE, new ByteCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE, new UnsignedByteCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD, new WordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD, new UnsignedWordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD, new DWordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD, new QWordCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY, new ByteArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY, new UnsignedByteArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY, new WordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY, new UnsignedWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY, new DWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY, new QWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY_WITH_LEN, new ByteArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY_WITH_LEN, new UnsignedByteArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY_WITH_LEN, new WordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY_WITH_LEN, new UnsignedWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY_WITH_LEN, new DWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY_WITH_LEN, new QWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY_WITH_FIXED_LEN, new ByteArrayWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY_WITH_FIXED_LEN, new UnsignedByteArrayWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY_WITH_FIXED_LEN, new WordArrayWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY_WITH_FIXED_LEN, new UnsignedWordArrayWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY_WITH_FIXED_LEN, new DWordArrayWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY_WITH_FIXED_LEN, new QWordArrayWithFixedLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION, new ByteCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION, new UnsignedByteCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION, new WordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION, new UnsignedWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION, new DWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION, new QWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION_WITH_LEN, new ByteCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION_WITH_LEN, new UnsignedByteCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION_WITH_LEN, new WordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION_WITH_LEN, new UnsignedWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION_WITH_LEN, new DWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION_WITH_LEN, new QWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION_WITH_FIXED_LEN, new ByteCollectionWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION_WITH_FIXED_LEN, new UnsignedByteCollectionWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION_WITH_FIXED_LEN, new WordCollectionWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION_WITH_FIXED_LEN, new UnsignedWordCollectionWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION_WITH_FIXED_LEN, new DWordCollectionWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION_WITH_FIXED_LEN, new QWordCollectionWithFixedLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BCD_8421_STRING, new Bcd8421StringCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_STRING, new CharsetEncodingStringCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_WITH_LEN_STRING, new CharsetEncodingStringWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, new CharsetEncodingStringWithFixedLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN, new GenericBeanCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.PADDING_BYTE, new PaddingByteCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.PADDING_BYTES, new PaddingBytesCodecFactory());
    }

    /**
     * charsetForName
     *
     * @return java.nio.charset.Charset
     * @author Jerry.X.He
     * @date 2021/9/28 10:25
     */
    public static Charset charsetForName(String charsetName) {
        try {
            return Charset.forName(charsetName);
        } catch (Exception e) {
            e.printStackTrace();
            return DEFAULT_CHARSET;
        }
    }

    /**
     * lenBytes
     *
     * @return int
     * @author Jerry.X.He
     * @date 2021/9/23 16:25
     */
    public static int lenBytes(ByteType byteType) {
        if (byteType == ByteType.BYTE) {
            return 1;
        } else if (byteType == ByteType.WORD) {
            return 2;
        } else if (byteType == ByteType.DWORD) {
            return 4;
        } else if (byteType == ByteType.QWORD) {
            return 8;
        } else {
            AssertUtils.state(false, " unknown byteType : " + byteType);
            return -1;
        }
    }

    /**
     * maxLenCouldHold
     *
     * @return long
     * @author Jerry.X.He
     * @date 2021/9/23 16:54
     */
    public static long maxLenCouldHold(ByteType byteType) {
        if (byteType == ByteType.BYTE) {
            return 0x7f;
        } else if (byteType == ByteType.WORD) {
            return 0x7fff;
        } else if (byteType == ByteType.DWORD) {
            return 0x7fffffff;
        } else if (byteType == ByteType.QWORD) {
            return 0x7fffffffffffffffL;
        } else {
            AssertUtils.state(false, " unknown byteType : " + byteType);
            return -1;
        }
    }

    /**
     * writeLen
     *
     * @return void
     * @author Jerry.X.He
     * @date 2021/9/23 15:03
     */
    public static void writeLen(ByteType byteType, ByteOrder byteOrder, int len, ByteBuf buf) {
        if (byteType == ByteType.BYTE) {
            AssertUtils.state(len <= maxLenCouldHold(byteType), " length overflow : " + len);
            if (byteOrder == ByteOrder.BIG_ENDIAN) {
                buf.writeByte(len);
            } else {
                buf.writeByte(len);
            }
        } else if (byteType == ByteType.WORD) {
            AssertUtils.state(len <= maxLenCouldHold(byteType), " length overflow : " + len);
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
     * registerDataType2Codec
     *
     * @return boolean
     * @author Jerry.X.He
     * @date 2021/9/28 9:41
     */
    public static void registerDataType2CodecFactory(DataType dataType, AbstractCodecFactory codec) {
        DATA_TYPE_2_CODEC.put(dataType, codec);
    }

    public static AbstractCodecFactory lookupCodecFactoryByDataType(DataType dataType) {
        return DATA_TYPE_2_CODEC.get(dataType);
    }


}
