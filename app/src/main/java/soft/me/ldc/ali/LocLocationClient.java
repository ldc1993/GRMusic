package soft.me.ldc.ali;

import android.content.Context;
import android.content.Intent;

import com.amap.api.location.AMapLocationClient;

/**
 * Created by liudi on 2018/2/13.
 */

public class LocLocationClient extends AMapLocationClient {
    private static LocLocationClient instance = null;

    public static LocLocationClient Instance(Context ctx) {
        synchronized (LocLocationClient.class) {
            if (instance == null)
                instance = new LocLocationClient(ctx);
        }
        return instance;
    }

    public static LocLocationClient Instance(Context ctx, Intent it) {
        synchronized (LocLocationClient.class) {
            if (instance == null)
                instance = new LocLocationClient(ctx, it);
        }
        return instance;
    }

    private LocLocationClient(Context context) {
        super(context);
    }

    private LocLocationClient(Context context, Intent intent) {
        super(context, intent);
    }
}
