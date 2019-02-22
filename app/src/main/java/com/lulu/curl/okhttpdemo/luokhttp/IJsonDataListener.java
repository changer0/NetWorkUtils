package com.lulu.curl.okhttpdemo.luokhttp;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public interface IJsonDataListener<T> {
    void onSuccess(T clazz);
    void onFailed();
}
