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

    String url = "https://ptcoopsearch.reader.qq.com/hotkey?hotkeytype=huawei&changenum=0";
    private TextView viewById;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.textView);




    }

    public void sendRequest(View view) {

        LuOkHttp.sendJsonRequest("", url, TestBean.class, new IJsonDataListener<TestBean>(){
            @Override
            public void onSuccess(TestBean clazz) {
                if (clazz != null) {
                    List<TestBean.BooksBean> books = clazz.getBooks();
                    viewById.setText(books.toString());
                }
            }

            @Override
            public void onFailed() {

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

}
