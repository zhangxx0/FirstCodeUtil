package com.xinxin.firstcodeutil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.activitycollector.ActivityCollector;

/**
 * Created by xinxin on 2016/1/21.
 * <p/>
 * 第二个页面；
 */
public class AtySecond extends BaseActivity implements View.OnClickListener {

    // 一键退出按钮,自定义标题栏按钮,聊天页面,
    private Button exitapp, customviewbtn, talkpagebtn, functionbtn, actionbarbtn, chartsbtn, rxjavabtn, retrofitbtn;
    private TextView fromactivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        exitapp = (Button) findViewById(R.id.exitapp);
        customviewbtn = (Button) findViewById(R.id.customviewbtn);
        fromactivity1 = (TextView) findViewById(R.id.fromactivity1);
        talkpagebtn = (Button) findViewById(R.id.talkpagebtn);
        functionbtn = (Button) findViewById(R.id.functionbtn);
        actionbarbtn = (Button) findViewById(R.id.actionbarbtn);
        chartsbtn = (Button) findViewById(R.id.chartsbtn);
        rxjavabtn = (Button) findViewById(R.id.rxjavabtn);
        retrofitbtn = (Button) findViewById(R.id.retrofitbtn);
        exitapp.setOnClickListener(this);
        customviewbtn.setOnClickListener(this);
        talkpagebtn.setOnClickListener(this);
        functionbtn.setOnClickListener(this);
        actionbarbtn.setOnClickListener(this);
        chartsbtn.setOnClickListener(this);
        rxjavabtn.setOnClickListener(this);
        retrofitbtn.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        Log.d("IntentExtra", b.getString("testStr"));

        fromactivity1.setText("From ActivityMain:" + b.getString("testStr"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitapp:
                ActivityCollector.finishAll();
                break;
            case R.id.customviewbtn:
                Intent intent = new Intent(this, AtyCustomTitle.class);
                startActivity(intent);
                break;
            case R.id.talkpagebtn:
                Intent intent1 = new Intent(this, AtyTalkPage.class);
                startActivity(intent1);
                break;
            case R.id.actionbarbtn:

                break;
            case R.id.chartsbtn:
                Intent intent3 = new Intent(this, AtyCharts.class);
                startActivity(intent3);
                break;
            case R.id.rxjavabtn:
                Intent intent4 = new Intent(this, AtyRxjava.class);
                startActivity(intent4);
                break;
            case R.id.retrofitbtn:
                Intent intent5 = new Intent(this, AtyRetrofit.class);
                startActivity(intent5);
                break;
            case R.id.functionbtn:
                Intent intent2 = new Intent(this, AtyFunctions.class);
                startActivity(intent2);
                break;
        }
    }

    // 启动方法
    public static void actionStart(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AtySecond.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
