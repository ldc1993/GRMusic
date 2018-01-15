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

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

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
             * album_list : [{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2004-08-03","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"<em>七里香<\/em>","album_id":"67909","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","hot":250,"author":"周杰伦","artist_id":"29"},{"resource_type_ext":"0","all_artist_id":"544466456","publishtime":"2017-07-03","company":"顺音娱乐","album_desc":"","title":"<em>七里香<\/em>-中文舞曲专辑","album_id":"544468967","pic_small":"http://qukufile2.qianqian.com/data2/pic/78ff817ec469c5f2d20ae389ea5f2b88/544468967/544468967.jpg@s_1,w_90,h_90","hot":0,"author":"DJ阿奇","artist_id":"544466456"},{"resource_type_ext":"0","all_artist_id":"43888407","publishtime":"2015-01-18","company":"代亚（上海）文化","album_desc":"","title":"再次寻找周杰伦","album_id":"243077173","pic_small":"http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90","hot":6403,"author":"刘瑞琦","artist_id":"43888407"},{"resource_type_ext":"0","all_artist_id":"313607","publishtime":"2009-11-27","company":"SONY MUSIC","album_desc":"","title":"心情出口","album_id":"15702037","pic_small":"http://qukufile2.qianqian.com/data2/pic/127c0e504c43b824874e7bf508e41d04/15702037/15702037.jpg@s_1,w_90,h_90","hot":206,"author":"群星","artist_id":"313607"},{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2005-08-01","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"Initial J (日本版)","album_id":"5684947","pic_small":"http://qukufile2.qianqian.com/data2/music/95590D9EEF216AFD368C03F706EC4BA5/252461857/252461857.jpg@s_0,w_90","hot":28,"author":"周杰伦","artist_id":"29"},{"resource_type_ext":"0","all_artist_id":"29","publishtime":"2005-01-21","company":"杰威尔JVR音乐有限公司","album_desc":"","title":"周杰伦 2004 无与伦比 演唱会 Live CD","album_id":"839081","pic_small":"http://qukufile2.qianqian.com/data2/pic/af350b3b4a65e34966479664514ad182/274046645/274046645.jpg@s_0,w_90","hot":26,"author":"周杰伦","artist_id":"29"}]
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
             * song_list : [{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"代亚（上海）文化传媒中心","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"再次寻找周杰伦","content":"","piao_id":"0","artist_id":"43888407","lrclink":"","data_source":0,"relate_status":0,"learn":0,"album_id":"243077173","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/music/1564537AF0AB27CCEC67094048E75478/255495348/255495348.jpg@s_0,w_90","song_id":"243120061","all_artist_id":"43888407","ting_uid":"60867779"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73330338","lrclink":"http://qukufile2.qianqian.com/data2/lrc/246654319/246654319.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"3","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"刘瑞琦","pic_small":"http://qukufile2.qianqian.com/data2/pic/3f9cc14180c9ac76d946dd6f46a98e1d/540045891/540045891.jpg@s_0,w_90","song_id":"73987452","all_artist_id":"73330338","ting_uid":"110935778"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73370032","lrclink":"http://qukufile2.qianqian.com/data2/lrc/fbcddbcef2e50470877eeac6982d99ba/262969501/262969501.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"袁雨桐","pic_small":"","song_id":"74119040","all_artist_id":"73370032","ting_uid":"110975472"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73366695","lrclink":"","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"娘子","pic_small":"","song_id":"74102722","all_artist_id":"73366695","ting_uid":"110972135"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":0,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73356324","lrclink":"http://qukufile2.qianqian.com/data2/lrc/238449176/238449176.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128","title":"七里香","has_mv_mobile":0,"author":"五线音伤","pic_small":"","song_id":"74002738","all_artist_id":"73356324","ting_uid":"110961764"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73359372","lrclink":"","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"云游侠何然","pic_small":"","song_id":"74071295","all_artist_id":"73359372","ting_uid":"110964812"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司索尼音乐","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"心情出口","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/0ad5c4b3166778683154405894c4ff60/271931997/271931997.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"15702037","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"64,128,256,320","title":"七里香","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/127c0e504c43b824874e7bf508e41d04/15702037/15702037.jpg@s_1,w_90,h_90","song_id":"271931995","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"1100000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889320","info":"专辑同名歌曲《七里香》其实暗示了专辑返璞归真的基调，该歌灵感来自席慕蓉的诗，伤感的歌词更像是回望儿时课桌上刻下的斑驳字迹。","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/70682806/70682806.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":93119037,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"七里香","has_mv_mobile":1,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"274085","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889312","info":"","has_mv":0,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13851727/13851727.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"外婆","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"260390","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0100000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889304","info":"《搁浅》：周杰伦《七里香》中的曲目6，发行于2004，由周杰伦作曲，宋健彰作词，钟兴民编曲。","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13871416/13871416.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"搁浅","has_mv_mobile":1,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"3451498","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13907185/13907185.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"借口","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"218579","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889288","info":"","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13897594/13897594.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"园游会","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"3451500","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889292","info":"广告《动感地带》主题曲","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13859215/13859215.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"我的地盘","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"271627","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889300","info":"","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13895071/13895071.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"乱舞春秋","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"3451496","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889283","info":"","has_mv":1,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13881596/13881596.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"止战之殇","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"290466","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889308","info":"","has_mv":0,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/4ab01c20c848940807e92631bb1f9162/258337604/258337604.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"64,128,192,256,320,flac","title":"将军","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"230819","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"","toneid":"600902000006889296","info":"","has_mv":0,"album_title":"七里香","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/13913849/13913849.lrc","data_source":0,"relate_status":0,"learn":1,"album_id":"67909","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"困兽之斗","has_mv_mobile":0,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/4dc725cd39963fa3f05a9cf38439b554/551080154/551080154.jpg@s_1,w_90,h_90","song_id":"272420","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"1100000000","del_status":"0","havehigh":2,"si_proxycompany":"深圳腾讯计算机系统有限公司杰威尔","versions":"现场","toneid":"600902000006889320","info":"专辑同名歌曲《七里香》其实暗示了专辑返璞归真的基调，该歌灵感来自席慕蓉的诗，伤感的歌词更像是回望儿时课桌上刻下的斑驳字迹。","has_mv":1,"album_title":"周杰伦 2004 无与伦比 演唱会 Live CD","content":"","piao_id":"0","artist_id":"29","lrclink":"http://qukufile2.qianqian.com/data2/lrc/65040435/65040435.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"839081","biaoshi":"vip,lossless","bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","song_source":"web","is_first_publish":0,"cluster_id":93119011,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"24,64,128,192,256,320,flac","title":"七里香","has_mv_mobile":1,"author":"周杰伦","pic_small":"http://qukufile2.qianqian.com/data2/pic/af350b3b4a65e34966479664514ad182/274046645/274046645.jpg@s_0,w_90","song_id":"1116382","all_artist_id":"29","ting_uid":"7994"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"北京顺音文化传媒有限公司","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"七里香-中文舞曲专辑","content":"","piao_id":"0","artist_id":"544466456","lrclink":"http://qukufile2.qianqian.com/data2/lrc/7145c74f01188298880ee44d3450e984/544468948/544468948.lrc","data_source":0,"relate_status":0,"learn":0,"album_id":"544468967","biaoshi":"lossless","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"0","korean_bb_song":"0","all_rate":"64,128,256,320,flac","title":"七里香","has_mv_mobile":0,"author":"DJ阿奇","pic_small":"http://qukufile2.qianqian.com/data2/pic/78ff817ec469c5f2d20ae389ea5f2b88/544468967/544468967.jpg@s_1,w_90,h_90","song_id":"544468969","all_artist_id":"544466456","ting_uid":"340197410"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":2,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73371447","lrclink":"http://qukufile2.qianqian.com/data2/lrc/246605656/246605656.lrc","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"24,64,128,192,256,320","title":"七里香日文版 - 初夏物语","has_mv_mobile":0,"author":"荒木毬菜","pic_small":"","song_id":"74066696","all_artist_id":"73371447","ting_uid":"110976887"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":0,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73341074","lrclink":"","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"1","korean_bb_song":"0","all_rate":"64,128","title":"七里香 苏荷版","has_mv_mobile":0,"author":"贾小曼","pic_small":"","song_id":"74115216","all_artist_id":"73341074","ting_uid":"110946514"},{"resource_type_ext":"0","has_filmtv":"0","resource_type":0,"mv_provider":"0000000000","del_status":"0","havehigh":0,"si_proxycompany":"disanfang","versions":"","toneid":"0","info":"","has_mv":0,"album_title":"","content":"","piao_id":"0","artist_id":"73343781","lrclink":"","data_source":0,"relate_status":1,"learn":0,"album_id":"0","biaoshi":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"yyr","is_first_publish":0,"cluster_id":0,"charge":0,"copy_type":"3","korean_bb_song":"0","all_rate":"24,64,128","title":"【YY官录】七里香 溪寂 16072803_20141001222658","has_mv_mobile":0,"author":"芙蓉帐暖夜笙歌","pic_small":"http://qukufile2.qianqian.com/data2/pic/fa61840eeb1dabfa4a9bcd995715d6a3/552105109/552105109.jpg@s_0,w_90","song_id":"73954466","all_artist_id":"73343781","ting_uid":"110949221"}]
             * total : 22
             */

            public int total;
            public List<SongListBean> song_list;


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
             * artist_list : [{"ting_uid":"60867779","song_num":121,"country":"中国","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/0a127ca37dc68eb9d7ab827029325d41/541302419/541302419.jpg@s_0,w_120","album_num":17,"artist_desc":"","author":"刘瑞琦","artist_source":"web","artist_id":"43888407"},{"ting_uid":"7994","song_num":371,"country":"台湾","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/046d17bfa056e736d873ec4f891e338f/540336142/540336142.jpg@s_0,w_120","album_num":31,"artist_desc":"","author":"周杰伦","artist_source":"web","artist_id":"29"},{"ting_uid":"340197410","song_num":18,"country":"中国","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/23372f2991e404fbb9d25b78d41c2af7/544466454/544466454.JPG@s_0,w_120","album_num":6,"artist_desc":"","author":"DJ阿奇","artist_source":"web","artist_id":"544466456"},{"ting_uid":"110972135","song_num":11,"country":"内地","avatar_middle":"http://qukufile2.qianqian.com/data2/pic/780a5e06fe0a847a7b7b1d952fdb0993/271184265/271184265.jpg@s_0,w_120","album_num":0,"artist_desc":"","author":"娘子","artist_source":"yyr","artist_id":"73366695"},{"ting_uid":"110964812","song_num":222,"country":"内地","avatar_middle":"","album_num":0,"artist_desc":"","author":"云游侠何然","artist_source":"yyr","artist_id":"73359372"}]
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
