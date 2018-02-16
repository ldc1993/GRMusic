package soft.me.ldc.layout;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.download.SyncDownloadExecutor;

import java.io.File;

import butterknife.BindView;
import me.wcy.lrcview.LrcView;
import soft.me.ldc.R;
import soft.me.ldc.base.RootFragment;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.service.PlayService;
import soft.me.ldc.utils.StringUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicLyricFragment extends RootFragment {
    //歌词下载路径
    final static String LrcDir = AppConfig.RootDir + "lrc" + File.separator;
    //播放服务
    volatile PlayService.ServiceBind playService = null;
    //数据
    volatile PlayMusicSongBean mData = null;
    //下载请求
    DownloadRequest downloadRequest = null;
    @BindView(R.id.mLrcView)
    LrcView mLrcView;
    //消息
    Message msg = null;
    //
    final static int FAILEDCODE = 0x000;//失败
    final static int SUCCESSCODE = 0x001;//成功
    final static int UPDATECODE = 0x002;//更新
    final static int DOWNLOADCODE = 0x003;//下载
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FAILEDCODE:
                    mLrcView.setLabel("没有找到歌词");
                    break;
                case SUCCESSCODE:
                    String lrc = (String) msg.obj;
                    mLrcView.loadLrc(new File(lrc));
                    //更新歌词
                    dkhandler.post(refreshLrc);
                    break;
                case UPDATECODE:
                    if (playService.IsPlaying()) {
                        if (mLrcView.hasLrc()) {
                            int time = (int) msg.obj;
                            mLrcView.updateTime(time);
                        }
                    } else {
                        mLrcView.updateTime(0);
                    }
                    break;
                case DOWNLOADCODE://下载歌词
                    new Thread(downloadLrc).start();
                    break;
            }
        }
    };


    //添加数据
    public void pushData(PlayMusicSongBean mData) {
        this.mData = mData;
    }

    @Override
    protected void NewCreate(@Nullable Bundle savedInstanceState) throws Exception {
        PlayMusicMusicActivity playMusicActivity = (PlayMusicMusicActivity) act;
        playService = playMusicActivity.getPlayService();
    }

    @Override
    protected View UI(LayoutInflater inflater) throws Exception {
        View mainView = inflater.inflate(R.layout.fragment_play_music_lyric, null, false);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return mainView;
    }

    @Override
    protected void Init() throws Exception {
        mLrcView.setOnPlayClickListener(new PlayListener());
        mLrcView.updateTime(0);
        mLrcView.setCurrentColor(Color.RED);
        mLrcView.computeScroll();

    }

    @Override
    protected void Main() throws Exception {
        dkhandler.sendEmptyMessage(DOWNLOADCODE);
    }


    // TODO: 2018/2/13 播放事件
    class PlayListener implements LrcView.OnPlayClickListener {

        @Override
        public boolean onPlayClick(long time) {
            playService.SeekTo((int) time);
            return true;
        }
    }


    //更新歌词
    private Runnable refreshLrc = new Runnable() {
        @Override
        public void run() {
            if (playService != null && playService.HasEnable() && playService.IsPlaying() && mLrcView.hasLrc()) {
                msg = dkhandler.obtainMessage(UPDATECODE);
                msg.obj = playService.getCurrentPosition();
                dkhandler.sendMessage(msg);
            }
            dkhandler.postDelayed(this, 600);
        }
    };

    //下载歌词
    private Runnable downloadLrc = new Runnable() {
        @Override
        public void run() {
            if (mData != null && StringUtil.isNotBlank(mData.songinfo.lrclink)) {
                downloadRequest = NoHttp.createDownloadRequest(mData.songinfo.lrclink, RequestMethod.GET, LrcDir, mData.songinfo.title + ".lrc", true, true);
                if (downloadRequest == null)
                    return;
                SyncDownloadExecutor.AsyncRequestExecutor.execute(1, downloadRequest, new DownLoadLrc());
            }
        }
    };

    // TODO: 2018/2/13  下载歌词
    class DownLoadLrc implements DownloadListener {

        @Override
        public void onDownloadError(int what, Exception exception) {
            dkhandler.sendEmptyMessage(FAILEDCODE);
        }

        @Override
        public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

        }

        @Override
        public void onProgress(int what, int progress, long fileCount, long speed) {
        }

        @Override
        public void onFinish(int what, String filePath) {
            msg = dkhandler.obtainMessage(SUCCESSCODE);
            msg.obj = filePath;
            dkhandler.sendMessage(msg);
        }

        @Override
        public void onCancel(int what) {
            dkhandler.sendEmptyMessage(FAILEDCODE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dkhandler.removeCallbacks(refreshLrc);
    }
}
