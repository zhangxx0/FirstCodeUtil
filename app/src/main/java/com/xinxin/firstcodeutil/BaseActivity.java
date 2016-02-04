package com.xinxin.firstcodeutil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.xinxin.firstcodeutil.activitycollector.ActivityCollector;

/**
 * Created by xinxin on 2016/1/21.
 *
 * 基类
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 打印Activity的名字，确认是哪个Activity
        Log.d("BaseActivity",getClass().getSimpleName());

        // 添加到活动管理器
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 从活动管理器中去除
        ActivityCollector.removeActivity(this);
    }
}
