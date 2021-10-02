package com.hx.codec.constants;

/**
 * DataType
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/23 17:11
 */
public enum DataType {

    // ------------------------------------------ basic types ------------------------------------------

    BYTE,
    UNSIGNED_BYTE,
    WORD,
    UNSIGNED_WORD,
    DWORD,
    QWORD,

    // ------------------------------------------ array types ------------------------------------------

    BYTE_ARRAY,
    UNSIGNED_BYTE_ARRAY,
    WORD_ARRAY,
    UNSIGNED_WORD_ARRAY,
    DWORD_ARRAY,
    QWORD_ARRAY,

    BYTE_ARRAY_WITH_LEN,
    UNSIGNED_BYTE_ARRAY_WITH_LEN,
    WORD_ARRAY_WITH_LEN,
    UNSIGNED_WORD_ARRAY_WITH_LEN,
    DWORD_ARRAY_WITH_LEN,
    QWORD_ARRAY_WITH_LEN,

    BYTE_ARRAY_WITH_FIXED_LEN,
    UNSIGNED_BYTE_ARRAY_WITH_FIXED_LEN,
    WORD_ARRAY_WITH_FIXED_LEN,
    UNSIGNED_WORD_ARRAY_WITH_FIXED_LEN,
    DWORD_ARRAY_WITH_FIXED_LEN,
    QWORD_ARRAY_WITH_FIXED_LEN,

    // ------------------------------------------ collection types ------------------------------------------

    BYTE_COLLECTION,
    UNSIGNED_BYTE_COLLECTION,
    WORD_COLLECTION,
    UNSIGNED_WORD_COLLECTION,
    DWORD_COLLECTION,
    QWORD_COLLECTION,

    BYTE_COLLECTION_WITH_LEN,
    UNSIGNED_BYTE_COLLECTION_WITH_LEN,
    WORD_COLLECTION_WITH_LEN,
    UNSIGNED_WORD_COLLECTION_WITH_LEN,
    DWORD_COLLECTION_WITH_LEN,
    QWORD_COLLECTION_WITH_LEN,

    BYTE_COLLECTION_WITH_FIXED_LEN,
    UNSIGNED_BYTE_COLLECTION_WITH_FIXED_LEN,
    WORD_COLLECTION_WITH_FIXED_LEN,
    UNSIGNED_WORD_COLLECTION_WITH_FIXED_LEN,
    DWORD_COLLECTION_WITH_FIXED_LEN,
    QWORD_COLLECTION_WITH_FIXED_LEN,

    // ------------------------------------------ string types ------------------------------------------

    BCD_8421_STRING,
    CHARSET_ENCODING_STRING,
    CHARSET_ENCODING_WITH_LEN_STRING,
    CHARSET_ENCODING_WITH_FIXED_LEN_STRING,

    // ------------------------------------------ entity types ------------------------------------------

    GENERIC_BEAN,

    // ------------------------------------------ other types ------------------------------------------

    PADDING_BYTE,
    PADDING_BYTES,

}
