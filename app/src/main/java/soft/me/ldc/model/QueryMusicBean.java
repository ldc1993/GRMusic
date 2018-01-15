package soft.me.ldc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudi on 2018/1/15.
 */

public class QueryMusicBean implements Serializable {
    static final long serialVersionUID = 1l;

    public int error_code;
    public ResultBean result;

    public static class ResultBean implements Serializable{
        static long serialVersionUID=1L;

        public TagInfoBean tag_info;
        public VideoInfoBean video_info;
        public String syn_words;
        public TopicInfoBean topic_info;
        public String query;
        public UserInfoBean user_info;
        public AlbumInfoBean album_info;
        public int rqt_type;
        public SongInfoBean song_info;
        public PlaylistInfoBean playlist_info;
        public ArtistInfoBean artist_info;

        public static class TagInfoBean implements Serializable{
            static long serialVersionUID=1L;
            /**
             * total : 0
             */

            public int total;

        }

        public static class VideoInfoBean implements Serializable{
            static long serialVersionUID=1L;
            /**
             * total : 65
             */

            public int total;


        }

        public static class TopicInfoBean implements Serializable{
            static long serialVersionUID=1L;
            /**
             * total : 0
             */

            public int total;


        }

        public static class UserInfoBean implements Serializable{
            static long serialVersionUID=1L;
            /**
             * total : 207
             */

            public int total;


        }

        public static class AlbumInfoBean implements Serializable{
            static long serialVersionUID=1L;

            public int total;
            public List<AlbumListBean> album_list;

            public static class AlbumListBean implements Serializable{
                static long serialVersionUID=1L;
                /**
                 * resource_type_ext : 0
                 * all_artist_id : 29
                 * publishtime : 2004-08-03
                 * company : 杰威尔JVR音乐有限公司
                 * album_desc :
                 * title : <em>七里香</em>
                 * album_id : 67909
                 * pic_small : http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90
                 * hot : 250
                 * author : 周杰伦
                 * artist_id : 29
                 */

                public String resource_type_ext;
                public String all_artist_id;
                public String publishtime;
                public String company;
                public String album_desc;
                public String title;
                public String album_id;
                public String pic_small;
                public int hot;
                public String author;
                public String artist_id;
            }
        }

        public static class SongInfoBean implements Serializable{
            static long serialVersionUID=1L;

            public int total;
            public List<SongListBean> song_list;


            public static class SongListBean implements Serializable{
                static long serialVersionUID=1L;

                public String resource_type_ext;
                public String has_filmtv;
                public int resource_type;
                public String mv_provider;
                public String del_status;
                public int havehigh;
                public String si_proxycompany;
                public String versions;
                public String toneid;
                public String info;
                public int has_mv;
                public String album_title;
                public String content;
                public String piao_id;
                public String artist_id;
                public String lrclink;
                public int data_source;
                public int relate_status;
                public int learn;
                public String album_id;
                public String biaoshi;
                public String bitrate_fee;
                public String song_source;
                public int is_first_publish;
                public int cluster_id;
                public int charge;
                public String copy_type;
                public String korean_bb_song;
                public String all_rate;
                public String title;
                public int has_mv_mobile;
                public String author;
                public String pic_small;
                public String song_id;
                public String all_artist_id;
                public String ting_uid;
            }
        }

        public static class PlaylistInfoBean implements Serializable{
            static long serialVersionUID=1L;
            /**
             * total : 1898
             */

            public int total;

        }

        public static class ArtistInfoBean implements Serializable{
            static long serialVersionUID=1L;

            public int total;
            public List<ArtistListBean> artist_list;


            public static class ArtistListBean implements Serializable{
                static long serialVersionUID=1L;
                /**
                 * ting_uid : 60867779
                 * song_num : 121
                 * country : 中国
                 * avatar_middle : http://qukufile2.qianqian.com/data2/pic/0a127ca37dc68eb9d7ab827029325d41/541302419/541302419.jpg@s_0,w_120
                 * album_num : 17
                 * artist_desc :
                 * author : 刘瑞琦
                 * artist_source : web
                 * artist_id : 43888407
                 */

                public String ting_uid;
                public int song_num;
                public String country;
                public String avatar_middle;
                public int album_num;
                public String artist_desc;
                public String author;
                public String artist_source;
                public String artist_id;
            }
        }
    }
}
