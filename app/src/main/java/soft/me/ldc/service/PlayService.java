package soft.me.ldc.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import java.io.IOException;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service {
    volatile MusicPlayer player = null;
    volatile float currSize = 0;
    volatile float allSize = 0;

    Handler dkhandler = new Handler();
    //更新当前进度
    private Runnable CurrPlaySizeRun = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                currSize = player.getCurrentPosition();
            }
            dkhandler.postDelayed(this, 1000);
        }
    };

    //实例化播放器
    private void initMediaPlay() {
        player = MusicPlayer.newInstance(PlayService.this);
        player.setScreenOnWhilePlaying(true);//屏幕唤醒
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(new MediaPlayerListener());
        player.setOnCompletionListener(new MediaPlayerListener());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBind();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        player = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMediaPlay();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initMediaPlay();
        return super.onStartCommand(intent, flags, startId);
    }

    // TODO: 2018/2/14
    class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

        //完成
        @Override
        public void onCompletion(MediaPlayer mp) {
            //播放完成结束更新
            dkhandler.removeCallbacks(CurrPlaySizeRun);
        }

        //准备
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
            allSize = mp.getDuration();//获取文件大小
            dkhandler.post(CurrPlaySizeRun);
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }
    }


    // TODO: 2018/1/20  绑定服务
    public class ServiceBind extends Binder implements IPlayMusic {
        volatile PlayMusicSongBean mData = null;

        @Override
        public void Play(PlayMusicSongBean mData) {
            try {
                this.mData = mData;
                //资源准备
                player.reset();
                player.seekTo(0);
                player.setDataSource(PlayService.this, Uri.parse(mData.bitrate.show_link));
                player.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean HasData() {
            boolean enable = true;
            if (mData == null) {
                enable = false;
            }
            return enable;
        }

        @Override
        public boolean HasEnable() {
            boolean enable = true;
            if (player == null) {
                enable = true;
            }
            return enable;
        }

        @Override
        public boolean IsPlaying() {
            return player.isPlaying();
        }

        @Override
        public boolean IsLooping() {
            return player.isLooping();
        }

        @Override
        public void Start() {
            if (!player.isPlaying()) {
                player.start();
            }
        }

        //暂停
        @Override
        public void Pause() {
            if (player.isPlaying())
                player.pause();
        }

        //滑动
        @Override
        public void SeekTo(int sec) {
            player.seekTo(sec);
        }

        //当前播放长度
        @Override
        public int getDuration() {
            return (int) allSize;
        }

        //回去当前进度条
        @Override
        public int getCurrentPosition() {
            return (int) currSize;

        }

        @Override
        public void Stop() {
            player.stop();
            try {
                player.prepare();
                player.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            player.release();//释放资源
        }

        @Override
        public void Reset() {
            try {
                player.reset();
                player.seekTo(0);
                player.setDataSource(PlayService.this, Uri.parse(mData.bitrate.show_link));
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void Looping(boolean b) {
            player.setLooping(b);
        }

        //音乐资源
        @Override
        public PlayMusicSongBean MusicSrc() {
            return mData;
        }


    }

    // TODO: 2018/2/12 生命周期
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
        this.startService(new Intent(this, PlayService.class));
    }
}
