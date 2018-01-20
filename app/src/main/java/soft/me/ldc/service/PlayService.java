package soft.me.ldc.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.thread.service.MultiThreadService;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service implements MusicPlayer.OnErrorListener, IPlayMusic {


    MusicPlayer player = null;


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
    public void Data(PlayMusicSongBean mData) {
        try {
            if (player.isPlaying()) {
                player.reset();
                player.seekTo(0);
            }
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

    @Override
    public void SeekTo(int sec) {
        player.seekTo(sec);
    }

    @Override
    public String getDuration() {
        return null;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

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
    }

    @Override
    public void Reset() {
        player.reset();
    }

    @Override
    public void Looping(boolean b) {
        player.setLooping(b);
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
