package soft.me.ldc.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import java.io.IOException;

import soft.me.ldc.R;
import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.config.AppConfig;
import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service {
    volatile MusicPlayer player = null;
    volatile float currSize = 0;
    volatile float allSize = 0;
    volatile PlayMusicSongBean mData = null;
    //通知栏
    NotificationCompat.Builder builder = null;
    NotificationManager notificationManager = null;
    RemoteViews remoteViews = null;
    volatile int notiId = 999;

    //
    final int PLAYCODE = 0x000;//播放
    final int PAUSECODE = 0x001;//暂停
    final int NEXTCODE = 0x002;//下一首
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PLAYCODE:

                    notificationManager.notify(notiId, builder.build());
                    break;
                case PAUSECODE:
                    break;
                case NEXTCODE:
                    break;
            }
        }
    };
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
        player.setOnSeekCompleteListener(new MediaPlayerListener());

    }

    //初始化对话框
    private void initNotify() {
        //初始化
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (builder == null)
            builder = new NotificationCompat.Builder(this);
        if (remoteViews == null)
            remoteViews = new RemoteViews(getPackageName(), R.layout.notifi_music_view);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //builder.setAutoCancel(true);
        builder.setOngoing(true);//常驻
        builder.setWhen(System.currentTimeMillis());//展示时间
        builder.setTicker("欢迎使用~");
        //builder.setCustomBigContentView(remoteViews);
        builder.setContentIntent(PendingIntent.getBroadcast(this, notiId, contentIt(), PendingIntent.FLAG_UPDATE_CURRENT));

    }

    //点击状态栏
    private Intent contentIt() {
        Intent it = new Intent();
        it.setAction(AppConfig.INSTANCE.broadCast_ItCode);
        if (this.mData != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", this.mData);
            it.putExtras(bundle);
        }
        return it;
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
        notificationManager.cancel(notiId);
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
    class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener {

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
            dkhandler.sendEmptyMessage(PLAYCODE);//播放
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }

        @Override
        public void onSeekComplete(MediaPlayer mp) {
            mp.start();
        }
    }


    // TODO: 2018/1/20  绑定服务
    public class ServiceBind extends Binder implements IPlayMusic {


        @Override
        public void Play(PlayMusicSongBean mData) {
            try {
                PlayService.this.mData = mData;
                //资源准备
                player.reset();
                player.seekTo(0);
                player.setDataSource(PlayService.this, Uri.parse(mData.bitrate.show_link));
                player.prepareAsync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //初始化对话框
                initNotify();
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
            if (player.isPlaying()) {
                player.pause();
                dkhandler.sendEmptyMessage(PAUSECODE);
            }
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
