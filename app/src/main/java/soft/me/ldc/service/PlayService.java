package soft.me.ldc.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service implements MusicPlayer.OnErrorListener, IPlayMusic {
    MusicPlayer player = null;
    PlayMusicSongBean mData = null;

    @Override
    public IBinder onBind(Intent intent) {
        player = MusicPlayer.newInstance(PlayService.this);
        return new ServiceBind();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Play() {
        if (!player.isPlaying())
            player.start();
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
        return player.getDuration();
    }

    //回去当前进度条
    @Override
    public int getCurrentPosition() {
        return 0;
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
        player.reset();
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

    // TODO: 2018/1/20  绑定服务
    public class ServiceBind extends Binder {
        //获取服务
        public PlayService Service() {
            return PlayService.this;
        }


    }
}
