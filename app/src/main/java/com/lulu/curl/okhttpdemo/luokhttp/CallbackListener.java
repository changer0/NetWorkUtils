package com.lulu.curl.okhttpdemo.luokhttp;

import java.io.InputStream;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public interface CallbackListener {

    /**
     * 成功回调
     * @param inputStream
     */
    void onSuccess(InputStream inputStream);

    /**
     * 失败
     */
    void onFailed();
}
