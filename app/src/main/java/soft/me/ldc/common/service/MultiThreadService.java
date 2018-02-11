package soft.me.ldc.common.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import soft.me.ldc.common.pool.MultiThreadPool;


// TODO: 2017/12/8 多线程执行任务 线程
public class MultiThreadService extends IntentService {
    private MultiThreadPool multiThreadPool = null;

    public MultiThreadService() {
        super("MultiThread");
    }


    // TODO: 2017/12/8 第一次创建执行
    @Override
    public void onCreate() {
        super.onCreate();
        if (multiThreadPool == null)
            multiThreadPool = MultiThreadPool.newInsance();
    }

    //启动服务 没有调用
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (multiThreadPool == null)
            multiThreadPool = MultiThreadPool.newInsance();
        if (multiThreadPool.getPool() == null)
            multiThreadPool.initPool();
        return Service.START_NOT_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //耗时任务
        if (multiThreadPool == null)
            multiThreadPool = MultiThreadPool.newInsance();
        if (multiThreadPool.getPool() == null)
            multiThreadPool.initPool();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
        this.startService(new Intent(this, MultiThreadService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
