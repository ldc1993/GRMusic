package soft.me.ldc.ali;

import java.io.Serializable;

/**
 * Created by liudi on 2018/2/13.
 */

public class LocInfo implements Serializable {
    final static long serialVersionUID = 1l;

    int LocationType;//获取当前定位结果来源，如网络定位结果，详见定位类型表

    double Latitude;//获取纬度

    double Longitude;//获取经度

    float Accuracy;//获取精度信息

    String Address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    String Country;//国家信息

    String Province;//省信息

    String City;//城市信息

    String District;//城区信息

    String Street;//街道信息

    String StreetNum;//街道门牌号信息

    String CityCode;//城市编码

    String AdCode;//地区编码

    String AoiName;//获取当前定位点的AOI信息

    String BuildingId;//获取当前室内定位的建筑物Id

    String Floor;//获取当前室内定位的楼层

    int GpsAccuracyStatus;//获取GPS的当前状态

    String Time;


    @Override
    public String toString() {
        return "LocInfo{" +
                "LocationType=" + LocationType +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", Accuracy=" + Accuracy +
                ", Address='" + Address + '\'' +
                ", Country='" + Country + '\'' +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", District='" + District + '\'' +
                ", Street='" + Street + '\'' +
                ", StreetNum='" + StreetNum + '\'' +
                ", CityCode='" + CityCode + '\'' +
                ", AdCode='" + AdCode + '\'' +
                ", AoiName='" + AoiName + '\'' +
                ", BuildingId='" + BuildingId + '\'' +
                ", Floor='" + Floor + '\'' +
                ", GpsAccuracyStatus=" + GpsAccuracyStatus +
                ", Time='" + Time + '\'' +
                '}';
    }
}
