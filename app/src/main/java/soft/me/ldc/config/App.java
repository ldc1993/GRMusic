package soft.me.ldc.config;

import android.app.Application;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class App extends Application {
    InitializationConfig config = null;

    @Override
    public void onCreate() {
        super.onCreate();
        config = InitializationConfig.newBuilder(this).readTimeout(40 * 1000)
                .connectionTimeout(30 * 1000).build();
        NoHttp.initialize(config);
    }
}
