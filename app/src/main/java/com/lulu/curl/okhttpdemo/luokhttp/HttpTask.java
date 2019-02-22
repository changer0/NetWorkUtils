package com.lulu.curl.okhttpdemo.luokhttp;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public class HttpTask<T> implements Runnable{

    private IHttpRequest iHttpRequest;

    public HttpTask(T requestData, String url, IHttpRequest httpRequest, CallbackListener callbackListener) {
        iHttpRequest = httpRequest;
        httpRequest.setQul(url);
        httpRequest.setListener(callbackListener);

        String content = new Gson().toJson(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        iHttpRequest.execute();
    }
}
