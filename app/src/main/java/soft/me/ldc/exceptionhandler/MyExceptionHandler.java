package soft.me.ldc.exceptionhandler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import soft.me.ldc.exceptionhandler.layout.Activity_Error;
import soft.me.ldc.exceptionhandler.model.ExceptionBean;
import soft.me.ldc.utils.CommonFileLogUtil;

/**
 * Created by LDC on 2018/3/18.
 */

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler = null;
    private boolean enableSelf = false;
    private Context context = null;
    private SimpleDateFormat dateFormat = null;
    //收集参数信息
    private HashMap<String, String> params = new HashMap<>();//实例化
    //是否已经启动activity
    private volatile int startflag = 0x000;
    private final int TureCode = 0x001;
    private final int FalseCode = 0x000;

    public enum Self {
        INSTANCE;
        private MyExceptionHandler instance = null;

        Self() {
            if (instance == null)
                instance = new MyExceptionHandler();
        }

        public MyExceptionHandler getHandler() {
            return instance;
        }
    }

    //启动功能服务
    public void SERVICE(Context context, boolean enableSelf) {
        this.enableSelf = enableSelf;
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);//设置默认
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (dateFormat == null)
            dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
        //手动处理
        if (enableSelf && processExceptionHandler()) {
            if (startflag == FalseCode) {
                ExceptionBean bean = null;
                if (bean == null)
                    bean = new ExceptionBean();
                bean.throwable = e;
                bean.params = params;
                //
                Bundle bundle = new Bundle();
                bundle.putSerializable("errorLog", bean);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(context, Activity_Error.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                startflag = TureCode;
            }
        }
        //调用系统默认处理
        else {
            if (mDefaultHandler == null)
                mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            mDefaultHandler.uncaughtException(t, e);
        }
        Process.killProcess(Process.myPid());//结束进程
        System.gc();//回收资源

    }

    // TODO: 2018/3/18 处理异常
    private boolean processExceptionHandler() {
        boolean enable = false;
        try {
            String fileName = dateFormat.format(new Date());
            collectDeviceInfo(context);
            CommonFileLogUtil fileLog = CommonFileLogUtil.Self.INSTANCE.getInstance();
            fileLog.WriteToSD(params, "Log", fileName);
            params.put("FilePath", fileLog.filePath);
            enable = true;
        } catch (Exception e) {
            enable = false;
        }
        return enable;
    }


    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                params.put("VersionName", versionName);
                params.put("VersionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                params.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
