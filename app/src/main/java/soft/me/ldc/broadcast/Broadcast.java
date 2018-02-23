package soft.me.ldc.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import soft.me.ldc.common.pool.MultiThreadPool;
import soft.me.ldc.model.LocalMusicBean;
import soft.me.ldc.model.PlayMusicSongBean;
import soft.me.ldc.task.PlayLocalMusicTask;
import soft.me.ldc.task.PlayMusicTask;
import soft.me.ldc.utils.StringUtil;

/**
 * Created by LDC on 2018/2/23.
 */

public class Broadcast extends BroadcastReceiver {
    private PlayMusicSongBean bean = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            bean = (PlayMusicSongBean) intent.getSerializableExtra("data");
            if (bean != null && StringUtil.isNotBlank(bean.songinfo.song_id)) {
                PlayMusicTask playMusicTask = PlayMusicTask.Instance(context, 1);
                playMusicTask.pushData(bean.songinfo.song_id);
                playMusicTask.pushPlayState(false);
                MultiThreadPool.newInsance().pushThread(playMusicTask);
            } else {
                LocalMusicBean localMusicBean = new LocalMusicBean();
                //解析数据
                localMusicBean.title = bean.songinfo.title;
                localMusicBean.author = bean.songinfo.author;
                localMusicBean.path = bean.bitrate.show_link;
                PlayLocalMusicTask playLocalMusicTask = PlayLocalMusicTask.Instance(context, 1);
                playLocalMusicTask.pushData(localMusicBean);
                playLocalMusicTask.pushPlayState(false);
                MultiThreadPool.newInsance().pushThread(playLocalMusicTask);
            }

        } catch (Exception e) {

        }

    }
}
