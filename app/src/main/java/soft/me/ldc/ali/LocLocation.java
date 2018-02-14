package soft.me.ldc.ali;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationPurpose;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by liudi on 2018/2/13.
 */

public class LocLocation implements LocCallBack, AMapLocationListener {
    Context ctx = null;

    volatile static LocInfo locInfo = null;
    static LocLocation instance = null;
    AMapLocationClient locationClient = null;
    AMapLocationClientOption locationClientOption = null;

    public static LocLocation Instance(Context ctx) {
        synchronized (LocLocation.class) {
            if (instance == null)
                instance = new LocLocation(ctx);
        }
        return instance;
    }

    private LocLocation(Context ctx) {
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
        locationClient.setLocationListener(this);
    }

    //设置
    public LocInfo getLocInfo() {
        return locInfo;
    }

    //获取
    private void setLocInfo(LocInfo locInfo) {
        this.locInfo = locInfo;
    }

    //开始定位
    public void StartLocation() {
        if (null != locationClient) {
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            locationClient.stopLocation();
            locationClient.startLocation();
        }
    }

    //停止定位
    public void StopLocation() {
        if (null != locationClient) {
            //设置场景模式后最好调用一次stop
            locationClient.stopLocation();
        }
    }

    //销毁定位
    public void DestroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationClientOption = null;
        }
    }

    @Override
    public int getLocationType() {
        return locInfo.LocationType;
    }

    @Override
    public double getLatitude() {
        return locInfo.Latitude;
    }

    @Override
    public double getLongitude() {
        return locInfo.Longitude;
    }

    @Override
    public float getAccuracy() {
        return locInfo.Accuracy;
    }

    @Override
    public String getAddress() {
        return locInfo.Address;
    }

    @Override
    public String getCountry() {
        return locInfo.Country;
    }

    @Override
    public String getProvince() {
        return locInfo.Province;
    }

    @Override
    public String getCity() {
        return locInfo.City;
    }

    @Override
    public String getDistrict() {
        return locInfo.District;
    }

    @Override
    public String getStreet() {
        return locInfo.Street;
    }

    @Override
    public String getStreetNum() {
        return locInfo.StreetNum;
    }

    @Override
    public String getCityCode() {
        return locInfo.CityCode;
    }

    @Override
    public String getAdCode() {
        return locInfo.AdCode;
    }

    @Override
    public String getAoiName() {
        return locInfo.AoiName;
    }

    @Override
    public String getBuildingId() {
        return locInfo.BuildingId;
    }

    @Override
    public String getFloor() {
        return locInfo.Floor;
    }

    @Override
    public int getGpsAccuracyStatus() {
        return 0;
    }

    @Override
    public String getTime() {
        return locInfo.Time;
    }


    // TODO: 2018/2/15 定位事件
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                locInfo = new LocInfo();
                locInfo.LocationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                locInfo.Latitude = amapLocation.getLatitude();//获取纬度
                locInfo.Longitude = amapLocation.getLongitude();//获取经度
                locInfo.Accuracy = amapLocation.getAccuracy();//获取精度信息
                locInfo.Address = amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                locInfo.Country = amapLocation.getCountry();//国家信息
                locInfo.Province = amapLocation.getProvince();//省信息
                locInfo.City = amapLocation.getCity();//城市信息
                locInfo.District = amapLocation.getDistrict();//城区信息
                locInfo.Street = amapLocation.getStreet();//街道信息
                locInfo.StreetNum = amapLocation.getStreetNum();//街道门牌号信息
                locInfo.CityCode = amapLocation.getCityCode();//城市编码
                locInfo.AdCode = amapLocation.getAdCode();//地区编码
                locInfo.AoiName = amapLocation.getAoiName();//获取当前定位点的AOI信息
                locInfo.BuildingId = amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                locInfo.Floor = amapLocation.getFloor();//获取当前室内定位的楼层
                locInfo.GpsAccuracyStatus = amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                //设置获取定位数据
                setLocInfo(locInfo);
                Log.e("LLL", "" + locInfo.toString());
            } else {
                //错误信息
            }
        }
    }
}


