package soft.me.ldc.http.accsess;

import okhttp3.Response;
import soft.me.ldc.http.param.HttpJsonParam;

/**
 * Created by liudi on 2018/1/12.
 */

public interface HttpJsonAccess {
    Response MethodPost(HttpJsonParam param) throws Exception;

    Response MethodGet(HttpJsonParam param) throws Exception;

    Response MethodPut(HttpJsonParam param) throws Exception;

    Response MethodDelete(HttpJsonParam param) throws Exception;

    Response MethodPatch(HttpJsonParam param) throws Exception;
}
