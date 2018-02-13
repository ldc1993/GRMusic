package soft.me.ldc.config;

import android.os.Environment;

import java.io.File;

import soft.me.ldc.BuildConfig;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class AppConfig {
    public static String ServiceUrl = BuildConfig.ServiceUrl;
    public static String WeatherUrl = BuildConfig.WeatherUrl;
    //
    public static String RootDir = Environment.getExternalStorageDirectory() + File.separator + "GRMusic" + File.separator;
}
