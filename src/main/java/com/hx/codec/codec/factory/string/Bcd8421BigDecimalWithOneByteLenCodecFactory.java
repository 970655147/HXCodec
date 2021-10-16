package com.hx.codec.codec.factory.string;

import com.alibaba.fastjson.JSONObject;
import com.hx.codec.anno.Field;
import com.hx.codec.codec.AbstractCodec;
import com.hx.codec.codec.factory.AbstractCodecFactory;
import com.hx.codec.codec.factory.CodecFactoryContext;
import com.hx.codec.codec.string.Bcd8421BigDecimalWithOneByteLenCodec;
import com.hx.codec.utils.JSONUtils;

import java.math.BigDecimal;
import java.nio.ByteOrder;

import static com.hx.codec.constants.CodecConstants.DEFAULT_BCD8421_PADDING;
import static com.hx.codec.constants.CodecConstants.KEY_PADDING_BYTE;

/**
 * Bcd8421BigDecimalWithOneByteLenCodecFactory
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021-10-16 20:43
 */
public class Bcd8421BigDecimalWithOneByteLenCodecFactory implements AbstractCodecFactory<BigDecimal, BigDecimal> {

    @Override
    public AbstractCodec<BigDecimal, BigDecimal> create(CodecFactoryContext context) {
        Field fieldAnno = context.getFieldAnno();
        ByteOrder byteOrder = fieldAnno.bigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
        JSONObject args = JSONObject.parseObject(fieldAnno.args());
        byte paddingByte = JSONUtils.getByteOrDefault(args, KEY_PADDING_BYTE, DEFAULT_BCD8421_PADDING);
        return new Bcd8421BigDecimalWithOneByteLenCodec(byteOrder, paddingByte);
    }

}
