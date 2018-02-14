package soft.me.ldc.iface;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.model.PlayMusicSongBean;

/**
 * Created by ldc45 on 2018/1/16.
 */

public interface IPlayMusic {
    //播放
    void Play(PlayMusicSongBean mData);

    //是否有播放文件
    boolean HasData();

    //是否可用
    boolean HasEnable();

    //是否在播放
    boolean IsPlaying();

    //是否循环
    boolean IsLooping();

    //开始
    void Start();

    //暂停
    void Pause();

    //播放点
    void SeekTo(int sec);

    //大小
    int getDuration();

    //当前大小
    int getCurrentPosition();

    //停止
    void Stop();

    //重置
    void Reset();

    //设置循环
    void Looping(boolean b);

    //设置数据
    PlayMusicSongBean MusicSrc();
}
