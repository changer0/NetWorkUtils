package com.lulu.curl.okhttpdemo.luokhttp;

/**
 * @author zhanglulu on 2019/2/22.
 * for 封装请求接口
 */
public interface IHttpRequest {

    /**
     * 协议地址
     * @param url
     */
    void setUrl(String url);

    /**
     * 设置请求参数
     */
    void setData(byte[] bytes);

    /**
     * 数据数据回调
     * @param callbackListener
     */
    void setListener(CallbackListener callbackListener);

    /**
     * TODO
     */
    void execute();
}
