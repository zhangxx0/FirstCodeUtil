package com.xinxin.firstcodeutil.page;

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
 *
 * 第二个页面；
 *
 */
public class AtySecond extends BaseActivity implements View.OnClickListener {

    // 一键退出按钮
    private Button exitapp;
    private TextView fromactivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        exitapp = (Button) findViewById(R.id.exitapp);
        fromactivity1 = (TextView) findViewById(R.id.fromactivity1);
        exitapp.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        Log.d("IntentExtra",b.getString("testStr"));

        fromactivity1.setText("From Activity1:" + b.getString("testStr"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exitapp :
                ActivityCollector.finishAll();
                break;
        }
    }

    // 启动方法
    public static void actionStart(Context context, Bundle bundle) {
        Intent intent = new Intent(context,AtySecond.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
