package soft.me.ldc.ali;

import java.io.Serializable;

/**
 * Created by liudi on 2018/2/13.
 */

public class LocInfo implements Serializable {
    final static long serialVersionUID = 1l;
    private static LocInfo instance = null;

    //单实例
    public static LocInfo Instance() {
        synchronized (LocInfo.class) {
            if (instance == null)
                instance = new LocInfo();
        }
        return instance;
    }

    Object LocationType;//获取当前定位结果来源，如网络定位结果，详见定位类型表

    Object Latitude;//获取纬度

    Object Longitude;//获取经度

    Object Accuracy;//获取精度信息

    Object Address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    Object Country;//国家信息

    Object Province;//省信息

    Object City;//城市信息

    Object District;//城区信息

    Object Street;//街道信息

    Object StreetNum;//街道门牌号信息

    Object CityCode;//城市编码

    Object AdCode;//地区编码

    Object AoiName;//获取当前定位点的AOI信息

    Object BuildingId;//获取当前室内定位的建筑物Id

    Object Floor;//获取当前室内定位的楼层

    Object GpsAccuracyStatus;//获取GPS的当前状态

    Object Time;
}
