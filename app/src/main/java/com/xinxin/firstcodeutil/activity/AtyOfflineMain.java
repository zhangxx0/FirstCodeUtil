package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 强制下线主页面
 *
 */
public class AtyOfflineMain extends BaseActivity {

    private Button force_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_main);

        force_offline = (Button) findViewById(R.id.force_offline);

        force_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.xinxin.firstcodeutil.broadcastreceiver.FORCE_ONLINE");

                sendBroadcast(intent);
            }
        });

    }
}
