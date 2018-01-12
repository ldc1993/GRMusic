package soft.me.ldc.http.accsess;


import okhttp3.Response;

/**
 * Created by LDC on 2017/12/12.
 */

public interface MethodAccsess {
    Response Method(Object obj) throws Exception;
}
