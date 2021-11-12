package com.hx.codec.utils;

import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.entity.GenericBeanCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.factory.array.*;
import com.hx.codec.codec.factory.collection.*;
import com.hx.codec.codec.factory.common.*;
import com.hx.codec.codec.factory.entity.GenericBeanCodecFactory;
import com.hx.codec.codec.factory.map.GenericMapCodecFactory;
import com.hx.codec.codec.factory.map.SchemaRegistryBasedMapCodecFactory;
import com.hx.codec.codec.factory.map.SchemaRegistryBasedMapWithExactlyLenCodecFactory;
import com.hx.codec.codec.factory.map.SchemaRegistryBasedMapWithLenCodecFactory;
import com.hx.codec.codec.factory.string.*;
import com.hx.codec.constants.ByteType;
import com.hx.codec.constants.DataType;
import com.hx.codec.schema.GenericBeanSchema;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
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
        // simple
        DATA_TYPE_2_CODEC.put(DataType.BYTE, new ByteCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE, new UnsignedByteCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD, new WordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD, new UnsignedWordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD, new DWordCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD, new QWordCodecFactory());

        // array
        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY, new ByteArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY, new UnsignedByteArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY, new WordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY, new UnsignedWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY, new DWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY, new QWordArrayCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_ARRAY, new GenericBeanArrayCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY_WITH_LEN, new ByteArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY_WITH_LEN, new UnsignedByteArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY_WITH_LEN, new WordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY_WITH_LEN, new UnsignedWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY_WITH_LEN, new DWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY_WITH_LEN, new QWordArrayWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_ARRAY_WITH_LEN, new GenericBeanArrayWithLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY_WITH_ELE_LEN, new ByteArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY_WITH_ELE_LEN, new UnsignedByteArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY_WITH_ELE_LEN, new WordArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY_WITH_ELE_LEN, new UnsignedWordArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY_WITH_ELE_LEN, new DWordArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY_WITH_ELE_LEN, new QWordArrayWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_ARRAY_WITH_ELE_LEN, new GenericBeanArrayWithEleLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_ARRAY_WITH_EXACTLY_LEN, new ByteArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_ARRAY_WITH_EXACTLY_LEN, new UnsignedByteArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_ARRAY_WITH_EXACTLY_LEN, new WordArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_ARRAY_WITH_EXACTLY_LEN, new UnsignedWordArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_ARRAY_WITH_EXACTLY_LEN, new DWordArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_ARRAY_WITH_EXACTLY_LEN, new QWordArrayWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_ARRAY_WITH_EXACTLY_LEN, new GenericBeanArrayWithExactlyLenCodecFactory());


        // collection
        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION, new ByteCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION, new UnsignedByteCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION, new WordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION, new UnsignedWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION, new DWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION, new QWordCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_COLLECTION, new GenericBeanCollectionCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_COLLECTION, new SchemaRegistryBasedCollectionCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION_WITH_LEN, new ByteCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION_WITH_LEN, new UnsignedByteCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION_WITH_LEN, new WordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION_WITH_LEN, new UnsignedWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION_WITH_LEN, new DWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION_WITH_LEN, new QWordCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_COLLECTION_WITH_LEN, new GenericBeanCollectionWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_COLLECTION_WITH_LEN, new SchemaRegistryBasedCollectionWithLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION_WITH_ELE_LEN, new ByteCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION_WITH_ELE_LEN, new UnsignedByteCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION_WITH_ELE_LEN, new WordCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION_WITH_ELE_LEN, new UnsignedWordCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION_WITH_ELE_LEN, new DWordCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION_WITH_ELE_LEN, new QWordCollectionWithEleLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_COLLECTION_WITH_ELE_LEN, new GenericBeanCollectionWithEleLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.BYTE_COLLECTION_WITH_EXACTLY_LEN, new ByteCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_BYTE_COLLECTION_WITH_EXACTLY_LEN, new UnsignedByteCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.WORD_COLLECTION_WITH_EXACTLY_LEN, new WordCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.UNSIGNED_WORD_COLLECTION_WITH_EXACTLY_LEN, new UnsignedWordCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.DWORD_COLLECTION_WITH_EXACTLY_LEN, new DWordCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.QWORD_COLLECTION_WITH_EXACTLY_LEN, new QWordCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN_COLLECTION_WITH_EXACTLY_LEN, new GenericBeanCollectionWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_COLLECTION_WITH_EXACTLY_LEN, new SchemaRegistryBasedCollectionWithExactlyLenCodecFactory());

        // string
        DATA_TYPE_2_CODEC.put(DataType.BCD_8421_STRING_WITH_LEN, new Bcd8421StringWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BCD_8421_STRING_WITH_FIXED_LEN, new Bcd8421StringWithFixedLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BCD_8421_STRING_WITH_EXACTLY_LEN, new Bcd8421StringWithExactlyLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.BCD_8421_BIG_DECIMAL_WITH_ONE_BYTE_LEN, new Bcd8421BigDecimalWithOneByteLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_STRING, new CharsetEncodingStringCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_WITH_LEN_STRING, new CharsetEncodingStringWithLenCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, new CharsetEncodingStringWithFixedLenCodecFactory());

        // entity
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_BEAN, new GenericBeanCodecFactory());

        // map
        DATA_TYPE_2_CODEC.put(DataType.GENERIC_MAP, new GenericMapCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_MAP, new SchemaRegistryBasedMapCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.GENERIC_MAP_WITH_LEN, new GenericMapCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_MAP_WITH_LEN, new SchemaRegistryBasedMapWithLenCodecFactory());

        DATA_TYPE_2_CODEC.put(DataType.GENERIC_MAP_WITH_EXACTLY_LEN, new GenericMapCodecFactory());
        DATA_TYPE_2_CODEC.put(DataType.SCHEMA_REGISTRY_BASED_MAP_WITH_EXACTLY_LEN, new SchemaRegistryBasedMapWithExactlyLenCodecFactory());

        // others
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
     * createCodeccForMapCodecFactory
     *
     * @param dataTypeStr dataTypeStr
     * @param context     context
     * @return com.hx.codec.codec.AbstractCodec
     * @author Jerry.X.He
     * @date 2021-10-10 17:12
     */
    public static AbstractCodec createCodecForMapCodecFactory(String dataTypeStr, Class fieldType, CodecFactoryContext context) {
        DataType dataType = DataType.valueOf(dataTypeStr);
        if (dataType == null) {
            return new GenericBeanCodec<>(new GenericBeanSchema<>(fieldType, context.getVersion()));
        }
        // other, bean array/collection omit, please defined CustomCodecFacttory
        if (dataType == DataType.GENERIC_BEAN) {
            return new GenericBeanCodec<>(new GenericBeanSchema<>(fieldType, context.getVersion()));
        }

        AbstractCodecFactory keyCodecFactory = CodecUtils.lookupCodecFactoryByDataType(dataType);
        return keyCodecFactory.create(context);
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

    /**
     * getActualTypeArgument
     *
     * @param field field
     * @return java.lang.Class
     * @author Jerry.X.He
     * @date 2021-10-03 17:22
     */
    public static Class getActualTypeArgument(Field field, int typeArgIdx) {
        Type type = field.getGenericType();
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getActualTypeArguments()[typeArgIdx];
        } else {
            AssertUtils.state(false, " unexpected field ");
            return null;
        }
    }

    public static Class getActualTypeArgument(Field field) {
        return getActualTypeArgument(field, 0);
    }

    /**
     * $desc
     *
     * @param str str
     * @return java.lang.String
     * @author Jerry.X.He
     * @date 2021-10-02 19:33
     */
    public static String lowerCaseFirstChar(String str) {
        if (str.length() == 1) {
            return str.toLowerCase();
        }
        if (Character.isUpperCase(str.charAt(0))) {
            return Character.toLowerCase(str.charAt(0)) + str.substring(1);
        }

        return str;
    }

    public static String upperCaseFirstChar(String str) {
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        if (Character.isLowerCase(str.charAt(0))) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }

        return str;
    }

    /**
     * lengthOfString
     *
     * @param str str
     * @return int
     * @author Jerry.X.He
     * @date 2021-10-02 18:17
     */
    public static int lengthOfString(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    public static int lengthOfString(String str) {
        return str == null ? 0 : str.length();
    }

    public static String trimString(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean isNotBlank(String str) {
        return lengthOfString(str) > 0;
    }

    public static boolean isBlank(String str) {
        return lengthOfString(str) == 0;
    }

    public static boolean isNotBlank(CharSequence str) {
        return lengthOfString(str) > 0;
    }

    public static boolean isBlank(CharSequence str) {
        return lengthOfString(str) == 0;
    }

    /**
     * isCollEmpty
     *
     * @param collection collection
     * @return boolean
     * @author Jerry.X.He
     * @date 2021-10-02 18:19
     */
    public static boolean isCollEmpty(Collection<?> collection) {
        return collection == null ? true : collection.isEmpty();
    }

    /**
     * isMapEmpty
     *
     * @param collection collection
     * @return boolean
     * @author Jerry.X.He
     * @date 2021-10-02 18:19
     */
    public static boolean isMapEmpty(Map<?, ?> collection) {
        return collection == null ? true : collection.isEmpty();
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

    //-----------------------------------------------------------------------

    /**
     * <p>Counts how many times the substring appears in the larger string.</p>
     *
     * <p>A {@code null} or empty ("") String input returns {@code 0}.</p>
     *
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param sub the substring to count, may be null
     * @return the number of occurrences, 0 if either CharSequence is {@code null}
     * @since 3.0 Changed signature from countMatches(String, String) to countMatches(CharSequence, CharSequence)
     */
    public static int countMatches(final CharSequence str, final CharSequence sub) {
        if (isBlank(str) || isBlank(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = indexOf(str, sub, idx)) >= 0) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * <p>Finds the first index in the {@code CharSequence} that matches the
     * specified character.</p>
     *
     * @param cs         the {@code CharSequence} to be processed, not null
     * @param searchChar the char to be searched for
     * @param start      the start index, negative starts at the string start
     * @return the index where the search char was found, -1 if not found
     */
    public static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
        return cs.toString().indexOf(searchChar.toString(), start);
    }


}
