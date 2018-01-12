package soft.me.ldc.http;




import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import soft.me.ldc.http.accsess.HttpAccsess;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/8/15.
 */

public class okhttp3MultipartRequst implements HttpAccsess {
    private static okhttp3MultipartRequst instance = null;
    private OkHttpClient okclient = null;

    //单实例 线程安全
    public static okhttp3MultipartRequst newInstance() {
        synchronized (okhttp3MultipartRequst.class) {
            if (instance == null)
                instance = new okhttp3MultipartRequst();
        }
        return instance;
    }

    private okhttp3MultipartRequst() {
        InitOkClient();
    }

    /**
     * get method
     *
     * @param param
     * @throws Exception
     */
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


    /**
     * post method
     *
     * @param param
     * @throws Exception
     */
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
        MultipartBody.Builder paramsBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                paramsBuilder.addFormDataPart(p.getKey(), String.valueOf(p.getValue()));
            }
        }
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
                File file = new File((String) f.getValue());
                paramsBuilder.addFormDataPart(f.getKey(), file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }

        requestbody = paramsBuilder.build();
        //构建请求体
        final Request request = new Request.Builder().url(param.url.trim()).post(requestbody).build();
        //请求
        return okclient.newCall(request).execute();

    }

    /**
     * Put method
     *
     * @param param
     * @throws Exception
     */
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
        MultipartBody.Builder paramsBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                paramsBuilder.addFormDataPart(p.getKey(), (String) p.getValue());
            }
        }
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
                File file = new File(f.getValue());
                paramsBuilder.addFormDataPart(f.getKey(), file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }

        requestbody = paramsBuilder.build();
        //构建请求体
        final Request request = new Request.Builder().url(param.url.trim()).put(requestbody).build();
        //请求
        return okclient.newCall(request).execute();

    }

    /**
     * delete method
     *
     * @param param
     * @throws Exception
     */
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
        MultipartBody.Builder paramsBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                paramsBuilder.addFormDataPart(p.getKey(), (String) p.getValue());
            }
        }
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
                File file = new File(f.getValue());
                paramsBuilder.addFormDataPart(f.getKey(), file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }

        requestbody = paramsBuilder.build();
        //构建请求体
        final Request request = new Request.Builder().url(param.url.trim()).delete(requestbody).build();
        //请求
        return okclient.newCall(request).execute();

    }


    /**
     * pathch method
     *
     * @param param
     * @throws Exception
     */
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
        MultipartBody.Builder paramsBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //添加参数
        if (param.parems != null && param.parems.size() > 0) {
            for (HashMap.Entry<String, Object> p : param.parems.entrySet()) {
                paramsBuilder.addFormDataPart(p.getKey(), (String) p.getValue());
            }
        }
        if (param.files != null && param.files.size() > 0) {
            for (HashMap.Entry<String, String> f : param.files.entrySet()) {
                //二进制文件
                File file = new File(f.getValue());
                paramsBuilder.addFormDataPart(f.getKey(), file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }

        requestbody = paramsBuilder.build();
        //构建请求体
        final Request request = new Request.Builder().url(param.url.trim()).patch(requestbody).build();
        //请求
        return okclient.newCall(request).execute();

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
}
