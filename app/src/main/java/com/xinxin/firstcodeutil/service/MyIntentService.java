package com.xinxin.firstcodeutil.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xinxin on 2016/2/17.
 *
 * IntentService
 * 异步的，会自动停止的服务
 *
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        // 调用父类的有参构造函数
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService", "Thread is:" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "myIntentService destroy");
    }
}
