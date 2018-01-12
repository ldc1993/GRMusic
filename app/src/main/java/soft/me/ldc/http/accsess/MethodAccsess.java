package soft.me.ldc.http.accsess;



import okhttp3.Response;
import soft.me.ldc.http.param.HttpParam;

/**
 * Created by LDC on 2017/12/12.
 */

public interface MethodAccsess {
    Response RequestMethod(HttpParam param) throws Exception;
}
