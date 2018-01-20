package soft.me.ldc.task;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;

import java.io.IOException;

import soft.me.ldc.iface.IPlayMusic;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.thread.ThreadTask;

/**
 * Created by ldc45 on 2018/1/20.
 */

public class PlayMusicTask extends ThreadTask implements IPlayMusic {
    //播放器
    MediaPlayer player = null;
    //数据
    PlayMusicSongBean mData = null;
    //
    Context ctx;
    //单实例
    private static PlayMusicTask instance = null;


    // TODO: 2018/1/20  消息
    public final static int PrevCode = 0x001;
    public final static int PlayorPauseCode = 0x002;
    public final static int NextCode = 0x003;
    volatile int MusicCode = 0x004;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PrevCode:
                    Prev();
                    break;
                case PlayorPauseCode:
                    PlayorPause();
                    break;
                case NextCode:
                    Next();
                    break;
            }
        }
    };


    public static PlayMusicTask newInstance(Context ctx, int priority) {

        synchronized (PlayMusicTask.class) {
            if (instance == null)
                instance = new PlayMusicTask(ctx, priority);
        }
        return instance;
    }

    private PlayMusicTask(Context ctx, int priority) {
        super(priority);
        this.ctx = ctx;
    }

    // TODO: 2018/1/20 添加数据
    public void pushData(int Code, PlayMusicSongBean mData) {
        this.mData = mData;
        MusicCode = Code;
    }

    @Override
    protected void doRun() {

        try {
            if (mData == null)
                return;
            if (player == null) {
                player = MediaPlayer.create(ctx, Uri.parse(mData.bitrate.show_link));
            } else {
                player.setDataSource(ctx, Uri.parse(mData.bitrate.show_link));
            }
            player.setLooping(false);

            dkhandler.sendEmptyMessage(MusicCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void PlayorPause() {
        if (player != null && !player.isPlaying()) {
            player.start();//开始播放音乐
        } else if (player.isPlaying() && player != null) {
            player.pause();//暂停播放音乐
        }
    }

    @Override
    public void Prev() {

    }

    @Override
    public void Next() {

    }

    @Override
    public void Looping(boolean b) {

    }
}
