package soft.me.ldc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldc45 on 2018/1/17.
 */

public class PlayMusicSongBean implements Serializable {
    static final long serialVersionUID = 1l;


    private SongurlBean songurl;
    private int error_code;
    private SonginfoBean songinfo;

    public static class SongurlBean implements Serializable {
        static final long serialVersionUID = 1l;
        private List<UrlBean> url;


        public static class UrlBean implements Serializable {
            static final long serialVersionUID = 1l;


            private String show_link;
            private int down_type;
            private int original;
            private int free;
            private String replay_gain;
            private int song_file_id;
            private int file_size;
            private String file_extension;
            private int file_duration;
            private int can_see;
            private boolean can_load;
            private int preload;
            private int file_bitrate;
            private String file_link;
            private int is_udition_url;
            private String hash;

        }
    }

    public static class SonginfoBean implements Serializable {
        static final long serialVersionUID = 1l;

        private String resource_type_ext;
        private String resource_type;
        private String del_status;
        private int collect_num;
        private String hot;
        private String res_reward_flag;
        private String sound_effect;
        private String title;
        private String language;
        private int play_type;
        private String country;
        private String biaoshi;
        private String bitrate_fee;
        private String song_source;
        private int is_first_publish;
        private String artist_640_1136;
        private String is_secret;
        private int charge;
        private String copy_type;
        private String share_url;
        private int has_mv_mobile;
        private String album_no;
        private String is_charge;
        private String pic_radio;
        private String has_filmtv;
        private String pic_huge;
        private String ting_uid;
        private int expire;
        private String bitrate;
        private String si_proxycompany;
        private String compose;
        private String toneid;
        private String area;
        private String info;
        private String artist_500_500;
        private String multiterminal_copytype;
        private int has_mv;
        private String total_listen_nums;
        private String song_id;
        private String piao_id;
        private String high_rate;
        private String compress_status;
        private int original;
        private String artist_480_800;
        private String relate_status;
        private int learn;
        private String artist;
        private String pic_big;
        private int comment_num;
        private String songwriting;
        private String pic_singer;
        private String album_1000_1000;
        private String album_id;
        private int share_num;
        private String album_500_500;
        private String aliasname;
        private String album_title;
        private String korean_bb_song;
        private String author;
        private String all_artist_id;
        private String file_duration;
        private String publishtime;
        private String versions;
        private String artist_1000_1000;
        private String res_encryption_flag;
        private String all_rate;
        private String artist_id;
        private String distribution;
        private String lrclink;
        private String pic_small;
        private String original_rate;
        private int havehigh;
        private String pic_premium;
        private List<ArtistListBean> artist_list;


        public static class ArtistListBean implements Serializable {
            static final long serialVersionUID = 1l;

            private String avatar_mini;
            private String avatar_s300;
            private String ting_uid;
            private String del_status;
            private String avatar_s500;
            private String artist_name;
            private String avatar_small;
            private String avatar_s180;
            private String artist_id;

        }
    }
}
