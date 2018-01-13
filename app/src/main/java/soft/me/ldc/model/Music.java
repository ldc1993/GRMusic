package soft.me.ldc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class Music implements Serializable {


    /**
     * song_list : [{"artist_id":"1641","language":"国语","pic_big":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_90,h_90","country":"内地","area":"0","publishtime":"2017-12-27","album_no":"1","lrclink":"http://qukufile2.qianqian.com/data2/lrc/aac3ec9d550094dc94d8a792e74e2ad5/567270931/567270931.lrc","copy_type":"1","hot":"67941","all_artist_ting_uid":"1579","resource_type":"0","is_new":"1","rank_change":"0","rank":"1","all_artist_id":"1641","style":"","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","file_duration":260,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","biaoshi":"first,lossless","info":"","has_filmtv":"0","si_proxycompany":"华宇世博音乐文化（北京）有限公司-亚神","res_encryption_flag":"0","song_id":"567270530","title":"言不由衷","ting_uid":"1579","author":"徐佳莹","album_id":"568564580","album_title":"心里学","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"徐佳莹","pic_radio":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_300,h_300","pic_s500":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500","pic_premium":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000","album_500_500":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500","album_800_800":"","album_1000_1000":"http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000"},{"artist_id":"1665","language":"国语","pic_big":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_90,h_90","country":"内地","area":"0","publishtime":"2017-12-29","album_no":"3","lrclink":"http://qukufile2.qianqian.com/data2/lrc/9d923fe8df5c82aa38fb1992214e2c05/567626249/567626249.lrc","copy_type":"1","hot":"54377","all_artist_ting_uid":"2611","resource_type":"0","is_new":"1","rank_change":"0","rank":"2","all_artist_id":"1665","style":"","del_status":"0","relate_status":"0","toneid":"0","all_rate":"64,128,256,320,flac","file_duration":285,"has_mv_mobile":0,"versions":"","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","biaoshi":"lossless","info":"","has_filmtv":"0","si_proxycompany":"TAIHE MUSIC GROUP","res_encryption_flag":"0","song_id":"567479594","title":"如我","ting_uid":"2611","author":"刘惜君","album_id":"568664804","album_title":"如我","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","artist_name":"刘惜君","pic_radio":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_300,h_300","pic_s500":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500","pic_premium":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000","album_500_500":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_500,h_500","album_800_800":"","album_1000_1000":"http://qukufile2.qianqian.com/data2/pic/1a4c14e99a7504383c7b06b623da57e2/568695363/568695363.jpg@s_1,w_1000,h_1000"}]
     * billboard : {"billboard_type":"1","billboard_no":"2438","update_date":"2018-01-13","billboard_songnum":"121","havemore":1,"name":"新歌榜","comment":"该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次","pic_s192":"http://hiphotos.qianqian.com/ting/pic/item/9922720e0cf3d7caf39ebc10f11fbe096b63a968.jpg","pic_s640":"http://hiphotos.qianqian.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg","pic_s444":"http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg","pic_s260":"http://hiphotos.qianqian.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg","pic_s210":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg","web_url":"http://music.baidu.com/top/new"}
     * error_code : 22000
     */

    public BillboardBean billboard;
    public int error_code;
    public List<SongListBean> song_list;

    public static class BillboardBean {
        /**
         * billboard_type : 1
         * billboard_no : 2438
         * update_date : 2018-01-13
         * billboard_songnum : 121
         * havemore : 1
         * name : 新歌榜
         * comment : 该榜单是根据百度音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次
         * pic_s192 : http://hiphotos.qianqian.com/ting/pic/item/9922720e0cf3d7caf39ebc10f11fbe096b63a968.jpg
         * pic_s640 : http://hiphotos.qianqian.com/ting/pic/item/f7246b600c33874495c4d089530fd9f9d62aa0c6.jpg
         * pic_s444 : http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg
         * pic_s260 : http://hiphotos.qianqian.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg
         * pic_s210 : http://business.cdn.qianqian.com/qianqian/pic/bos_client_c49310115801d43d42a98fdc357f6057.jpg
         * web_url : http://music.baidu.com/top/new
         */

        public String billboard_type;
        public String billboard_no;
        public String update_date;
        public String billboard_songnum;
        public int havemore;
        public String name;
        public String comment;
        public String pic_s192;
        public String pic_s640;
        public String pic_s444;
        public String pic_s260;
        public String pic_s210;
        public String web_url;


    }

    public static class SongListBean {
        /**
         * artist_id : 1641
         * language : 国语
         * pic_big : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_150,h_150
         * pic_small : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_90,h_90
         * country : 内地
         * area : 0
         * publishtime : 2017-12-27
         * album_no : 1
         * lrclink : http://qukufile2.qianqian.com/data2/lrc/aac3ec9d550094dc94d8a792e74e2ad5/567270931/567270931.lrc
         * copy_type : 1
         * hot : 67941
         * all_artist_ting_uid : 1579
         * resource_type : 0
         * is_new : 1
         * rank_change : 0
         * rank : 1
         * all_artist_id : 1641
         * style :
         * del_status : 0
         * relate_status : 0
         * toneid : 0
         * all_rate : 64,128,256,320,flac
         * file_duration : 260
         * has_mv_mobile : 0
         * versions :
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * biaoshi : first,lossless
         * info :
         * has_filmtv : 0
         * si_proxycompany : 华宇世博音乐文化（北京）有限公司-亚神
         * res_encryption_flag : 0
         * song_id : 567270530
         * title : 言不由衷
         * ting_uid : 1579
         * author : 徐佳莹
         * album_id : 568564580
         * album_title : 心里学
         * is_first_publish : 0
         * havehigh : 2
         * charge : 0
         * has_mv : 1
         * learn : 0
         * song_source : web
         * piao_id : 0
         * korean_bb_song : 0
         * resource_type_ext : 0
         * mv_provider : 0000000000
         * artist_name : 徐佳莹
         * pic_radio : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_300,h_300
         * pic_s500 : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500
         * pic_premium : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500
         * pic_huge : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000
         * album_500_500 : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_500,h_500
         * album_800_800 :
         * album_1000_1000 : http://qukufile2.qianqian.com/data2/pic/1ffc1e912125f7dc708f5c17f14b8aec/568564581/568564581.jpg@s_1,w_1000,h_1000
         */

        public String artist_id;
        public String language;
        public String pic_big;
        public String pic_small;
        public String country;
        public String area;
        public String publishtime;
        public String album_no;
        public String lrclink;
        public String copy_type;
        public String hot;
        public String all_artist_ting_uid;
        public String resource_type;
        public String is_new;
        public String rank_change;
        public String rank;
        public String all_artist_id;
        public String style;
        public String del_status;
        public String relate_status;
        public String toneid;
        public String all_rate;
        public int file_duration;
        public int has_mv_mobile;
        public String versions;
        public String bitrate_fee;
        public String biaoshi;
        public String info;
        public String has_filmtv;
        public String si_proxycompany;
        public String res_encryption_flag;
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
        public String resource_type_ext;
        public String mv_provider;
        public String artist_name;
        public String pic_radio;
        public String pic_s500;
        public String pic_premium;
        public String pic_huge;
        public String album_500_500;
        public String album_800_800;
        public String album_1000_1000;

    }
}
