package com.lulu.curl.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lulu.curl.okhttpdemo.luokhttp.IJsonDataListener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends AppCompatActivity {

    String url = "https://www.baidu.com/";
    private TextView viewById;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.textView);




    }

    public void sendRequest(View view) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                URL url = null;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("http://v.juhe.cn/toutiao/index");
                    urlConnection = (HttpURLConnection) url.openConnection();//打开Http链接
                    urlConnection.setConnectTimeout(6000);//连接超时时间
                    urlConnection.setUseCaches(false);//不使用缓存
                    urlConnection.setInstanceFollowRedirects(false);//是成员函数,仅作用于当前函数,设置这个链接是否可以被重定向
                    urlConnection.setReadTimeout(3000);//响应的超时时间
                    urlConnection.setDoInput(true);//设置这个连接是否可以写入数据
                    urlConnection.setDoOutput(true);//设置这个连接是否可以输出数据
                    urlConnection.setRequestMethod("GET");//设置请求方式
                    urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");//设置消息类型
                    urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6799.400 QQBrowser/10.3.2908.400");//设置消息类型
                    urlConnection.connect();//连接,从上述至此的配置必须要在connect之前完成


                    //-----------字符流写入数据-----------
                    if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {//得到服务器返回码是否连接成功
                        InputStream in = urlConnection.getInputStream();
                        Log.d(TAG, "run: 打印数据: " + getContent(in));
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            }
        }.start();



//        LuOkHttp.sendJsonRequest("", url, TestBean.class, new IJsonDataListener<TestBean>(){
//            @Override
//            public void onSuccess(TestBean clazz) {
//                if (clazz != null) {
//                    List<TestBean.BooksBean> books = clazz.getBooks();
//                    viewById.setText(books.toString());
//                }
//            }
//
//            @Override
//            public void onFailed() {
//
//            }
//        });
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

}
