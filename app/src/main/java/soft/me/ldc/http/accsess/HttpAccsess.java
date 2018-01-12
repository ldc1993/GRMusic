package soft.me.ldc.http.accsess;




import okhttp3.Response;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/8/21.
 */

public interface HttpAccsess {

    Response MethodPost(HttpParam param) throws Exception;

    Response MethodGet(HttpParam param) throws Exception;

    Response MethodPut(HttpParam param) throws Exception;

    Response MethodDelete(HttpParam param) throws Exception;

    Response MethodPatch(HttpParam param) throws Exception;
}