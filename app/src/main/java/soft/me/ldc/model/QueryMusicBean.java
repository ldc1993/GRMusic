package soft.me.ldc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudi on 2018/1/15.
 */

public class QueryMusicBean {


    /**
     * error_code : 22000
     * result : {"tag_info":{"total":0},"video_info":{"total":65},"syn_words":"","topic_info":{"total":0},"query":"七里香","user_info":{"total":207},"album_info":{"album_list":[{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2004-08-03","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"<em>七里香<\/em>","album_id":"67909","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","hot":250,"author":"周杰伦","artist_id":"29"},{"resource_type_ext":"0","all_artist_id":"544466456","publishtime":"2017-07-03","company":"顺音娱乐","album_desc":"","title":"<em>七里香<\/em>-中文舞曲专辑","album_id":"544468967","pic_small":"http://qukufile2.qianqian.com/data2/pic/78ff817ec469c5f2d20ae389ea5f2b88/544468967/544468967.jpg@s_1,w_90,h_90","hot":0,"author":"DJ阿奇","artist_id":"544466456"}],"total":6},"rqt_type":3,"song_info":{"song_list":[{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"代亚（上海）文化传媒中心","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"再次寻找周杰伦","content":"","piao_id":"0","artist_id":"43888407","lrclink":"","data_source":0,"relate_status":0,"learn":0,"album_id":"243077173","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90","song_id":"243120061","all_artist_id":"43888407","ting_uid":"60867779"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73330338","lrclink":"http://qukufile2.qianqian.com/data2/lrc/246654319/246654319.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"3","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/pic/3f9cc14180c9ac76d946dd6f46a98e1d/540045891/540045891.jpg@s_0,w_90","song_id":"73987452","all_artist_id":"73330338","ting_uid":"110935778"}],"total":22},"playlist_info":{"total":1898},"artist_info":{"artist_list":[{"ting_uid":"60867779","song_num":121,"country":"中国","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/0a127ca37dc68eb9d7ab827029325d41/541302419/541302419.jpg@s_0,w_120","album_num":17,"artist_desc":"","author":"刘瑞琦","artist_source":"web","artist_id":"43888407"},{"ting_uid":"7994","song_num":371,"country":"台湾","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/046d17bfa056e736d873ec4f891e338f/540336142/540336142.jpg@s_0,w_120","album_num":31,"artist_desc":"","author":"周杰伦","artist_source":"web","artist_id":"29"}],"total":5}}
     */

    public int error_code;
    public ResultBean result =new ResultBean();

    public static class ResultBean {
        /**
         * tag_info : {"total":0}
         * video_info : {"total":65}
         * syn_words :
         * topic_info : {"total":0}
         * query : 七里香
         * user_info : {"total":207}
         * album_info : {"album_list":[{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2004-08-03","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"<em>七里香<\/em>","album_id":"67909","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","hot":250,"author":"周杰伦","artist_id":"29"},{"resource_type_ext":"0","all_artist_id":"544466456","publishtime":"2017-07-03","company":"顺音娱乐","album_desc":"","title":"<em>七里香<\/em>-中文舞曲专辑","album_id":"544468967","pic_small":"http://qukufile2.qianqian.com/data2/pic/78ff817ec469c5f2d20ae389ea5f2b88/544468967/544468967.jpg@s_1,w_90,h_90","hot":0,"author":"DJ阿奇","artist_id":"544466456"}],"total":6}
         * rqt_type : 3
         * song_info : {"song_list":[{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"代亚（上海）文化传媒中心","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"再次寻找周杰伦","content":"","piao_id":"0","artist_id":"43888407","lrclink":"","data_source":0,"relate_status":0,"learn":0,"album_id":"243077173","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90","song_id":"243120061","all_artist_id":"43888407","ting_uid":"60867779"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73330338","lrclink":"http://qukufile2.qianqian.com/data2/lrc/246654319/246654319.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"3","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/pic/3f9cc14180c9ac76d946dd6f46a98e1d/540045891/540045891.jpg@s_0,w_90","song_id":"73987452","all_artist_id":"73330338","ting_uid":"110935778"}],"total":22}
         * playlist_info : {"total":1898}
         * artist_info : {"artist_list":[{"ting_uid":"60867779","song_num":121,"country":"中国","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/0a127ca37dc68eb9d7ab827029325d41/541302419/541302419.jpg@s_0,w_120","album_num":17,"artist_desc":"","author":"刘瑞琦","artist_source":"web","artist_id":"43888407"},{"ting_uid":"7994","song_num":371,"country":"台湾","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/046d17bfa056e736d873ec4f891e338f/540336142/540336142.jpg@s_0,w_120","album_num":31,"artist_desc":"","author":"周杰伦","artist_source":"web","artist_id":"29"}],"total":5}
         */

        public TagInfoBean tag_info =new TagInfoBean();
        public VideoInfoBean video_info=new VideoInfoBean();
        public String syn_words="";
        public TopicInfoBean topic_info =new TopicInfoBean();
        public String query="";
        public UserInfoBean user_info =new UserInfoBean();
        public AlbumInfoBean album_info=new AlbumInfoBean();
        public int rqt_type;
        public SongInfoBean song_info =new SongInfoBean();
        public PlaylistInfoBean playlist_info=new PlaylistInfoBean();
        public ArtistInfoBean artist_info=new ArtistInfoBean();

        public static class TagInfoBean {
            /**
             * total : 0
             */

            public int total;

        }

        public static class VideoInfoBean {
            /**
             * total : 65
             */

            public int total;

        }

        public static class TopicInfoBean {
            /**
             * total : 0
             */

            public int total;

        }

        public static class UserInfoBean {
            /**
             * total : 207
             */

            public int total;
        }

        public static class AlbumInfoBean {
            /**
             * album_list : [{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2004-08-03","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"<em>七里香<\/em>","album_id":"67909","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","hot":250,"author":"周杰伦","artist_id":"29"},{"resource_type_ext":"0","all_artist_id":"544466456","publishtime":"2017-07-03","company":"顺音娱乐","album_desc":"","title":"<em>七里香<\/em>-中文舞曲专辑","album_id":"544468967","pic_small":"http://qukufile2.qianqian.com/data2/pic/78ff817ec469c5f2d20ae389ea5f2b88/544468967/544468967.jpg@s_1,w_90,h_90","hot":0,"author":"DJ阿奇","artist_id":"544466456"}]
             * total : 6
             */

            public int total;
            public List<AlbumListBean> album_list;

            public static class AlbumListBean {
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

        public static class SongInfoBean {
            /**
             * song_list : [{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"代亚（上海）文化传媒中心","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"再次寻找周杰伦","content":"","piao_id":"0","artist_id":"43888407","lrclink":"","data_source":0,"relate_status":0,"learn":0,"album_id":"243077173","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90","song_id":"243120061","all_artist_id":"43888407","ting_uid":"60867779"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73330338","lrclink":"http://qukufile2.qianqian.com/data2/lrc/246654319/246654319.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"3","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/pic/3f9cc14180c9ac76d946dd6f46a98e1d/540045891/540045891.jpg@s_0,w_90","song_id":"73987452","all_artist_id":"73330338","ting_uid":"110935778"}]
             * total : 22
             */

            public int total;
            public List<SongListBean> song_list =new ArrayList<>();

            public static class SongListBean {
                /**
                 * resource_type_ext : 0
                 * has_filmtv : 0
                 * resource_type : 0
                 * mv_provider : 0000000000
                 * del_status : 0
                 * havehigh : 2
                 * si_proxycompany : 代亚（上海）文化传媒中心
                 * versions :
                 * toneid : 0
                 * info :
                 * has_mv : 0
                 * album_title : 再次寻找周杰伦
                 * content :
                 * piao_id : 0
                 * artist_id : 43888407
                 * lrclink :
                 * data_source : 0
                 * relate_status : 0
                 * learn : 0
                 * album_id : 243077173
                 * biaoshi : lossless
                 * bitrate_fee : {"0":"0|0","1":"0|0"}
                 * song_source : web
                 * is_first_publish : 0
                 * cluster_id : 0
                 * charge : 0
                 * copy_type : 1
                 * korean_bb_song : 0
                 * all_rate : 64,128,256,320
                 * title : 七里香
                 * has_mv_mobile : 0
                 * author : 刘瑞琦
                 * pic_small : http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90
                 * song_id : 243120061
                 * all_artist_id : 43888407
                 * ting_uid : 60867779
                 */

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

        public static class PlaylistInfoBean {
            /**
             * total : 1898
             */

            public int total;
        }

        public static class ArtistInfoBean {
            /**
             * artist_list : [{"ting_uid":"60867779","song_num":121,"country":"中国","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/0a127ca37dc68eb9d7ab827029325d41/541302419/541302419.jpg@s_0,w_120","album_num":17,"artist_desc":"","author":"刘瑞琦","artist_source":"web","artist_id":"43888407"},{"ting_uid":"7994","song_num":371,"country":"台湾","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/046d17bfa056e736d873ec4f891e338f/540336142/540336142.jpg@s_0,w_120","album_num":31,"artist_desc":"","author":"周杰伦","artist_source":"web","artist_id":"29"}]
             * total : 5
             */

            public int total;
            public List<ArtistListBean> artist_list;

            public static class ArtistListBean {
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
