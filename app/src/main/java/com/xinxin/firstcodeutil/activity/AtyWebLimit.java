package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.util.HttpCallbackListener;
import com.xinxin.firstcodeutil.util.HttpUtil;

/**
 * Created by xinxin on 2016/4/21.
 */
public class AtyWebLimit extends BaseActivity {

    private Button web_limit_test;
    private TextView web_limit_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_limit);

        web_limit_test = (Button) findViewById(R.id.web_limit_test);
        web_limit_show = (TextView) findViewById(R.id.web_limit_show);

        web_limit_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String limitUrl = "http://182.18.61.50/getUserJson?name=%E5%BA%B7%E6%81%A9%E9%A5%AD_&area=north";
                String limitUrl2 = "http://ncw.worldoftanks.cn/zh-cn/community/clans/show_clan_block/?spa_id=1509154099&time_token=1459046879981";

                HttpUtil.sendHttpRequest(limitUrl2, new HttpCallbackListener() {
                    @Override
                    public void onFinish(final String response) {
                        Log.d("成功",response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                web_limit_show.setText(response);

                            }
                        });
                    }

                    @Override
                    public void onError(final Exception e) {
                        Log.d("失败",e.toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                web_limit_show.setText(e.toString());

                            }
                        });

                    }
                });

            }
        });

    }
}
