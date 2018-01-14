package soft.me.ldc.http.nohttpTool.accsess;

import com.yanzhenjie.nohttp.rest.Request;

import okhttp3.Response;
import soft.me.ldc.http.nohttpTool.param.noHttpParam;
import soft.me.ldc.http.okhttp3Tool.param.okHttpParam;

/**
 * Created by ldc45 on 2018/1/14.
 */

public interface noHttpAccsess<T> {
    Request<T> MethodGet(noHttpParam param) throws Exception;

    Request<T> MethodPost(noHttpParam param) throws Exception;

    Request<T> MethodPut(noHttpParam param) throws Exception;

    Request<T> MethodDelete(noHttpParam param) throws Exception;

    Request<T> MethodPatch(noHttpParam param) throws Exception;
}
