package com.xinxin.firstcodeutil.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/10.
 * <p/>
 * sharedpreferences存储
 */
public class AtyStorageSharedPreferences extends BaseActivity implements View.OnClickListener {

    private Button shared_preferences_save, shared_preferences_restore;
    private TextView shared_preferences_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_shared_preferences);

        shared_preferences_save = (Button) findViewById(R.id.shared_preferences_save);
        shared_preferences_restore = (Button) findViewById(R.id.shared_preferences_restore);
        shared_preferences_show = (TextView) findViewById(R.id.shared_preferences_show);

        shared_preferences_save.setOnClickListener(this);
        shared_preferences_restore.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shared_preferences_save:
                // 保存到SharedPreferences 第一行没有指定名字，导致无法获取；
                // SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 27);
                editor.putBoolean("married", false);
                editor.commit();

                break;
            case R.id.shared_preferences_restore:
                // 从SharedPreferences取值

                // 这里并没有取到相应的值？？？这是什么情况？？？
                // 2016年2月16日14:28:31 进行错误的查找
                //  保存的时候没有指定名字,如上；OK
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);

                Log.d("SharedPreferences:", "name is:" + name);
                Log.d("SharedPreferences:", "age id:" + age);
                Log.d("SharedPreferences:", "married is:" + married);

                shared_preferences_show.setText("name:"+ name+",age:"+age+",married:"+married);


                break;
        }
    }
}
