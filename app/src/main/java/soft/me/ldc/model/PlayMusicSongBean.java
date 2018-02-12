package soft.me.ldc.model;

import java.io.Serializable;

/**
 * Created by ldc45 on 2018/1/17.
 */

public class PlayMusicSongBean implements Serializable {
    static final long serialVersionUID = 1l;

    /**
     * songinfo : {"special_type":0,"pic_huge":"","ting_uid":"1100","pic_premium":"http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_500","havehigh":2,"si_proxycompany":"正东音乐娱乐咨询（北京）有限公司","author":"Beyond","toneid":"600902000004240302","has_mv":1,"song_id":"877578","title":"海阔天空","artist_id":"130","lrclink":"http://qukufile2.qianqian.com/data2/lrc/238975978/238975978.lrc","relate_status":"1","learn":1,"pic_big":"http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_150","play_type":0,"album_id":"197864","pic_radio":"http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_300","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_source":"web","all_artist_id":"130","all_artist_ting_uid":"1100","piao_id":"0","charge":0,"copy_type":"0","all_rate":"64,128,192,256,320,flac","korean_bb_song":"0","is_first_publish":0,"has_mv_mobile":0,"album_title":"海阔天空","pic_small":"http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_90","album_no":"1","resource_type_ext":"0","resource_type":"0"}
     * error_code : 22000
     * bitrate : {"show_link":"http://zhangmenshiting.qianqian.com/data2/music/42783748/42783748.mp3?xcode=805eed9cf626686d4394f800f7f2550e","free":1,"song_file_id":42783748,"file_size":2679447,"file_extension":"mp3","file_duration":322,"file_bitrate":64,"file_link":"http://zhangmenshiting.qianqian.com/data2/music/42783748/42783748.mp3?xcode=805eed9cf626686d4394f800f7f2550e","hash":"74d926076dc8f2f857ffaa403d79935a65f80dec"}
     */

    public SonginfoBean songinfo =new SonginfoBean();
    public int error_code;
    public BitrateBean bitrate=new BitrateBean();

   

    public static class SonginfoBean implements Serializable {
        static final long serialVersionUID = 1l;
        /**
         * special_type : 0
         * pic_huge : 
         * ting_uid : 1100
         * pic_premium : http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_500
         * havehigh : 2
         * si_proxycompany : 正东音乐娱乐咨询（北京）有限公司
         * author : Beyond
         * toneid : 600902000004240302
         * has_mv : 1
         * song_id : 877578
         * title : 海阔天空
         * artist_id : 130
         * lrclink : http://qukufile2.qianqian.com/data2/lrc/238975978/238975978.lrc
         * relate_status : 1
         * learn : 1
         * pic_big : http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_150
         * play_type : 0
         * album_id : 197864
         * pic_radio : http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_300
         * bitrate_fee : {"0":"0|0","1":"0|0"}
         * song_source : web
         * all_artist_id : 130
         * all_artist_ting_uid : 1100
         * piao_id : 0
         * charge : 0
         * copy_type : 0
         * all_rate : 64,128,192,256,320,flac
         * korean_bb_song : 0
         * is_first_publish : 0
         * has_mv_mobile : 0
         * album_title : 海阔天空
         * pic_small : http://qukufile2.qianqian.com/data2/pic/88582702/88582702.jpg@s_0,w_90
         * album_no : 1
         * resource_type_ext : 0
         * resource_type : 0
         */

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
