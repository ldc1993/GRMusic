package soft.me.ldc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudi on 2018/2/16.
 */

public class SongListBean implements Serializable {
    final static long serialVersionUID = 1l;
    public String songnums;
    public int havemore;
    public int error_code;
    public List<SonglistBean> songlist = new ArrayList<>();

    public static class SonglistBean implements Serializable {
        final static long serialVersionUID = 1l;
        /**
         * artist_id : 29
         * all_artist_ting_uid : 7994
         * all_artist_id : 29
         * language : 国语
         * publishtime : 2016-06-24
         * album_no : 8
         * versions :
         * pic_big : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_150,h_150
         * pic_small : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_90,h_90
         * country : 港台
         * area : 1
         * lrclink : http://qukufile2.qianqian.com/data2/lrc/5736dcf00298fb7ea4ab2c58cd97483b/540999677/540999677.lrc
         * hot : 920
         * file_duration : 215
         * del_status : 0
         * resource_type : 0
         * resource_type_ext : 0
         * copy_type : 0
         * relate_status : 0
         * all_rate : 64,128,256,320
         * has_mv_mobile : 1
         * toneid : 0
         * bitrate_fee : {"0":"129|-1","1":"-1|-1"}
         * biaoshi : lossless,vip
         * info :
         * has_filmtv : 0
         * si_proxycompany : 深圳腾讯计算机系统有限公司杰威尔
         * song_id : 266322598
         * title : 告白气球
         * ting_uid : 7994
         * author : 周杰伦
         * album_id : 266322553
         * album_title : 周杰伦的床边故事
         * is_first_publish : 0
         * havehigh : 2
         * charge : 0
         * has_mv : 1
         * learn : 1
         * song_source : web
         * piao_id : 0
         * korean_bb_song : 0
         * mv_provider : 0000000000
         * listen_total : 138
         * pic_radio : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_300,h_300
         * pic_s500 : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_500,h_500
         * pic_premium : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_500,h_500
         * pic_huge : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_1000,h_1000
         * album_500_500 : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_500,h_500
         * album_800_800 :
         * album_1000_1000 : http://qukufile2.qianqian.com/data2/pic/98c6dc2454b0b891951652bb29c16cf2/266322553/266322553.jpg@s_1,w_1000,h_1000
         */

        public String artist_id;
        public String all_artist_ting_uid;
        public String all_artist_id;
        public String language;
        public String publishtime;
        public String album_no;
        public String versions;
        public String pic_big;
        public String pic_small;
        public String country;
        public String area;
        public String lrclink;
        public String hot;
        public String file_duration;
        public String del_status;
        public String resource_type;
        public String resource_type_ext;
        public String copy_type;
        public String relate_status;
        public String all_rate;
        public int has_mv_mobile;
        public String toneid;
        public String bitrate_fee;
        public String biaoshi;
        public String info;
        public String has_filmtv;
        public String si_proxycompany;
        public String song_id;
        public String title;
        public String ting_uid;
        public String author;
        public String album_id;
        public String album_title;
        public int is_first_publish;
        public int havehigh;
        public int charge;
        public int has_mv;
        public int learn;
        public String song_source;
        public String piao_id;
        public String korean_bb_song;
        public String mv_provider;
        public String listen_total;
        public String pic_radio;
        public String pic_s500;
        public String pic_premium;
        public String pic_huge;
        public String album_500_500;
        public String album_800_800;
        public String album_1000_1000;
    }
}
