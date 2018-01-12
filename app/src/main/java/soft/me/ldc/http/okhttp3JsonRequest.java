package soft.me.ldc.http;


import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import soft.me.ldc.http.accsess.HttpAccsess;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/12/12.
 */

public class okhttp3JsonRequest implements HttpAccsess {

    private static okhttp3JsonRequest instance = null;
    private OkHttpClient okclient = null;

    //单实例 线程安全
    public static okhttp3JsonRequest newInstance() {
        synchronized (okhttp3DefaultRequest.class) {
            if (instance == null)
                instance = new okhttp3JsonRequest();
        }
        return instance;
    }

    private okhttp3JsonRequest() {
        InitOkClient();
    }

    /**
     * 初始化客户端
     */
    private void InitOkClient() {
        if (okclient == null)
            okclient = new OkHttpClient.Builder()
                    .connectTimeout(30 * 1000, TimeUnit.SECONDS)
                    .readTimeout(30 * 1000, TimeUnit.SECONDS)
                    .writeTimeout(40 * 1000, TimeUnit.SECONDS)
                    .build();//初始化客户端

    }

    @Override
    public Response MethodPost(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //表单请求
        RequestBody requestbody = null;
        //请求
        Request request = null;

        requestbody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.json);

        request = new Request.Builder().url(param.url).post(requestbody).build();

        return okclient.newCall(request).execute();
    }

    @Override
    public Response MethodGet(HttpParam param) throws Exception {

        return null;
    }

    @Override
    public Response MethodPut(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //表单请求
        RequestBody requestbody = null;
        //请求
        Request request = null;

        requestbody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.json);

        request = new Request.Builder().url(param.url).put(requestbody).build();

        return okclient.newCall(request).execute();
    }

    @Override
    public Response MethodDelete(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //表单请求
        RequestBody requestbody = null;
        //请求
        Request request = null;

        requestbody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.json);

        request = new Request.Builder().url(param.url).delete(requestbody).build();

        return okclient.newCall(request).execute();
    }

    @Override
    public Response MethodPatch(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //表单请求
        RequestBody requestbody = null;
        //请求
        Request request = null;

        requestbody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param.json);

        request = new Request.Builder().url(param.url).patch(requestbody).build();

        return okclient.newCall(request).execute();
    }
}
