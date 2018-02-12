package soft.me.ldc.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import soft.me.ldc.model.LocalMusicBean;

/**
 * Created by liudi on 2018/2/12.
 */

public class QueryLoadMusicUtil {
    static QueryLoadMusicUtil instance = null;
    Context ctx = null;

    public static QueryLoadMusicUtil Instance(Context ctx) {
        synchronized (QueryLoadMusicUtil.class) {
            if (instance == null)
                instance = new QueryLoadMusicUtil(ctx);
        }
        return instance;
    }

    //构造函数
    private QueryLoadMusicUtil(Context ctx) {
        this.ctx = ctx;
    }

    //获取本地音乐
    public List<LocalMusicBean> QueryMusic() {
        List<LocalMusicBean> list = new ArrayList<>();
        try {
            // 媒体库查询语句（写一个工具类MusicUtils）
            Cursor cursor = ctx.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                    null, MediaStore.Audio.AudioColumns.IS_MUSIC);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    LocalMusicBean song = new LocalMusicBean();
                    song.title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    song.author = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                    if (song.size > 1000 * 800) {
                        // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）
                        if (song.title.contains("-")) {
                            String[] str = song.title.split("-");
                            song.author = str[0];
                            song.title = str[1];
                        }
                        list.add(song);
                    }
                }
                // 释放资源
                cursor.close();
            }
        } catch (Exception e) {
            list.clear();
            e.printStackTrace();
        }

        return list;
    }

}
