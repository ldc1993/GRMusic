package soft.me.ldc.model;

import java.io.Serializable;

/**
 * Created by ldc45 on 2018/1/17.
 */

public class PlayMusicSongBean implements Serializable {
    static final long serialVersionUID = 1l;

    public SonginfoBean songinfo =new SonginfoBean();
    public int error_code;
    public BitrateBean bitrate=new BitrateBean();

   

    public static class SonginfoBean implements Serializable {
        static final long serialVersionUID = 1l;

        public int special_type;
        public String pic_huge;
        public String ting_uid;
        public String pic_premium;
        public int havehigh;
        public String si_proxycompany;
        public String author;
        public String toneid;
        public int has_mv;
        public String song_id;
        public String title;
        public String artist_id;
        public String lrclink;
        public String relate_status;
        public int learn;
        public String pic_big;
        public int play_type;
        public String album_id;
        public String pic_radio;
        public String bitrate_fee;
        public String song_source;
        public String all_artist_id;
        public String all_artist_ting_uid;
        public String piao_id;
        public int charge;
        public String copy_type;
        public String all_rate;
        public String korean_bb_song;
        public int is_first_publish;
        public int has_mv_mobile;
        public String album_title;
        public String pic_small;
        public String album_no;
        public String resource_type_ext;
        public String resource_type;

       
    }

    public static class BitrateBean implements Serializable {
        static final long serialVersionUID = 1l;
        /**
         * show_link : http://zhangmenshiting.qianqian.com/data2/music/42783748/42783748.mp3?xcode=805eed9cf626686d4394f800f7f2550e
         * free : 1
         * song_file_id : 42783748
         * file_size : 2679447
         * file_extension : mp3
         * file_duration : 322
         * file_bitrate : 64
         * file_link : http://zhangmenshiting.qianqian.com/data2/music/42783748/42783748.mp3?xcode=805eed9cf626686d4394f800f7f2550e
         * hash : 74d926076dc8f2f857ffaa403d79935a65f80dec
         */

        public String show_link;
        public int free;
        public int song_file_id;
        public int file_size;
        public String file_extension;
        public int file_duration;
        public int file_bitrate;
        public String file_link;
        public String hash;
        
    }
}
