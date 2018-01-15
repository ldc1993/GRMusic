package soft.me.ldc.service;

import android.content.Context;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

import soft.me.ldc.config.AppConfig;
import soft.me.ldc.http.nohttpTool.accsess.noHttpResultListener;
import soft.me.ldc.http.nohttpTool.noHttpQueue;

/**
 * Created by ldc45 on 2018/1/13.
 */

public class MusicService {

    private static MusicService instance = null;
    private Context ctx = null;

    public static MusicService Instance(Context ctx) {

        synchronized (MusicService.class) {
            if (instance == null) {
                instance = new MusicService(ctx);
            }

        }
        return instance;
    }

    private MusicService(Context ctx) {
        this.ctx = ctx;
    }

    String resultStr = null;

    /**
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

}
