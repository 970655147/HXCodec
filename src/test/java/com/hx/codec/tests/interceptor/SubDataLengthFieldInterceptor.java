package com.hx.codec.tests.interceptor;

import com.hx.codec.interceptor.FieldInterceptContext;
import com.hx.codec.interceptor.FieldInterceptor;
import com.hx.codec.utils.ByteBufUtils;
import com.hx.codec.utils.CodecUtils;
import io.netty.buffer.ByteBuf;

/**
 * SubDataLengthFieldInterceptor
 *
 * @author Jerry.X.He <970655147@qq.com>
 * @version 1.0
 * @date 2021-10-10 10:52
 */
public class SubDataLengthFieldInterceptor implements FieldInterceptor {

    @Override
    public void beforeEncode(FieldInterceptContext context) throws Exception {
        String fieldName = context.getFieldSchema().getFieldName();
        ByteBuf byteBuf = context.getByteBuf();
        if (fieldName.equals("subDataLength")) {
            context.init();
            context.getArgsMap().put("subDataLengthWriterIndex", byteBuf.writerIndex());
        }
    }

    @Override
    public void afterEncode(FieldInterceptContext context) throws Exception {
        String fieldName = context.getFieldSchema().getFieldName();
        ByteBuf byteBuf = context.getByteBuf();
        if (!fieldName.equals("subDataLength")) {
            int writerIndex = byteBuf.writerIndex();
            int subDataLengthWriterIndex = (int) context.getArgsMap().get("subDataLengthWriterIndex");

            int subDataLengthLenInBytes = context.getBeanSchema().lookupFieldSchema("subDataLength").getFieldAnno().lengthInBytes();
            int subDataLength = writerIndex - subDataLengthWriterIndex - subDataLengthLenInBytes;
            ByteBufUtils.setInt(byteBuf, subDataLengthLenInBytes, subDataLengthWriterIndex, subDataLength);
        }
    }

    @Override
    public void beforeDecode(FieldInterceptContext context) throws Exception {

    }

    @Override
    public void afterDecode(FieldInterceptContext context) throws Exception {

    }

}
