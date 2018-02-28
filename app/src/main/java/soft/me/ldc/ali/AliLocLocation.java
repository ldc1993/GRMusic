package soft.me.ldc.ali;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationClientOption.AMapLocationPurpose;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by liudi on 2018/2/13.
 */

public class AliLocLocation {
    AMapLocationClient locationClient = null;
    AMapLocationClientOption locationClientOption = null;
    Context ctx = null;
    static AliLocLocation instance = null;

    public static AliLocLocation Instance(Context ctx) {
        synchronized (AliLocLocation.class) {
            if (instance == null)
                instance = new AliLocLocation(ctx);
        }
        return instance;
    }

    private AliLocLocation(Context ctx) {
        Initial(ctx);
        this.ctx = ctx;
    }

    //初始化
    private void Initial(Context ctx) {
        //配置
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationPurpose(AMapLocationPurpose.SignIn);
        //locationClientOption.setLocationMode(AMapLocationMode.Battery_Saving);//高精度
        locationClientOption.setLocationMode(AMapLocationMode.Battery_Saving);//低功耗
        //locationClientOption.setLocationMode(AMapLocationMode.Device_Sensors);//设备模式
        locationClientOption.setOnceLocationLatest(true);
        locationClientOption.setOnceLocation(false);//获取一次定位结果
        locationClientOption.setInterval(5000);//定位频率
        locationClientOption.setNeedAddress(true);//返回地址信息
        locationClientOption.setMockEnable(true);//允许模拟位置
        locationClientOption.setHttpTimeOut(20000);//超时
        locationClientOption.setLocationCacheEnable(false);//关闭缓存
        //客户端
        if (locationClient == null)
            locationClient = new AMapLocationClient(ctx.getApplicationContext());
        locationClient.setLocationOption(locationClientOption);

    }

    public void setListener(AMapLocationListener listener) {
        if (listener != null)
            locationClient.setLocationListener(listener);
    }

    public void StartLocation() {
        if (locationClient == null) {
            Initial(ctx);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            locationClient.stopLocation();
        }
        locationClient.startLocation();
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
}


