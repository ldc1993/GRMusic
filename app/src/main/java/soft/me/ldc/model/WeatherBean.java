package soft.me.ldc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudi on 2018/2/13.
 */

public class WeatherBean {
    /**
     * status : 1
     * count : 1
     * info : OK
     * infocode : 10000
     * lives : [{"province":"北京","city":"东城区","adcode":"110101","weather":"晴","temperature":"4","winddirection":"北","windpower":"4","humidity":"17","reporttime":"2018-02-13 19:00:00"}]
     */

    public String status;
    public String count;
    public String info;
    public String infocode;
    public List<LivesBean> lives = new ArrayList<>();


    public static class LivesBean implements Serializable {
        final static long serialVersionUID = 1l;
        /**
         * province : 北京
         * city : 东城区
         * adcode : 110101
         * weather : 晴
         * temperature : 4
         * winddirection : 北
         * windpower : 4
         * humidity : 17
         * reporttime : 2018-02-13 19:00:00
         */

        public String province;
        public String city;
        public String adcode;
        public String weather;
        public String temperature;
        public String winddirection;
        public String windpower;
        public String humidity;
        public String reporttime;
    }
}
