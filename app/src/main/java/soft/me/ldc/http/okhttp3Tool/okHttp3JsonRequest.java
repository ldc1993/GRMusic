package soft.me.ldc.http.okhttp3Tool;


import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import soft.me.ldc.http.okhttp3Tool.accsess.okHttpJsonAccess;
import soft.me.ldc.http.okhttp3Tool.param.okHttpJsonParam;

/**
 * Created by LDC on 2017/12/12.
 */

public class okHttp3JsonRequest implements okHttpJsonAccess {

    private static okHttp3JsonRequest instance = null;
    private OkHttpClient okclient = null;

    //单实例 线程安全
    public static okHttp3JsonRequest newInstance() {
        synchronized (okHttp3FormBody.class) {
            if (instance == null)
                instance = new okHttp3JsonRequest();
        }
        return instance;
    }

    private okHttp3JsonRequest() {
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
    public Response MethodPost(okHttpJsonParam param) throws Exception {
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
    public Response MethodGet(okHttpJsonParam param) throws Exception {
        //get 不做json 提交
        return null;
    }

    @Override
    public Response MethodPut(okHttpJsonParam param) throws Exception {
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
    public Response MethodDelete(okHttpJsonParam param) throws Exception {
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
    public Response MethodPatch(okHttpJsonParam param) throws Exception {
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
