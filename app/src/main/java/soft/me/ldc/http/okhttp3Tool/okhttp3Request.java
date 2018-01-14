package soft.me.ldc.http.okhttp3Tool;


import okhttp3.Response;
import soft.me.ldc.http.okhttp3Tool.accsess.okHttpMethodAccsess;
import soft.me.ldc.http.okhttp3Tool.param.okHttpJsonParam;
import soft.me.ldc.http.okhttp3Tool.param.okHttpParam;

/**
 * Created by LDC on 2017/12/12.
 */

public class okhttp3Request implements okHttpMethodAccsess {
    private static okhttp3Request instance = null;

    public static okhttp3Request getInstance() {
        synchronized (okhttp3Request.class) {
            if (instance == null)
                instance = new okhttp3Request();
        }
        return instance;
    }

    @Override
    public Response Method(Object obj) throws Exception {
        Response response = null;
        if (obj == null)
            return null;

        if (obj instanceof okHttpParam) {
            okHttpParam param = (okHttpParam) obj;
            // TODO: 2017/12/12 参数文件提交
            if (param.requestType == okHttpParam.MultipartBodyType) {
                if (param.method.trim().equals("0")) {
                    response = okhttp3MultipartBody.newInstance().MethodGet(param);
                }
                if (param.method.trim().equals("1")) {
                    response = okhttp3MultipartBody.newInstance().MethodPost(param);
                }
                if (param.method.trim().equals("2")) {
                    response = okhttp3MultipartBody.newInstance().MethodPut(param);
                }
                if (param.method.trim().equals("3")) {
                    response = okhttp3MultipartBody.newInstance().MethodDelete(param);
                }
                if (param.method.trim().equals("4")) {
                    response = okhttp3MultipartBody.newInstance().MethodPatch(param);
                }
            }
            // TODO: 2017/12/12 提交参数
            else if (param.requestType == okHttpParam.FormBodyType) {
                if (param.method.trim().equals("0")) {
                    response = okhttp3FormBody.newInstance().MethodGet(param);
                }
                if (param.method.trim().equals("1")) {
                    response = okhttp3FormBody.newInstance().MethodPost(param);
                }
                if (param.method.trim().equals("2")) {
                    response = okhttp3FormBody.newInstance().MethodPut(param);
                }
                if (param.method.trim().equals("3")) {
                    response = okhttp3FormBody.newInstance().MethodDelete(param);
                }
                if (param.method.trim().equals("4")) {
                    response = okhttp3FormBody.newInstance().MethodPatch(param);
                }
            }

        }

        // TODO: 2017/12/12 提交json数据
        else if (obj instanceof okHttpJsonParam) {
            //转类型
            okHttpJsonParam param = (okHttpJsonParam) obj;
            if (param.requestType == okHttpParam.MediaType) {
                if (param.method.trim().equals("0")) {
                    response = okhttp3JsonRequest.newInstance().MethodGet(param);
                }
                if (param.method.trim().equals("1")) {
                    response = okhttp3JsonRequest.newInstance().MethodPost(param);
                }
                if (param.method.trim().equals("2")) {
                    response = okhttp3JsonRequest.newInstance().MethodPut(param);
                }
                if (param.method.trim().equals("3")) {
                    response = okhttp3JsonRequest.newInstance().MethodDelete(param);
                }
                if (param.method.trim().equals("4")) {
                    response = okhttp3JsonRequest.newInstance().MethodPatch(param);
                }
            }
        }
        if (response == null)
            return null;
        return response;
    }


}
