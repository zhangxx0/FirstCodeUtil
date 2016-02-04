package com.xinxin.firstcodeutil.activitycollector;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinxin on 2016/1/21.
 *
 * Activity管理器
 *
 * 用于随时随地退出程序
 */
public class ActivityCollector {

    // Activity列表
    private static List<Activity> activities = new ArrayList<Activity>();

    // 添加
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // 去除
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 结束所有Activity，一键退出
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
