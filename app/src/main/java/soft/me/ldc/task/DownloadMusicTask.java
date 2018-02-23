package soft.me.ldc.task;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.download.SyncDownloadExecutor;

import java.io.File;

import soft.me.ldc.R;
import soft.me.ldc.common.ThreadTask;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

/**
 * Created by liudi on 2018/2/12.
 */

public class DownloadMusicTask extends ThreadTask {
    final static String SongDir = AppConfig.RootDir + "music" + File.separator;
    PlayMusicSongBean mData = null;
    Context ctx = null;
    Message msg = null;
    DownloadRequest downloadRequest = null;
    //
    NotificationCompat.Builder builder = null;
    NotificationManager notificationManager = null;
    //
    volatile static DownloadMusicTask instance = null;
    //
    final static int SUCCESSCODE = 0x001;
    final static int FAILEDCODE = 0x002;
    final static int UpdateProgressCode = 0x003;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESSCODE:
                    GRToastView.show(ctx, "下载成功", Toast.LENGTH_SHORT);
                    notificationManager.cancel(Integer.parseInt(mData.songinfo.song_id));//取消消息栏
                    break;
                case FAILEDCODE:
                    GRToastView.show(ctx, "下载失败", Toast.LENGTH_SHORT);
                    builder.setContentText("下载失败");
                    builder.setProgress(100, 0, false);
                    builder.setContentInfo("0%");
                    notificationManager.notify(Integer.parseInt(mData.songinfo.song_id), builder.build());
                    break;
                case UpdateProgressCode:
                    int number = (Integer) msg.obj;
                    builder.setProgress(100, number, false);
                    builder.setContentInfo(number + "%");
                    notificationManager.notify(Integer.parseInt(mData.songinfo.song_id), builder.build());
                    break;
            }
        }
    };

    //单实例
    public static DownloadMusicTask Instance(Context ctx, int priority) {
        if (instance == null) {
            synchronized (DownloadMusicTask.class) {
                if (instance == null)
                    instance = new DownloadMusicTask(ctx, priority);
            }
        }
        return instance;
    }

    private DownloadMusicTask(Context ctx, int priority) {
        super(priority);
        this.ctx = ctx;
    }

    public void pushData(PlayMusicSongBean mData) {
        this.mData = mData;
    }

    @Override
    protected void doRun() {
        if (notificationManager == null)
            notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (builder == null)
            builder = new NotificationCompat.Builder(ctx);
        //
        if (mData == null || StringUtil.isBlank(mData.bitrate.file_link))
            return;
        //
        builder.setTicker("下载任务");
        builder.setAutoCancel(true);//自动取消
        builder.setContentTitle(mData.songinfo.title);
        builder.setSmallIcon(R.drawable.play_icon_download);
        builder.setContentText("请稍等...");
        builder.setProgress(100, 0, false);
        builder.setContentInfo("0%");
        notificationManager.notify(Integer.parseInt(mData.songinfo.song_id), builder.build());
        try {
            downloadRequest = NoHttp.createDownloadRequest(mData.bitrate.file_link, RequestMethod.GET, SongDir, mData.songinfo.title + ".mp3", true, true);
            if (downloadRequest == null)
                return;
            SyncDownloadExecutor.INSTANCE.execute(0, downloadRequest, new DownLoad());
        } catch (Exception e) {
            dkhandler.sendMessage(dkhandler.obtainMessage(FAILEDCODE));
            e.printStackTrace();
        }

    }

    //下载下载监听
    class DownLoad implements DownloadListener {

        @Override
        public void onDownloadError(int what, Exception exception) {
            dkhandler.sendEmptyMessage(FAILEDCODE);
        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
            msg = dkhandler.obtainMessage(UpdateProgressCode);
            msg.obj = progress;
            dkhandler.sendMessage(msg);
        }

        @Override
        public void onFinish(int what, String filePath) {
            dkhandler.sendEmptyMessage(SUCCESSCODE);
        }

        @Override
        public void onCancel(int what) {

        }
    }
}
