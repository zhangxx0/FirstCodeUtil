package com.xinxin.firstcodeutil.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xinxin.firstcodeutil.service.LongRunningService;

/**
 * Created by xinxin on 2016/2/17.
 *
 * service定时执行广播接收器
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LongRunningService.class);
        context.startService(i);
    }
}
