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
        Request<String> jorequest = null;
        try {
            if (jorequest == null)
                jorequest = NoHttp.createStringRequest(AppConfig.ServiceUrl, RequestMethod.GET);
            jorequest.add("method", "baidu.ting.billboard.billList");
            jorequest.add("type", type);
            jorequest.add("size", size);
            jorequest.add("offset", offset);
            Response<String> response = NoHttp.startRequestSync(jorequest);
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
