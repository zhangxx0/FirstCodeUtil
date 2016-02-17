package com.xinxin.firstcodeutil.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xinxin.firstcodeutil.broadcastreceiver.AlarmReceiver;

import java.util.Date;

/**
 * Created by xinxin on 2016/2/17.
 * <p/>
 * 后台长时间运行service
 */
public class LongRunningService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().toString());

            }
        }).start();

        // 使用Alarm机制实现；具备唤醒CPU的功能
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int anhour = 60 * 60 * 1000; // 1小时
        int anhour = 60 * 1000; // 1分钟
        // 开机至今的时间
        long triggerAtTime = SystemClock.elapsedRealtime() + anhour;
        // 1970年至今的时间
        long triggerAtTime2 = SystemClock.currentThreadTimeMillis() + anhour;

        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        // 两种写法
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
//        manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime2, pi);

        return super.onStartCommand(intent, flags, startId);
    }
}
