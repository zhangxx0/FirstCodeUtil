package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/17.
 */
public class AtyWeb extends BaseActivity implements View.OnClickListener {

    private Button web_webview_btn,web_web_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        web_webview_btn = (Button) findViewById(R.id.web_webview_btn);
        web_web_btn = (Button) findViewById(R.id.web_web_btn);
        web_webview_btn.setOnClickListener(this);
        web_web_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_webview_btn:
                Intent i = new Intent(this, AtyWebView.class);
                startActivity(i);
                break;
            case R.id.web_web_btn:
                Intent i2 = new Intent(this, AtyWebNetwork.class);
                startActivity(i2);
                break;
            default:
                break;
        }
    }
}
