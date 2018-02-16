package soft.me.ldc.ali;

/**
 * Created by liudi on 2018/2/13.
 */

public interface AliLocCallBack {
    int getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表

    double getLatitude();//获取纬度

    double getLongitude();//获取经度

    float getAccuracy();//获取精度信息

    String getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    String getCountry();//国家信息

    String getProvince();//省信息

    String getCity();//城市信息

    String getDistrict();//城区信息

    String getStreet();//街道信息

    String getStreetNum();//街道门牌号信息

    String getCityCode();//城市编码

    String getAdCode();//地区编码

    String getAoiName();//获取当前定位点的AOI信息

    String getBuildingId();//获取当前室内定位的建筑物Id

    String getFloor();//获取当前室内定位的楼层

    int getGpsAccuracyStatus();//获取GPS的当前状态

    String getTime();
}
