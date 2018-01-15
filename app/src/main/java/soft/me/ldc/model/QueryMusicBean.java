package soft.me.ldc.model;

import java.util.List;

/**
 * Created by liudi on 2018/1/15.
 */

public class QueryMusicBean {

    /**
     * query : QQ
     * is_artist : 1
     * is_album : 0
     * rs_words :
     * pages : {"total":"90","rn_num":"8"}
     * song_list : [{"title":"<em>QQ<\/em>","song_id":"265906807","author":"文文姐姐","artist_id":"14498456","all_artist_id":"14498456","album_title":"福娃","appendix":"","album_id":"265906841","lrclink":"/data2/lrc/ddd9c951628298adf6d5ff63231b282a/266087486/266087486.lrc","resource_type":"0","content":"","relate_status":0,"havehigh":2,"copy_type":"1","del_status":"0","all_rate":"64,128,256,320,flac","has_mv":0,"has_mv_mobile":0,"mv_provider":"0000000000","charge":0,"toneid":"0","info":"","data_source":0,"learn":0},{"title":"Never Know the Use","song_id":"562728589","author":"<em>Qq<\/em>","artist_id":"7418579","all_artist_id":"7418579","album_title":"Hit Me With Music","appendix":"","album_id":"562728551","lrclink":"","resource_type":"0","content":"","relate_status":0,"havehigh":2,"copy_type":"1","del_status":"0","all_rate":"320","has_mv":0,"has_mv_mobile":0,"mv_provider":"0000000000","charge":0,"toneid":"0","info":"","data_source":0,"learn":0}]
     * artist : {"artist_id":7418579,"ting_uid":"232635966","name":"Qq","country":"","albums_total":"0","songs_total":"4","avatar":{"small":"","big":""}}
     */

    public String query;
    public int is_artist;
    public int is_album;
    public String rs_words;
    public PagesBean pages;
    public ArtistBean artist;
    public List<SongListBean> song_list;

    public static class PagesBean {
        /**
         * total : 90
         * rn_num : 8
         */

        public String total;
        public String rn_num;
    }

    public static class ArtistBean {
        /**
         * artist_id : 7418579
         * ting_uid : 232635966
         * name : Qq
         * country :
         * albums_total : 0
         * songs_total : 4
         * avatar : {"small":"","big":""}
         */

        public int artist_id;
        public String ting_uid;
        public String name;
        public String country;
        public String albums_total;
        public String songs_total;
        public AvatarBean avatar;

        public static class AvatarBean {
            /**
             * small :
             * big :
             */

            public String small;
            public String big;
        }
    }

    public static class SongListBean {
        /**
         * title : <em>QQ</em>
         * song_id : 265906807
         * author : 文文姐姐
         * artist_id : 14498456
         * all_artist_id : 14498456
         * album_title : 福娃
         * appendix :
         * album_id : 265906841
         * lrclink : /data2/lrc/ddd9c951628298adf6d5ff63231b282a/266087486/266087486.lrc
         * resource_type : 0
         * content :
         * relate_status : 0
         * havehigh : 2
         * copy_type : 1
         * del_status : 0
         * all_rate : 64,128,256,320,flac
         * has_mv : 0
         * has_mv_mobile : 0
         * mv_provider : 0000000000
         * charge : 0
         * toneid : 0
         * info :
         * data_source : 0
         * learn : 0
         */

        public String title;
        public String song_id;
        public String author;
        public String artist_id;
        public String all_artist_id;
        public String album_title;
        public String appendix;
        public String album_id;
        public String lrclink;
        public String resource_type;
        public String content;
        public int relate_status;
        public int havehigh;
        public String copy_type;
        public String del_status;
        public String all_rate;
        public int has_mv;
        public int has_mv_mobile;
        public String mv_provider;
        public int charge;
        public String toneid;
        public String info;
        public int data_source;
        public int learn;
    }
}
