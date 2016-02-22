package com.xinxin.firstcodeutil.util;

import android.content.Context;
import android.widget.Toast;

import com.xinxin.firstcodeutil.MyApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xinxin on 2016/2/17.
 *
 * 网络请求工具类
 *
 */
public class HttpUtil {

    public static void sendHttpRequest(final Context context,final String address,final HttpCallbackListener listener) {

        if (!isNetworkAvailable()) {
            // 使用MyApplication获取context；
            Toast.makeText(MyApplication.getContext(), "network is unavailable", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    // return response.toString();
                    // 回调onFinish()方法
                    if (listener != null) {
                        // 在子线程中运行，不能进行UI操作；
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // return e.getMessage();
                    // 在子线程中运行，不能进行UI操作；
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();


    }

    // 判断网络是否可用
    private static boolean isNetworkAvailable() {
        return true;
    }
}
