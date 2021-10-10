package com.hx.codec.tests.model;

import com.hx.codec.anno.Field;
import com.hx.codec.constants.DataType;

/**
 * 卫星定位数据
 *
 * @author Jerry.X.He
 * @version 1.0
 * @date 2021/9/15 15:11
 */
public class GnssDataItem {

    /**
     * 该字段标识传输的定位信息是否使用国家测绘局批准的地图保 密插件进行加密。加密标识：1-已加密，0-未加密
     */
    private Integer encrypt;
    /**
     * 日月年（dmyy）,年的表示是先将年转换成两位十六进制数，如 2009 表示为 0x07 0xD9
     */
    private Integer[] date;
    /**
     * 时分秒（hms）
     */
    private Integer[] time;
    /**
     * 经度,单位为1*10-6度
     */
    private Integer lon;
    /**
     * 纬度,单位为1 * 10-6度
     */
    private Integer lat;
    /**
     * 速度,指卫星定位车载终端设备上传的行车速度信息，为必填项， 单位为千米每小时（km/h）
     */
    private Integer vec1;
    /**
     * 行驶记录速度，指车辆行驶记录设备上传的行车速度信息，单位 为千米每小时（km/h）
     */
    private Integer vec2;
    /**
     * 车辆当前总里程数，指车辆上传的行车里程数，单位为千米（km）
     */
    private Integer vec3;
    /**
     * 方向，0 ~359,单位为度（°），正北为0,顺时针
     */
    private Integer direction;
    /**
     * 海拔高度，单位为米（m）
     */
    private Integer altitude;
    /**
     * 车辆状态，二进制表示:B31B30……B2B1B0。具体定义按照JT/T808—2011 中表 17 的规定
     */
    private Integer state;
    /**
     * 报警状态，二进制表示，0表示正常，1表示报警:B31B30B29…… B2B1B0。 具体定义按照 JT/T 808—2011中表18的规定
     */
    private Integer alarm;

    public GnssDataItem() {
    }

    public GnssDataItem(Integer encrypt, Integer[] date, Integer[] time, Integer lon, Integer lat, Integer vec1, Integer vec2, Integer vec3,
                        Integer direction, Integer altitude, Integer state, Integer alarm) {
        this.encrypt = encrypt;
        this.date = date;
        this.time = time;
        this.lon = lon;
        this.lat = lat;
        this.vec1 = vec1;
        this.vec2 = vec2;
        this.vec3 = vec3;
        this.direction = direction;
        this.altitude = altitude;
        this.state = state;
        this.alarm = alarm;
    }

    @Field(name = "msgLength", sort = 1, dataType = DataType.BYTE, lengthInBytes = 1, desc = "该字段标识传输的定位信息是否使用国家测绘局批准的地图保 密插件进行加密。加密标识：1-已加密，0-未加密", version={2011, 2019})
    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    @Field(name = "msgLength", sort = 2, dataType = DataType.BYTE_ARRAY_WITH_FIXED_LEN, eleLength = 4, desc = "日月年（dmyy）,年的表示是先将年转换成两位十六进制数，如 2009 表示为 0x07 0xD9", version={2011, 2019})
    public Integer[] getDate() {
        return date;
    }

    public void setDate(Integer[] date) {
        this.date = date;
    }

    @Field(name = "msgLength", sort = 3, dataType = DataType.BYTE_ARRAY_WITH_FIXED_LEN, eleLength = 3, desc = "时分秒（hms）", version={2011, 2019})
    public Integer[] getTime() {
        return time;
    }

    public void setTime(Integer[] time) {
        this.time = time;
    }

    @Field(name = "msgLength", sort = 4, dataType = DataType.DWORD, lengthInBytes = 4, desc = "经度,单位为1*10-6度", version={2011, 2019})
    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    @Field(name = "msgLength", sort = 5, dataType = DataType.DWORD, lengthInBytes = 4, desc = "纬度,单位为1 * 10-6度", version={2011, 2019})
    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    @Field(name = "msgLength", sort = 6, dataType = DataType.WORD, lengthInBytes = 2, desc = "速度,指卫星定位车载终端设备上传的行车速度信息，为必填项， 单位为千米每小时（km/h）", version={2011, 2019})
    public Integer getVec1() {
        return vec1;
    }

    public void setVec1(Integer vec1) {
        this.vec1 = vec1;
    }

    @Field(name = "msgLength", sort = 7, dataType = DataType.WORD, lengthInBytes = 2, desc = "行驶记录速度，指车辆行驶记录设备上传的行车速度信息，单位 为千米每小时（km/h）", version={2011, 2019})
    public Integer getVec2() {
        return vec2;
    }

    public void setVec2(Integer vec2) {
        this.vec2 = vec2;
    }

    @Field(name = "msgLength", sort = 8, dataType = DataType.DWORD, lengthInBytes = 4, desc = "车辆当前总里程数，指车辆上传的行车里程数，单位为千米（km）", version={2011, 2019})
    public Integer getVec3() {
        return vec3;
    }

    public void setVec3(Integer vec3) {
        this.vec3 = vec3;
    }

    @Field(name = "msgLength", sort = 9, dataType = DataType.WORD, lengthInBytes = 2, desc = "方向，0 ~359,单位为度（°），正北为0,顺时针", version={2011, 2019})
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @Field(name = "msgLength", sort = 10, dataType = DataType.WORD, lengthInBytes = 2, desc = "海拔高度，单位为米（m）", version={2011, 2019})
    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    @Field(name = "msgLength", sort = 11, dataType = DataType.DWORD, lengthInBytes = 4, desc = "车辆状态，二进制表示:B31B30……B2B1B0。具体定义按照JT/T808—2011 中表 17 的规定", version={2011, 2019})
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Field(name = "msgLength", sort = 12, dataType = DataType.DWORD, lengthInBytes = 4, desc = "报警状态，二进制表示，0表示正常，1表示报警:B31B30B29…… B2B1B0。 具体定义按照 JT/T 808—2011中表18的规定", version={2011, 2019})
    public Integer getAlarm() {
        return alarm;
    }

    public void setAlarm(Integer alarm) {
        this.alarm = alarm;
    }

}
