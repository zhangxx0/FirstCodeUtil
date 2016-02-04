package com.xinxin.firstcodeutil.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 广播接收
 *
 */
public class AtyBroadcast extends BaseActivity implements View.OnClickListener {

    private Button broadcast_normal_btn,broadcast_ordered_btn,broadcast_local_btn
            ,broadcast_force_offline_btn;

    // 本地广播
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        broadcast_normal_btn = (Button) findViewById(R.id.broadcast_normal_btn);
        broadcast_ordered_btn = (Button) findViewById(R.id.broadcast_ordered_btn);
        broadcast_local_btn = (Button) findViewById(R.id.broadcast_local_btn);
        broadcast_force_offline_btn = (Button) findViewById(R.id.broadcast_force_offline_btn);

        broadcast_normal_btn.setOnClickListener(this);
        broadcast_ordered_btn.setOnClickListener(this);
        broadcast_local_btn.setOnClickListener(this);
        broadcast_force_offline_btn.setOnClickListener(this);

        // 本地广播机制
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.xinxin.firstcodeutil.broadcastreceiver.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        // 注册本地广播监听器
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.broadcast_normal_btn:
                Intent intent = new Intent("com.xinxin.firstcodeutil.broadcastreceiver.MY_BROADCAST");
                sendBroadcast(intent);
                break;
            case R.id.broadcast_ordered_btn:
                Intent intent2 = new Intent("com.xinxin.firstcodeutil.broadcastreceiver.MY_BROADCAST");
                // 发送有序广播；
                sendOrderedBroadcast(intent2,null);
                break;
            case R.id.broadcast_local_btn:
                // 发送本地广播
                Intent intent1 = new Intent("com.xinxin.firstcodeutil.broadcastreceiver.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent1);
                break;
            case R.id.broadcast_force_offline_btn:
                Intent intent3 = new Intent(this,AtyOfflineLogin.class);
                startActivity(intent3);
                break;
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
