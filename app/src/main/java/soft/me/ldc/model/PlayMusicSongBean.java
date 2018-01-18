package soft.me.ldc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldc45 on 2018/1/17.
 */

public class PlayMusicSongBean implements Serializable {
    static final long serialVersionUID = 1l;
    public SongurlBean songurl;
    public int error_code;
    public SonginfoBean songinfo;

    public static class SongurlBean implements Serializable {
        static final long serialVersionUID = 1l;
        public List<UrlBean> url;


        public static class UrlBean implements Serializable {
            static final long serialVersionUID = 1l;


            public String show_link;
            public int down_type;
            public int original;
            public int free;
            public String replay_gain;
            public int song_file_id;
            public int file_size;
            public String file_extension;
            public int file_duration;
            public int can_see;
            public boolean can_load;
            public int preload;
            public int file_bitrate;
            public String file_link;
            public int is_udition_url;
            public String hash;

        }
    }

    public static class SonginfoBean implements Serializable {
        static final long serialVersionUID = 1l;

        public String resource_type_ext;
        public String resource_type;
        public String del_status;
        public int collect_num;
        public String hot;
        public String res_reward_flag;
        public String sound_effect;
        public String title;
        public String language;
        public int play_type;
        public String country;
        public String biaoshi;
        public String bitrate_fee;
        public String song_source;
        public int is_first_publish;
        public String artist_640_1136;
        public String is_secret;
        public int charge;
        public String copy_type;
        public String share_url;
        public int has_mv_mobile;
        public String album_no;
        public String is_charge;
        public String pic_radio;
        public String has_filmtv;
        public String pic_huge;
        public String ting_uid;
        public int expire;
        public String bitrate;
        public String si_proxycompany;
        public String compose;
        public String toneid;
        public String area;
        public String info;
        public String artist_500_500;
        public String multiterminal_copytype;
        public int has_mv;
        public String total_listen_nums;
        public String song_id;
        public String piao_id;
        public String high_rate;
        public String compress_status;
        public int original;
        public String artist_480_800;
        public String relate_status;
        public int learn;
        public String artist;
        public String pic_big;
        public int comment_num;
        public String songwriting;
        public String pic_singer;
        public String album_1000_1000;
        public String album_id;
        public int share_num;
        public String album_500_500;
        public String aliasname;
        public String album_title;
        public String korean_bb_song;
        public String author;
        public String all_artist_id;
        public String file_duration;
        public String publishtime;
        public String versions;
        public String artist_1000_1000;
        public String res_encryption_flag;
        public String all_rate;
        public String artist_id;
        public String distribution;
        public String lrclink;
        public String pic_small;
        public String original_rate;
        public int havehigh;
        public String pic_premium;
        public List<ArtistListBean> artist_list;


        public static class ArtistListBean implements Serializable {
            static final long serialVersionUID = 1l;

            public String avatar_mini;
            public String avatar_s300;
            public String ting_uid;
            public String del_status;
            public String avatar_s500;
            public String artist_name;
            public String avatar_small;
            public String avatar_s180;
            public String artist_id;

        }
    }
}
