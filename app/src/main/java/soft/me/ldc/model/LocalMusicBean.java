package soft.me.ldc.model;

import java.io.Serializable;

/**
 * Created by liudi on 2018/2/12.
 */

public class LocalMusicBean implements Serializable {
    final static long serialVersionUID = 1l;
    /**
     * 歌手
     */
    public String author;
    /**
     * 歌曲名
     */
    public String title;
    /**
     * 歌曲的地址
     */
    public String path;
    /**
     * 歌曲长度
     */
    public int duration;
    /**
     * 歌曲的大小
     */
    public long size;

}
