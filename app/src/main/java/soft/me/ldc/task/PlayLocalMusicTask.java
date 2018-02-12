package soft.me.ldc.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.google.gson.Gson;

import soft.me.ldc.common.ThreadTask;
import soft.me.ldc.layout.PlayMusicActivity;
import soft.me.ldc.model.LocalMusicBean;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.service.HttpService;
import soft.me.ldc.utils.StringUtil;
import soft.me.ldc.view.GRToastView;

/**
 * Created by ldc45 on 2018/1/20.
 */

public class PlayLocalMusicTask extends ThreadTask {

    private static PlayLocalMusicTask instance = null;
    LocalMusicBean locMusicbean = null;
    //
    Context ctx;
    //消息
    Message msg = null;
    //
    Bundle bundle = null;
    //
    volatile boolean IsNewPlay = true;

    public static PlayLocalMusicTask Instance(Context ctx, int priority) {
        synchronized (PlayLocalMusicTask.class) {
            if (instance == null)
                instance = new PlayLocalMusicTask(ctx, priority);
        }
        return instance;

    }

    final static int FailedCode = 0x000;
    final static int SuccessCode = 0x001;
    final static int ErrorCode = 0x004;
    Handler dkhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SuccessCode:
                    PlayMusicSongBean mData = (PlayMusicSongBean) msg.obj;
                    if (mData != null) {
                        bundle = new Bundle();
                        bundle.putSerializable("play", mData);
                        bundle.putBoolean("play_new_song", IsNewPlay);
                        Intent it = new Intent();
                        it.putExtras(bundle);
                        it.setClass(ctx, PlayMusicActivity.class);
                        ctx.startActivity(it);

                    } else {
                        dkhandler.sendEmptyMessage(FailedCode);
                    }
                    break;
                case FailedCode:
                    GRToastView.show(ctx, "无效播放", Toast.LENGTH_SHORT);
                    break;
                case ErrorCode:
                    GRToastView.show(ctx, "错误播放", Toast.LENGTH_SHORT);
                    break;
            }
        }
    };

    private PlayLocalMusicTask(Context ctx, int priority) {
        super(priority);
        this.ctx = ctx;
    }

    public void pushData(LocalMusicBean locMusicbean) {
        this.locMusicbean = locMusicbean;
    }

    public void pushPlayState(boolean newPlay) {
        this.IsNewPlay = newPlay;
    }

    @Override
    protected void doRun() {
        if (locMusicbean == null)
            return;
        try {
            PlayMusicSongBean mDate = new PlayMusicSongBean();
            //解析数据
            mDate.songinfo.title = locMusicbean.title;
            mDate.songinfo.author = locMusicbean.author;
            mDate.bitrate.show_link = locMusicbean.path;
            if (mDate != null) {
                msg = new Message();
                msg.obj = mDate;
                msg.what = SuccessCode;
                dkhandler.sendMessage(msg);
            } else {
                dkhandler.sendEmptyMessage(FailedCode);
            }

        } catch (Exception e) {
            dkhandler.sendEmptyMessage(ErrorCode);
            e.printStackTrace();
        }

    }
}
