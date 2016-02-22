package com.xinxin.firstcodeutil;

import android.app.Application;
import android.content.Context;

/**
 * Created by xinxin on 2016/2/22.
 *
 * MyApplication
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
