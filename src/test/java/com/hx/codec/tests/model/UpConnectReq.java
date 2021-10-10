package com.hx.codec.tests.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.DataType;
import com.hx.codec.tests.interceptor.VerifyFieldValueFieldInterceptor;

import java.io.Serializable;

/**
 * UpConnectReq
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/9 17:40
 */
public class UpConnectReq implements Serializable {

    /**
     * dummyField
     */
    private Object dummyField;
    /**
     * 用户名
     */
    private Integer userid;
    /**
     * 密码
     */
    private String password;
    /**
     * 下级平台接入码，上级平台给下级平台分配的唯一标识号
     */
    private Integer msgGnsscenterid;
    /**
     * 下级平台提供对应的从链路服务端IP地址
     */
    private String downLinkIp;
    /**
     * 下级平台提供对应的从链路服务端口号
     */
    private Integer downLinkPort;

    public UpConnectReq() {
    }

    @Field(dataType = DataType.PADDING_BYTE, name = "dummyField", sort = 1, lengthInBytes = 10, version = {2011, 2019},
            args = "{\"paddingByte\":\"7\"}", fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public Object getDummyField() {
        return dummyField;
    }

    public void setDummyField(Object dummyField) {
        this.dummyField = dummyField;
    }

    @Field(dataType = DataType.DWORD, name = "userId", sort = 2, lengthInBytes = 4, desc = "用户名", version = {2011, 2019},
            fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Field(dataType = DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, name = "password", sort = 3, lengthInBytes = 8, desc = "密码", version = {2011, 2019}
            , args = "{\"paddingFirst\":\"false\"}", fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Field(dataType = DataType.DWORD, name = "msgGnsscenterid", sort = 4, lengthInBytes = 4, desc = "下级平台接入码，上级平台给下级平台分配的唯一标识号", version = {2019},
            fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public Integer getMsgGnsscenterid() {
        return msgGnsscenterid;
    }

    public void setMsgGnsscenterid(Integer msgGnsscenterid) {
        this.msgGnsscenterid = msgGnsscenterid;
    }

    @Field(dataType = DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, name = "downLinkIp", sort = 5, lengthInBytes = 32, desc = "下级平台提供对应的从链路服务端IP地址", version = {2011}
            , args = "{\"paddingFirst\":\"false\"}", fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    @Field(dataType = DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, name = "downLinkIp", sort = 5, lengthInBytes = 32, desc = "下级平台提供对应的从链路服务端IP地址", version = {2019}
            , args = "{\"paddingFirst\":\"false\"}", fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public String getDownLinkIp() {
        return downLinkIp;
    }

    public void setDownLinkIp(String downLinkIp) {
        this.downLinkIp = downLinkIp;
    }

    @Field(dataType = DataType.WORD, name = "downLinkPort", sort = 6, lengthInBytes = 2, desc = "下级平台提供对应的从链路服务端口号", version = {2011},
            fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    @Field(dataType = DataType.WORD, name = "downLinkPort", sort = 6, lengthInBytes = 2, desc = "下级平台提供对应的从链路服务端口号", version = {2019},
            fieldInterceptorClazz = VerifyFieldValueFieldInterceptor.class)
    public Integer getDownLinkPort() {
        return downLinkPort;
    }

    public void setDownLinkPort(Integer downLinkPort) {
        this.downLinkPort = downLinkPort;
    }


}
