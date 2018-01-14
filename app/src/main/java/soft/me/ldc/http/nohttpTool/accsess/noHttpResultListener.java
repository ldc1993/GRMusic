package soft.me.ldc.http.nohttpTool.accsess;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by ldc45 on 2018/1/14.
 */

public abstract class noHttpResultListener<T> implements OnResponseListener<T> {
    @Override
    public void onStart(int what) {

    }

    @Override
    public void onSucceed(int what, Response<T> response) {

    }

    @Override
    public void onFailed(int what, Response<T> response) {

    }

    @Override
    public void onFinish(int what) {

    }
}
