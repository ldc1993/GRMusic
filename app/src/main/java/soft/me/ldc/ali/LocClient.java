package soft.me.ldc.ali;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationPurpose;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by liudi on 2018/2/13.
 */

public class LocClient implements LocCallBack {
    Context ctx = null;
    static LocClient instance = null;
    AMapLocationClient locationClient = null;
    AMapLocationClientOption locationClientOption = null;

    public static LocClient Instance(Context ctx) {
        synchronized (LocClient.class) {
            if (instance == null)
                instance = new LocClient(ctx);
        }
        return instance;
    }

    private LocClient(Context ctx) {
        this.ctx = ctx;
        Initial();
    }

    //初始化
    private void Initial() {
        //配置
        if (locationClientOption == null)
            locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationPurpose(AMapLocationPurpose.SignIn);
        locationClientOption.setLocationMode(AMapLocationMode.Hight_Accuracy);//高精度
        locationClientOption.setLocationMode(AMapLocationMode.Battery_Saving);//低功耗
        locationClientOption.setLocationMode(AMapLocationMode.Device_Sensors);//设备模式
        locationClientOption.setOnceLocationLatest(true);
        locationClientOption.setOnceLocation(false);//获取一次定位结果
        locationClientOption.setInterval(5000);//定位频率
        locationClientOption.setNeedAddress(true);//返回地址信息
        locationClientOption.setMockEnable(true);//允许模拟位置
        locationClientOption.setHttpTimeOut(20000);//超时
        locationClientOption.setLocationCacheEnable(false);//关闭缓存
        //客户端
        locationClient = LocLocationClient.Instance(ctx.getApplicationContext());
        locationClient.setLocationOption(locationClientOption);
        locationClient.setLocationListener(new LocListener());
        //启动定位服务
        if (null != locationClient) {
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            locationClient.stopLocation();
            locationClient.startLocation();
        }
    }

    //定位监听
    class LocListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    LocInfo.Instance().LocationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    LocInfo.Instance().Latitude = amapLocation.getLatitude();//获取纬度
                    LocInfo.Instance().Longitude = amapLocation.getLongitude();//获取经度
                    LocInfo.Instance().Accuracy = amapLocation.getAccuracy();//获取精度信息
                    LocInfo.Instance().Address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    LocInfo.Instance().Country = amapLocation.getCountry();//国家信息
                    LocInfo.Instance().Province = amapLocation.getProvince();//省信息
                    LocInfo.Instance().City = amapLocation.getCity();//城市信息
                    LocInfo.Instance().District = amapLocation.getDistrict();//城区信息
                    LocInfo.Instance().Street = amapLocation.getStreet();//街道信息
                    LocInfo.Instance().StreetNum = amapLocation.getStreetNum();//街道门牌号信息
                    LocInfo.Instance().CityCode = amapLocation.getCityCode();//城市编码
                    LocInfo.Instance().AdCode = amapLocation.getAdCode();//地区编码
                    LocInfo.Instance().AoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
                    LocInfo.Instance().BuildingId = amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                    LocInfo.Instance().Floor = amapLocation.getFloor();//获取当前室内定位的楼层
                    LocInfo.Instance().GpsAccuracyStatus = amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态

                } else {
                    //错误信息
                }
            }
        }
    }

    @Override
    public Object getLocationType() {
        return LocInfo.Instance().LocationType;
    }

    @Override
    public Object getLatitude() {
        return LocInfo.Instance().Latitude;
    }

    @Override
    public Object getLongitude() {
        return LocInfo.Instance().Longitude;
    }

    @Override
    public Object getAccuracy() {
        return LocInfo.Instance().Accuracy;
    }

    @Override
    public Object getAddress() {
        return LocInfo.Instance().Address;
    }

    @Override
    public Object getCountry() {
        return LocInfo.Instance().Country;
    }

    @Override
    public Object getProvince() {
        return LocInfo.Instance().Province;
    }

    @Override
    public Object getCity() {
        return LocInfo.Instance().City;
    }

    @Override
    public Object getDistrict() {
        return LocInfo.Instance().District;
    }

    @Override
    public Object getStreet() {
        return LocInfo.Instance().Street;
    }

    @Override
    public Object getStreetNum() {
        return LocInfo.Instance().StreetNum;
    }

    @Override
    public Object getCityCode() {
        return LocInfo.Instance().CityCode;
    }

    @Override
    public Object getAdCode() {

        return LocInfo.Instance().AdCode;
    }

    @Override
    public Object getAoiName() {
        return LocInfo.Instance().AoiName;
    }

    @Override
    public Object getBuildingId() {
        return LocInfo.Instance().BuildingId;
    }

    @Override
    public Object getFloor() {
        return LocInfo.Instance().Floor;
    }

    @Override
    public Object getGpsAccuracyStatus() {
        return LocInfo.Instance().GpsAccuracyStatus;
    }

    @Override
    public Object getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date((Long) LocInfo.Instance().Time);
        return df.format(date);
    }


}


