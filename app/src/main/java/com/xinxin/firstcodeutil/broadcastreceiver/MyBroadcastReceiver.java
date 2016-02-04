package com.xinxin.firstcodeutil.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 自定义广播接收器
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"received in MyBroadcastReceiver",Toast.LENGTH_SHORT).show();
        // 截断，根据优先级
        abortBroadcast();
    }
}
