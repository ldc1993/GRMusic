package soft.me.ldc.http.okhttp3Tool.accsess;

import okhttp3.Response;
import soft.me.ldc.http.okhttp3Tool.param.okHttpJsonParam;

/**
 * Created by liudi on 2018/1/12.
 */

public interface okHttpJsonAccess {
    Response MethodPost(okHttpJsonParam param) throws Exception;

    Response MethodGet(okHttpJsonParam param) throws Exception;

    Response MethodPut(okHttpJsonParam param) throws Exception;

    Response MethodDelete(okHttpJsonParam param) throws Exception;

    Response MethodPatch(okHttpJsonParam param) throws Exception;
}
