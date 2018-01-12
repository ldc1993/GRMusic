package soft.me.ldc.http;


import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import soft.me.ldc.http.accsess.HttpAccsess;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/12/12.
 */

public class okhttp3FormBody implements HttpAccsess {
    private static okhttp3FormBody instance = null;
    private OkHttpClient okclient = null;

    //单实例 线程安全
    public static okhttp3FormBody newInstance() {
        synchronized (okhttp3FormBody.class) {
            if (instance == null)
                instance = new okhttp3FormBody();
        }
        return instance;
    }

    private okhttp3FormBody() {
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
        //请求体
        RequestBody requestBody = null;
        //请求
        Request request = null;
        FormBody.Builder formBody = new FormBody.Builder();
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                formBody.add(p.getKey(), (String) p.getValue());
            }
        }
        //添加文件
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
            }
        }
        //请求体
        requestBody = formBody.build();
        //构建请求体
        request = new Request.Builder().url(param.url).post(requestBody).build();
        //请求
        return okclient.newCall(request).execute();
    }

    @Override
    public Response MethodGet(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //拼接参数
        StringBuffer tempparam = new StringBuffer();
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                //参数拼接
                tempparam.append("&");
                tempparam.append(String.format("%s=%s", p.getKey(), URLEncoder.encode((String) p.getValue(), "utf-8")));
            }
        }
        //添加文件
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
            }
        }
        //补全请求地址
        String requesturl = String.format("%s?%s", param.url, tempparam.toString());
        //构建请求体
        final Request request = new Request.Builder().url(requesturl.trim()).get().build();
        //请求
        return okclient.newCall(request).execute();
    }

    @Override
    public Response MethodPut(HttpParam param) throws Exception {
        if (okclient == null)
            InitOkClient();
        if (param == null)
            return null;
        if (param.url == null || param.url.equals("") || param.url.trim().length() == 0)
            return null;
        //请求体
        RequestBody requestBody = null;
        //请求
        Request request = null;
        FormBody.Builder formBody = new FormBody.Builder();
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                formBody.add(p.getKey(), (String) p.getValue());
            }
        }
        //添加文件
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
            }
        }
        //请求体
        requestBody = formBody.build();
        //构建请求体
        request = new Request.Builder().url(param.url).put(requestBody).build();
        //请求
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
        //请求体
        RequestBody requestBody = null;
        Request request = null;
        FormBody.Builder formBody = new FormBody.Builder();
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                formBody.add(p.getKey(), (String) p.getValue());
            }
        }
        //添加文件
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
            }
        }
        //请求体
        requestBody = formBody.build();
        //构建请求体
        request = new Request.Builder().url(param.url).delete(requestBody).build();
        //请求
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
        //请求体
        RequestBody requestBody = null;
        //请求
        Request request = null;
        FormBody.Builder formBody = new FormBody.Builder();
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                formBody.add(p.getKey(), (String) p.getValue());
            }
        }
        //添加文件
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
            }
        }
        //请求体
        requestBody = formBody.build();
        //构建请求体
        request = new Request.Builder().url(param.url).patch(requestBody).build();
        //请求
        return okclient.newCall(request).execute();
    }
}
