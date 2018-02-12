package soft.me.ldc.task;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.download.SyncDownloadExecutor;

import java.io.File;

import soft.me.ldc.common.ThreadTask;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.model.PlayMusicSongBean;
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
    static DownloadMusicTask instance = null;
    //
    final static int SUCCESSCODE = 0x001;
    final static int FAILEDCODE = 0x002;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESSCODE:
                    GRToastView.show(ctx, "下载成功", Toast.LENGTH_SHORT);
                    break;
                case FAILEDCODE:
                    break;
            }
        }
    };

    //单实例
    public static DownloadMusicTask Instance(Context ctx, int priority) {
        synchronized (DownloadMusicTask.class) {
            if (instance == null)
                instance = new DownloadMusicTask(ctx, priority);
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
        if (mData == null)
            return;
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
            Log.e("LLL", "" + progress);
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
