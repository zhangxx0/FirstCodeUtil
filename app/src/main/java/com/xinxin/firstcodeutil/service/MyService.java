package com.xinxin.firstcodeutil.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.activity.AtyService;

/**
 * Created by xinxin on 2016/2/17.
 */
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getprogress executed");
            return 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "启动MyService");

        // 前台服务
        // 郭霖所使用的方法，setLatestEventInfo已经废弃，无法继续使用；
//        Notification notification = new Notification(R.mipmap.ic_launcher, "Notification comes", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, AtyService.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(this, "This is title", "This is content",
//                pendingIntent);
//        startForeground(1, notification);

        // 查找前台服务的实现方法
        // 网上没有具体的教程，打算自己来写一篇博客来阐述下这个的实现；再完善下，加上tick之类；
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // 必需的通知内容
        builder.setContentTitle("content title")
                .setContentText("content describe")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent notifyIntent = new Intent(this, AtyService.class);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(notifyPendingIntent);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(1, notification);

        startForeground(1, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "MyService onStartCommand方法");
        // 一旦启动，立即执行的动作
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 处理具体的逻辑
                //stopSelf(); // 停止服务
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "销毁MyService");
    }
}
