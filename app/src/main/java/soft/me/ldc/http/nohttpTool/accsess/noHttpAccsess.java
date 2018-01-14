package soft.me.ldc.http.nohttpTool.accsess;

import com.yanzhenjie.nohttp.rest.Request;

import okhttp3.Response;
import soft.me.ldc.http.nohttpTool.param.noHttpParam;
import soft.me.ldc.http.okhttp3Tool.param.okHttpParam;

/**
 * Created by ldc45 on 2018/1/14.
 */

public interface noHttpAccsess {
    Request MethodGet(noHttpParam param) throws Exception;

    Request MethodPost(noHttpParam param) throws Exception;

    Request MethodPut(noHttpParam param) throws Exception;

    Request MethodDelete(noHttpParam param) throws Exception;

    Request MethodPatch(noHttpParam param) throws Exception;
}
