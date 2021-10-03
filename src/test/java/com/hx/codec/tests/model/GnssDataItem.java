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
    private int encrypt;
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
    private int lon;
    /**
     * 纬度,单位为1 * 10-6度
     */
    private int lat;
    /**
     * 速度,指卫星定位车载终端设备上传的行车速度信息，为必填项， 单位为千米每小时（km/h）
     */
    private int vec1;
    /**
     * 行驶记录速度，指车辆行驶记录设备上传的行车速度信息，单位 为千米每小时（km/h）
     */
    private int vec2;
    /**
     * 车辆当前总里程数，指车辆上传的行车里程数，单位为千米（km）
     */
    private int vec3;
    /**
     * 方向，0 ~359,单位为度（°），正北为0,顺时针
     */
    private int direction;
    /**
     * 海拔高度，单位为米（m）
     */
    private int altitude;
    /**
     * 车辆状态，二进制表示:B31B30……B2B1B0。具体定义按照JT/T808—2011 中表 17 的规定
     */
    private int state;
    /**
     * 报警状态，二进制表示，0表示正常，1表示报警:B31B30B29…… B2B1B0。 具体定义按照 JT/T 808—2011中表18的规定
     */
    private int alarm;

    public GnssDataItem() {
    }

    public GnssDataItem(int encrypt, Integer[] date, Integer[] time, int lon, int lat, int vec1, int vec2, int vec3,
                        int direction, int altitude, int state, int alarm) {
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
    public int getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(int encrypt) {
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
    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    @Field(name = "msgLength", sort = 5, dataType = DataType.DWORD, lengthInBytes = 4, desc = "纬度,单位为1 * 10-6度", version={2011, 2019})
    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    @Field(name = "msgLength", sort = 6, dataType = DataType.WORD, lengthInBytes = 2, desc = "速度,指卫星定位车载终端设备上传的行车速度信息，为必填项， 单位为千米每小时（km/h）", version={2011, 2019})
    public int getVec1() {
        return vec1;
    }

    public void setVec1(int vec1) {
        this.vec1 = vec1;
    }

    @Field(name = "msgLength", sort = 7, dataType = DataType.WORD, lengthInBytes = 2, desc = "行驶记录速度，指车辆行驶记录设备上传的行车速度信息，单位 为千米每小时（km/h）", version={2011, 2019})
    public int getVec2() {
        return vec2;
    }

    public void setVec2(int vec2) {
        this.vec2 = vec2;
    }

    @Field(name = "msgLength", sort = 8, dataType = DataType.DWORD, lengthInBytes = 4, desc = "车辆当前总里程数，指车辆上传的行车里程数，单位为千米（km）", version={2011, 2019})
    public int getVec3() {
        return vec3;
    }

    public void setVec3(int vec3) {
        this.vec3 = vec3;
    }

    @Field(name = "msgLength", sort = 9, dataType = DataType.WORD, lengthInBytes = 2, desc = "方向，0 ~359,单位为度（°），正北为0,顺时针", version={2011, 2019})
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Field(name = "msgLength", sort = 10, dataType = DataType.WORD, lengthInBytes = 2, desc = "海拔高度，单位为米（m）", version={2011, 2019})
    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Field(name = "msgLength", sort = 11, dataType = DataType.DWORD, lengthInBytes = 4, desc = "车辆状态，二进制表示:B31B30……B2B1B0。具体定义按照JT/T808—2011 中表 17 的规定", version={2011, 2019})
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Field(name = "msgLength", sort = 12, dataType = DataType.DWORD, lengthInBytes = 4, desc = "报警状态，二进制表示，0表示正常，1表示报警:B31B30B29…… B2B1B0。 具体定义按照 JT/T 808—2011中表18的规定", version={2011, 2019})
    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

}
