package soft.me.ldc.ali;

import java.io.Serializable;

/**
 * Created by liudi on 2018/2/13.
 */

public class AliLocInfo implements Serializable {
    final static long serialVersionUID = 1l;

    public int LocationType = 0;//获取当前定位结果来源，如网络定位结果，详见定位类型表

    public double Latitude = 0;//获取纬度

    public double Longitude = 0;//获取经度

    public float Accuracy = 0;//获取精度信息

    public String Address = "";//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    public String Country = "";//国家信息

    public String Province = "";//省信息

    public String City = "";//城市信息

    public String District = "";//城区信息

    public String Street = "";//街道信息

    public String StreetNum = "";//街道门牌号信息

    public String CityCode = "";//城市编码

    public String AdCode = "";//地区编码

    public String AoiName = "";//获取当前定位点的AOI信息

    public String BuildingId = "";//获取当前室内定位的建筑物Id

    public String Floor = "";//获取当前室内定位的楼层

    public int GpsAccuracyStatus = 0;//获取GPS的当前状态

    public String Time = "";//时间
}
