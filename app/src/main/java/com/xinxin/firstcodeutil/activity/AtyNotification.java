package com.xinxin.firstcodeutil.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/11.
 * <p/>
 * 发送通知
 */
public class AtyNotification extends BaseActivity {

    private Button notification_send;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification_send = (Button) findViewById(R.id.notification_send);

        notification_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送通知
                // （1）郭霖书中的方式，但是其中notification的初始化方法已经废弃；
                /*
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification(R.mipmap.ic_launcher,"ticker text",System.currentTimeMillis());

                Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
                notification.sound = soundUri;

                long[] vibrates = {0,1000,1000,1000};
                notification.vibrate = vibrates;

                notification.ledARGB = Color.GREEN;
                notification.ledOnMS = 1000;
                notification.ledOffMS = 1000;
                notification.flags = Notification.FLAG_SHOW_LIGHTS;

//                notification.defaults = Notification.DEFAULT_ALL;

                Intent intent = new Intent(AtyNotification.this,AtyNotificationPending.class);
                PendingIntent pendingIntent = PendingIntent.getActivities(AtyNotification.this,0, new Intent[]{intent},PendingIntent.FLAG_CANCEL_CURRENT);

                // 郭霖的代码中这一行报错？？？
                notification.setLatestEventInfo(this, "This is content title",
                        "This is content text", pendingIntent);

                manager.notify(1,notification);
                */

                // （2）比较新的一种方式
                NotificationCompat.Builder builder = new NotificationCompat.Builder(AtyNotification.this);

                count ++ ;

                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("有"+count+"个新消息");
                builder.setContentText("消息内容");

                Notification notification = builder.build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                manager.notify(R.layout.activity_notification,notification);



            }
        });

    }
}
