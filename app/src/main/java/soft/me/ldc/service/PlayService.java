package soft.me.ldc.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import soft.me.ldc.iface.IPlayMusic;

/**
 * Created by ldc45 on 2018/1/16.
 */

public class PlayService extends Service implements IPlayMusic {
    MediaPlayer player = null;
    Uri uri = null;
    Bundle bundle = null;
    //标识
    public final static int PlayCode = 0x001;
    public final static int PauseCode = 0x002;
    public final static int StopCode = 0x003;
    volatile int musicFlag = StopCode;
    volatile String musicUrl = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        try {
            bundle = intent.getExtras();
            musicFlag = bundle.getInt("command", StopCode);
            musicUrl = bundle.getString("url", "");
            uri = Uri.parse(musicUrl);
            if (player == null)
                player = MediaPlayer.create(this, uri);
            player.setLooping(false);

        } catch (Exception e) {
            musicFlag = StopCode;
            e.printStackTrace();
        }

        switch (musicFlag) {
            case PlayCode:
                Play();
                break;
            case PauseCode:
                Pause();
                break;
            case StopCode:
                Stop();
                break;
        }
        return START_STICKY;
    }

    //销毁服务
    @Override
    public void onDestroy() {
        if (player != null) {
            player.stop();//停止播放
            player.release();//释放资源
            player = null;//把player对象设置为null
        }
        super.onDestroy();
    }


    //音乐操作

    @Override
    public void Play() {
        if (player != null && !player.isPlaying()) {
            player.start();//开始播放音乐

        }
    }

    @Override
    public void Stop() {
        // 当player对象不为空时
        if (player != null) {
           try{
               player.reset();
               player.prepare();
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }

    @Override
    public void Pause() {
        // 当player对象正在播放时并且player对象不为空时
        if (player.isPlaying() && player != null) {
            player.pause();//暂停播放音乐
        }
    }

    @Override
    public void Looping(boolean b) {
        if (player != null)
            player.setLooping(b);
    }
}
