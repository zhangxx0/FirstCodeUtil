package com.xinxin.firstcodeutil.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.service.LongRunningService;
import com.xinxin.firstcodeutil.service.MyIntentService;
import com.xinxin.firstcodeutil.service.MyService;

/**
 * Created by xinxin on 2016/2/17.
 *
 * 服务
 *
 */
public class AtyService extends BaseActivity implements View.OnClickListener {

    private Button service_handler,service_asynctask,
            service_start,service_stop,service_bind,service_unbind,
            service_start_intent,service_best_practice;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 指挥服务
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        init();

    }

    private void init() {
        service_handler = (Button) findViewById(R.id.service_handler);
        service_handler.setOnClickListener(this);
        service_asynctask = (Button) findViewById(R.id.service_asynctask);
        service_asynctask.setOnClickListener(this);
        service_start = (Button) findViewById(R.id.service_start);
        service_start.setOnClickListener(this);
        service_stop = (Button) findViewById(R.id.service_stop);
        service_stop.setOnClickListener(this);
        service_bind = (Button) findViewById(R.id.service_bind);
        service_bind.setOnClickListener(this);
        service_unbind = (Button) findViewById(R.id.service_unbind);
        service_unbind.setOnClickListener(this);
        service_start_intent = (Button) findViewById(R.id.service_start_intent);
        service_start_intent.setOnClickListener(this);

        service_best_practice = (Button) findViewById(R.id.service_best_practice);
        service_best_practice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service_handler:
                Intent i = new Intent(this, AtyHandler.class);
                startActivity(i);
                break;
            case R.id.service_asynctask:
                break;
            case R.id.service_start:
                Intent i0 = new Intent(this, MyService.class);
                startService(i0); // 启动服务
                break;
            case R.id.service_stop:
                Intent i1 = new Intent(this, MyService.class);
                stopService(i1); // 停止服务
                break;
            case R.id.service_bind:
                Intent i2 = new Intent(this, MyService.class);
                // BIND_AUTO_CREATE 表示在活动和服务进行绑定后自动创建服务，这会使MyService中的onCreate方法执行，但onStartCommand方法不执行；
                bindService(i2, connection, BIND_AUTO_CREATE); // 绑定服务
                break;
            case R.id.service_unbind:
                unbindService(connection); // 解绑服务
                break;
            case R.id.service_start_intent:
                Log.d("AtyService", "Thread is " + Thread.currentThread());
                Intent i3 = new Intent(this, MyIntentService.class);
                startService(i3);
                break;

            case R.id.service_best_practice:
                Intent i4 = new Intent(this, LongRunningService.class);
                startService(i4);
                break;
        }

    }
}
