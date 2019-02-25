package com.lulu.curl.okhttpdemo.luokhttp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public class HttpTask<T> implements Runnable, Delayed {

    private IHttpRequest mHttpRequest;

    private static final String TAG = "HttpTask";

    public HttpTask(T requestData, String url, IHttpRequest httpRequest, CallbackListener callbackListener) {
        mHttpRequest = httpRequest;
        httpRequest.setUrl(url);
        httpRequest.setListener(callbackListener);
        Log.d(TAG, "HttpTask: url: " + url);
        String content = new Gson().toJson(requestData);
        try {
            httpRequest.setData(content.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // implements Runnable
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void run() {
        try {
            mHttpRequest.execute();
        } catch (Exception e) {
            //将失败的任务的添加到重试队列中
            ThreadPoolManager.getInstance().addDelayTask(this);
        }
    }

    private long delayTime;//延迟时间
    private int retryCount;//重试次数

    public long getDelayTime() {
        return delayTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime + System.currentTimeMillis();
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    ///////////////////////////////////////////////////////////////////////////
    // implements Delayed
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public long getDelay(@NonNull TimeUnit unit) {
        Log.d(TAG, "getDelay: this.delayTime - System.currentTimeMillis(): " + (this.delayTime - System.currentTimeMillis()));
        return unit.convert(this.delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NonNull Delayed o) {
        return 0;
    }
}
