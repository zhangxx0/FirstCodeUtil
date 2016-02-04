package com.xinxin.firstcodeutil.broadcastreceiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.xinxin.firstcodeutil.activity.AtyOfflineLogin;
import com.xinxin.firstcodeutil.activitycollector.ActivityCollector;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 强制下线广播接收器
 *
 */
public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {

        AlertDialog.Builder dialogBuider = new AlertDialog.Builder(context);
        dialogBuider.setTitle("Warning");
        dialogBuider.setMessage("You are force to be offline.Please try to login again.");
        dialogBuider.setCancelable(false); // 不可取消
        dialogBuider.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();

                Intent intent1 = new Intent(context, AtyOfflineLogin.class);
                // 广播接收器中启动activity，需要加入以下标志；
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent1);

            }
        });
        AlertDialog alertDialog = dialogBuider.create();
        // 设置alertdialog的类型，保证其在广播接收器中可以正常的弹出；
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();

    }
}
