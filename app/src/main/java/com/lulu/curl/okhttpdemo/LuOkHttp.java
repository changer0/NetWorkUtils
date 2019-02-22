package com.lulu.curl.okhttpdemo;

import com.lulu.curl.okhttpdemo.luokhttp.HttpTask;
import com.lulu.curl.okhttpdemo.luokhttp.IHttpRequest;
import com.lulu.curl.okhttpdemo.luokhttp.IJsonDataListener;
import com.lulu.curl.okhttpdemo.luokhttp.JsonCallbackListener;
import com.lulu.curl.okhttpdemo.luokhttp.JsonHttpRequest;
import com.lulu.curl.okhttpdemo.luokhttp.ThreadPoolManager;


/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public class LuOkHttp {
    /**
     * 发送网络请求
     * @param request
     * @param url
     * @param response
     * @param listener
     * @param <T>
     * @param <M>
     */
    public static<T, M> void sendJsonRequest(T request, String url,
                                             Class<M> response, IJsonDataListener listener) {
        IHttpRequest httpRequest = new JsonHttpRequest();
        JsonCallbackListener<M> mJsonCallbackListener = new JsonCallbackListener<>(response, listener);
        HttpTask<T> httpTask = new HttpTask<>(request, url, httpRequest, mJsonCallbackListener);
        ThreadPoolManager.getInstance().addTask(httpTask);
    }
}
