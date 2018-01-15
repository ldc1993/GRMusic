package soft.me.ldc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldc45 on 2018/1/15.
 */

public class RadioStationSongBean implements Serializable {
    static long serialVersionUID = 1L;

    public int error_code;
    public ResultBean result;


    public static class ResultBean implements Serializable {
        static long serialVersionUID = 1L;

        public String channel;
        public Object channelid;
        public String ch_name;
        public Object artistid;
        public Object avatar;
        public Object count;
        public List<SonglistBean> songlist;


        public static class SonglistBean implements Serializable {
            static long serialVersionUID = 1L;

            public String songid;
            public String title;
            public String artist;
            public String thumb;
            public int method;
            public int flow;
            public String artist_id;
            public String all_artist_id;
            public String resource_type;
            public int havehigh;
            public int charge;
            public String all_rate;
        }
    }
}
