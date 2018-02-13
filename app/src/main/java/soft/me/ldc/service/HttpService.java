package soft.me.ldc.service;

import android.content.Context;
import android.util.Log;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import soft.me.ldc.config.App;
import soft.me.ldc.config.AppConfig;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class HttpService {

    private static HttpService instance = null;
    private Context ctx = null;

    public static HttpService Instance(Context ctx) {

        synchronized (HttpService.class) {
            if (instance == null) {
                instance = new HttpService(ctx);
            }

        }
        return instance;
    }

    private HttpService(Context ctx) {
        this.ctx = ctx;
    }

    String resultStr = null;

    /**
     * 音乐分类
     *
     * @param type   音乐类型
     * @param size   返回数量
     * @param offset 偏移 分页
     * @return
     */
    public String MusicList(String type, String size, String offset) {
        resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            request.add("method", "baidu.ting.billboard.billList");
            request.add("type", type);
            request.add("size", size);
            request.add("offset", offset);
            request.add("version", "2.1.0");
            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }

        } catch (Exception e) {
            resultStr = null;
            e.printStackTrace();
        }
        return resultStr;

    }


    /**
     * 歌曲搜索
     *
     * @param query     关键字
     * @param page_size 分页大小
     * @param page_on   页数
     * @return
     */
    public String QueryMusic(String query, String page_on, String page_size) {
        resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            request.add("method", "baidu.ting.search.merge");//方法
            request.add("from", "android");//平台
            request.add("version", "5.6.5.0");//版本
            request.add("format", "json");//返回格式
            request.add("query", query);//关键字
            request.add("page_no", page_on);//页数
            request.add("page_size", page_size);//分页大小
            request.add("data_source", "0");
            request.add("use_cluster", "1");
            request.add("type", "-1");

            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }

        } catch (Exception e) {
            resultStr = null;
            e.printStackTrace();
        }
        return resultStr;

    }


    /**
     * 电台列表
     *
     * @return String
     */
    public String RadioStation() {
        resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            request.add("method", "baidu.ting.radio.getCategoryList");//方法
            request.add("from", "android");//平台
            request.add("version", "2.1.0");//版本
            request.add("format", "json");//返回格式

            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }

        } catch (Exception e) {
            resultStr = null;
            e.printStackTrace();
        }
        return resultStr;

    }

    /**
     * 电台歌曲列表
     *
     * @return String
     */
    public String RadioStationSong(String channelname, String page_on, String page_size) {
        resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            request.add("method", "baidu.ting.radio.getChannelSong");//方法
            request.add("from", "android");//平台
            request.add("version", "2.1.0");//版本
            request.add("format", "json");//返回格式
            request.add("channelname", channelname);
            request.add("pn", page_on);//分页数
            request.add("rn", page_size);//分页大小

            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }

        } catch (Exception e) {
            resultStr = null;
            e.printStackTrace();
        }
        return resultStr;

    }

    /**
     * 歌曲详情,音乐播放
     *
     * @return String
     */
    public String PlayMusicSong(String songId) {
        resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            request.add("method", "baidu.ting.song.play");//方法
            request.add("songid", songId);//歌曲id
            request.add("format", "json");//返回格式

            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }

        } catch (Exception e) {
            resultStr = "";
            e.printStackTrace();
        }
        return resultStr;
    }


    /**
     * @param city
     * @return
     */
    public String Weather(String city) {
        String resultStr = "";
        Request<String> request = null;
        try {
            if (request == null)
                request = NoHttp.createStringRequest(AppConfig.WeatherUrl, RequestMethod.POST);
            request.add("key", "c705dedc6d9d2f0b2c43f3a21817568a");
            request.add("city", city + "");
            request.add("extensions", "base");
            request.add("output", "JSON");
            Response<String> response = NoHttp.startRequestSync(request);
            if (response != null && response.isSucceed()) {

                Headers headers = response.getHeaders();
                if (headers.getResponseCode() == 200) {
                    resultStr = response.get();
                }

            }
        } catch (Exception e) {
            resultStr = "";
            e.printStackTrace();
        }
        Log.e("LLL", "" + resultStr);
        return resultStr;

    }
}
