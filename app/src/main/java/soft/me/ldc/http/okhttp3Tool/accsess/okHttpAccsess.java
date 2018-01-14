package soft.me.ldc.http.okhttp3Tool.accsess;




import okhttp3.Response;
import soft.me.ldc.http.okhttp3Tool.param.okHttpParam;

/**
 * Created by LDC on 2017/8/21.
 */

public interface okHttpAccsess {

    Response MethodPost(okHttpParam param) throws Exception;

    Response MethodGet(okHttpParam param) throws Exception;

    Response MethodPut(okHttpParam param) throws Exception;

    Response MethodDelete(okHttpParam param) throws Exception;

    Response MethodPatch(okHttpParam param) throws Exception;
}