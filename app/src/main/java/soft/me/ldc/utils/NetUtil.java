package soft.me.ldc.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

/**
 * Created by liudi on 2018/2/15.
 */

public class NetUtil {

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean isAvailable(Context ctx) {

        boolean enable = false;
        NetworkInfo info = getActiveNetworkInfo(ctx);
        if (info != null && info.isAvailable()) {
            enable = true;
        }

        return enable;
    }

    /**
     * 获取活动网络信息
     *
     * @param context
     * @return
     */
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    //设置界面
    public static void NetSetting(final Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle("网络设置提示")
                .setMessage("网络连接不可用,是否进行设置?")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = null;
                        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
                        if (android.os.Build.VERSION.SDK_INT > 10) {
                            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        } else {
                            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        }
                        ctx.startActivity(intent);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        }).show();
    }
}
