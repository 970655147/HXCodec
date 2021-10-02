package com.hx.codec.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.DataType;

import java.io.Serializable;

/**
 * JT809Header
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/8 15:25
 */
public class Jt809Header implements Serializable {

    /**
     * 数据长度(包括头标识、数据头、数据体和尾标识) 4字节
     */
    private int msgLength;
    /**
     * 报文序列号 4字节
     */
    private int msgSn;
    /**
     * 业务数据类型 2字节
     */
    private int msgId;
    /**
     * 下级平台接入码，上级平台给下级平台分配唯一标识码。4字节
     */
    private int msgGNSSCenterId;
    /**
     * 协议版本号标识，上下级平台之间采用的标准协议版
     * 编号；长度为 3 个字节来表示，0x01 0x02 0x0F 标识
     * 的版本号是 v1.2.15，以此类推。
     * Hex编码 ,这个是3个字节，需要注意
     */
    private Integer[] versionFlag;
    /**
     * 报文加密标识位 b: 0 表示报文不加密，1 表示报文加密。0x00 0x01,这里默认不加密 1字节
     */
    private int encryptFlag = 0x00;
    /**
     * 数据加密的密匙，长度为 4 个字节
     */
    private int encryptKey;
    /**
     * 发送消息的时候系统的 UTC 时间, 长度为8字节
     */
    private long time;

    public Jt809Header() {
    }

    @Field(name = "msgLength", sort = 1, dataType = DataType.DWORD, desc = "数据长度, 包括头标志, 数据头, 数据体, crc校验码 和 尾标志", version = {2011, 2019})
    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
        this.msgLength = msgLength;
    }

    @Field(name = "msgSn", sort = 2, dataType = DataType.DWORD, desc = "报文序列号", version = {2011, 2019})
    public int getMsgSn() {
        return msgSn;
    }

    public void setMsgSn(int msgSn) {
        this.msgSn = msgSn;
    }

    @Field(name = "msgId", sort = 3, dataType = DataType.WORD, desc = "业务数据类型", version = {2011, 2019})
    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Field(name = "msgGNSSCenterId", sort = 4, dataType = DataType.DWORD, desc = "下级平台接入码", version = {2011, 2019})
    public int getMsgGNSSCenterId() {
        return msgGNSSCenterId;
    }

    public void setMsgGNSSCenterId(int msgGNSSCenterId) {
        this.msgGNSSCenterId = msgGNSSCenterId;
    }

    @Field(name = "versionFlag", sort = 5, dataType = DataType.BYTE_ARRAY_WITH_FIXED_LEN, eleLength = 3, desc = "下级平台接入码", version = {2011, 2019})
    public Integer[] getVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(Integer[] versionFlag) {
        this.versionFlag = versionFlag;
    }

    @Field(name = "encryptFlag", sort = 6, dataType = DataType.BYTE, lengthInBytes = 1, desc = "加密标志", version = {2011, 2019})
    public int getEncryptFlag() {
        return encryptFlag;
    }

    public void setEncryptFlag(int encryptFlag) {
        this.encryptFlag = encryptFlag;
    }

    @Field(name = "encryptKey", sort = 7, dataType = DataType.DWORD, desc = "加密的秘钥", version = {2011, 2019})
    public int getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(int encryptKey) {
        this.encryptKey = encryptKey;
    }

    @Field(name = "time", sort = 8, dataType = DataType.QWORD, desc = "发送时间", version = {2019})
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
