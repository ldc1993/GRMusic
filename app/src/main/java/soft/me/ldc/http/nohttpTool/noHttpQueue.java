package soft.me.ldc.http.nohttpTool;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.SimpleResponseListener;

import soft.me.ldc.http.nohttpTool.accsess.noHttpResultListener;

/**
 * Created by ldc45 on 2018/1/14.
 */

public class noHttpQueue {
    private static noHttpQueue instance = null;
    private RequestQueue requestQueue = null;

    public static noHttpQueue getInstance() {
        if (instance == null) {
            synchronized (noHttpQueue.class) {
                if (instance == null)
                    instance = new noHttpQueue();
            }
        }
        return instance;
    }

    //私有构造函数
    private noHttpQueue() {
        if (requestQueue == null)
            requestQueue = new RequestQueue(10);//线程池数 10
        requestQueue.start();
    }

    //添加请求
    public <T> void push(int what, Request<T> request, noHttpResultListener<T> listener) {
        requestQueue.add(what, request, listener);
    }

    //添加请求
    public <T> void push(int what, Request<T> request, OnResponseListener<T> listener) {
        requestQueue.add(what, request, listener);
    }

    public void cancelAll() {
        requestQueue.cancelAll();
    }

    //关闭线程池
    public void stop() {
        requestQueue.stop();
    }

}
