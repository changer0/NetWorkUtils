package com.lulu.curl.okhttpdemo.luokhttp;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhanglulu on 2019/2/22.
 * for
 */
public class JsonCallbackListener<T> implements CallbackListener {

    private Class<T> resposeClass;
    private IJsonDataListener jsonDataListener;

    private static final String TAG = "JsonCallbackListener";

    Handler handler = new Handler(Looper.getMainLooper());

    public JsonCallbackListener(Class<T> responseClass, IJsonDataListener listener) {
        this.resposeClass = responseClass;
        this.jsonDataListener = listener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String response = getContent(inputStream);
        Log.d(TAG, "onSuccess: response: " + response);
        final T clazz = new Gson().fromJson(response, resposeClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                jsonDataListener.onSuccess(clazz);
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = "";
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;
    }

    @Override
    public void onFailed() {

    }
}
