package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/17.
 *
 * WebView
 *
 */
public class AtyWebView extends BaseActivity {

    private WebView web_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        web_webview = (WebView) findViewById(R.id.web_webview);

        web_webview.getSettings().setJavaScriptEnabled(true);
        web_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 这个方法可以不用重写，直接new一个WebViewClient也是可以的；
                // 根据传入的参数再去加载新的网页
                view.loadUrl(url);
                // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
                return true;
            }
        });

        web_webview.loadUrl("https://www.baidu.com/");


    }
}
