package soft.me.ldc.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.TimedMetaData;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service implements MusicPlayer.OnErrorListener, IPlayMusic {
    volatile MusicPlayer player = null;
    volatile PlayMusicSongBean mData = null;
    volatile float CurrSize = 0;
    volatile float AllSize = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBind();
    }

    // TODO: 2018/1/20  绑定服务
    public class ServiceBind extends Binder {
        // TODO: 2018/2/11  获取服务 并实例化播放器
        public PlayService Service() {
            player = MusicPlayer.newInstance(PlayService.this);
            return PlayService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
        this.startService(new Intent(this, PlayService.class));
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void PushData(PlayMusicSongBean mData) {
        try {
            this.mData = mData;
            //资源准备
            player.reset();
            player.seekTo(0);
            player.setDataSource(this, Uri.parse(mData.bitrate.show_link));
            player.prepare();
            AllSize = player.getDuration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Play() {
        if (!player.isPlaying()) {
            player.start();
        }
    }

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
        return (int) AllSize;
    }

    //回去当前进度条
    @Override
    public int getCurrentPosition() {
        return (int) CurrSize;
    }

    //上一首
    @Override
    public void Prev() {
        player.reset();

    }

    @Override
    public void Next() {
        player.reset();
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
            player.setDataSource(this, Uri.parse(mData.bitrate.show_link));
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
    public PlayMusicSongBean MusicBean() {
        return mData;
    }

    @Override
    public MusicPlayer Player() {
        return player;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    class MediaPlayLisener implements
            MediaPlayer.OnCompletionListener {


        //播放完成
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!mp.isLooping()) {
                mp.reset();
                mp.seekTo(0);
            }
        }
    }
}
