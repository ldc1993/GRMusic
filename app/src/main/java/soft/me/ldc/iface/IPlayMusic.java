package soft.me.ldc.iface;

import soft.me.ldc.component.MusicPlayer;
import soft.me.ldc.model.PlayMusicSongBean;

/**
 * Created by ldc45 on 2018/1/16.
 */

public interface IPlayMusic {
    void Data(PlayMusicSongBean mData);

    void Play();

    void Pause();

    void SeekTo(int sec);

    String getDuration();

    int getCurrentPosition();

    void Prev();

    void Next();

    void Stop();

    void Reset();

    void Looping(boolean b);

    MusicPlayer Player();
}
