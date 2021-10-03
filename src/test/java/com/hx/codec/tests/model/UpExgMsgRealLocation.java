package com.hx.codec.tests.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.DataType;

/**
 * UpExgMsgRealLocationReq
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/9 11:54
 */
public class UpExgMsgRealLocation {

    /**
     * 车牌号
     */
    private String vehicleNo;
    /**
     * 车牌颜色，按照JT/T 697J7-2014中的规定
     */
    private int vehicleColor;
    /**
     * 子业务类型标识
     */
    private int subDataType;
    /**
     * 后续数据长度
     */
    private int subDataLength;
    /**
     * 卫星定位数据
     */
    private GnssDataItem gnssDataItem;

    public UpExgMsgRealLocation() {
    }

    @Field(name = "msgLength", sort = 1, dataType = DataType.CHARSET_ENCODING_WITH_FIXED_LEN_STRING, lengthInBytes = 21, desc = "车牌号", version = {2011, 2019}
            , args = "{\"paddingFirst\":\"false\"}")
    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    @Field(name = "vehicleColor", sort = 2, dataType = DataType.BYTE, lengthInBytes = 1, desc = "车牌颜色，按照JT/T 697J7-2014中的规定", version = {2011, 2019})
    public int getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(int vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    @Field(name = "subDataType", sort = 3, dataType = DataType.WORD, lengthInBytes = 2, desc = "子业务类型标识", version = {2011, 2019})
    public int getSubDataType() {
        return subDataType;
    }

    public void setSubDataType(int subDataType) {
        this.subDataType = subDataType;
    }

    @Field(name = "subDataLength", sort = 4, dataType = DataType.DWORD, lengthInBytes = 4, desc = "后续数据长度", version = {2011, 2019})
    public int getSubDataLength() {
        return subDataLength;
    }

    public void setSubDataLength(int subDataLength) {
        this.subDataLength = subDataLength;
    }

    @Field(name = "gnssDataItem", sort = 5, dataType = DataType.GENERIC_BEAN, desc = "卫星定位数据", version = {2011, 2019})
    public GnssDataItem getGnssDataItem() {
        return gnssDataItem;
    }

    public void setGnssDataItem(GnssDataItem gnssDataItem) {
        this.gnssDataItem = gnssDataItem;
    }
}
