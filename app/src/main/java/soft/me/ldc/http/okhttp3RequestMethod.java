package soft.me.ldc.http;


import okhttp3.Response;
import soft.me.ldc.http.accsess.MethodAccsess;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/12/12.
 */

public class okhttp3RequestMethod implements MethodAccsess {
    private static okhttp3RequestMethod instance = null;

    public static okhttp3RequestMethod getInstance() {
        synchronized (okhttp3RequestMethod.class) {
            if (instance == null)
                instance = new okhttp3RequestMethod();
        }
        return instance;
    }

    @Override
    public Response RequestMethod(HttpParam param) throws Exception {
        Response response = null;
        if (param == null)
            return null;
        // TODO: 2017/12/12 参数文件提交
        if (param.submitType == HttpParam.submitType_Multipart) {
            if (param.method.trim().equals("0")) {
                response = okhttp3MultipartRequst.newInstance().MethodGet(param);
            }
            if (param.method.trim().equals("1")) {
                response = okhttp3MultipartRequst.newInstance().MethodPost(param);
            }
            if (param.method.trim().equals("2")) {
                response = okhttp3MultipartRequst.newInstance().MethodPut(param);
            }
            if (param.method.trim().equals("3")) {
                response = okhttp3MultipartRequst.newInstance().MethodDelete(param);
            }
            if (param.method.trim().equals("4")) {
                response = okhttp3MultipartRequst.newInstance().MethodPatch(param);
            }
        }
        // TODO: 2017/12/12 提交json数据
        else if (param.submitType == HttpParam.submitType_Json) {
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
        // TODO: 2017/12/12 提交参数
        else if (param.submitType == HttpParam.submitType_Default) {
            if (param.method.trim().equals("0")) {
                response = okhttp3DefaultRequest.newInstance().MethodGet(param);
            }
            if (param.method.trim().equals("1")) {
                response = okhttp3DefaultRequest.newInstance().MethodPost(param);
            }
            if (param.method.trim().equals("2")) {
                response = okhttp3DefaultRequest.newInstance().MethodPut(param);
            }
            if (param.method.trim().equals("3")) {
                response = okhttp3DefaultRequest.newInstance().MethodDelete(param);
            }
            if (param.method.trim().equals("4")) {
                response = okhttp3DefaultRequest.newInstance().MethodPatch(param);
            }
        }

        if (response == null)
            return null;
        return response;
    }


}
