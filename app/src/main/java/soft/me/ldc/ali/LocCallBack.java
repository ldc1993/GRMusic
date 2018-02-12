package soft.me.ldc.ali;

/**
 * Created by liudi on 2018/2/13.
 */

public interface LocCallBack {
    Object getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表

    Object getLatitude();//获取纬度

    Object getLongitude();//获取经度

    Object getAccuracy();//获取精度信息

    Object getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。

    Object getCountry();//国家信息

    Object getProvince();//省信息

    Object getCity();//城市信息

    Object getDistrict();//城区信息

    Object getStreet();//街道信息

    Object getStreetNum();//街道门牌号信息

    Object getCityCode();//城市编码

    Object getAdCode();//地区编码

    Object getAoiName();//获取当前定位点的AOI信息

    Object getBuildingId();//获取当前室内定位的建筑物Id

    Object getFloor();//获取当前室内定位的楼层

    Object getGpsAccuracyStatus();//获取GPS的当前状态

    Object getTime();
}
