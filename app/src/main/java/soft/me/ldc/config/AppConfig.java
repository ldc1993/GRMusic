package soft.me.ldc.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class AppConfig {
    public static String ServiceUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting";
    //
    public static String RootDir = Environment.getExternalStorageDirectory() + File.separator + "GRMusic" + File.separator;
}
