package soft.me.ldc.config;

import android.os.Environment;

import java.io.File;

import soft.me.ldc.BuildConfig;

/**
 * Created by ldc45 on 2018/1/13.
 */

public enum AppConfig {
    INSTANCE;
    public String ServiceUrl = BuildConfig.ServiceUrl;
    public String WeatherUrl = BuildConfig.WeatherUrl;
    //
    public String RootDir = Environment.getExternalStorageDirectory() + File.separator + "WZCMusic" + File.separator;
    //广播接收器
    public String broadCast_ItCode = "android.intent.action.Notify";
    public String broadCast_PlayCode = "android.intent.action.Notify_play";
    public String broadCast_PauseCode = "android.intent.action.Notify_pause";
    public String broadCast_CloseCode = "android.intent.action.Notify_close";
}
