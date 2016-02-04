package com.xinxin.firstcodeutil.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 自定义标题栏
 */
public class TitleLayout extends LinearLayout {

    private Button backBtn,editBtn;
    private TextView titleText;

    public TitleLayout(Context context,AttributeSet attrs) {
        super(context,attrs);
        // 动态加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_title,this);

        backBtn = (Button) findViewById(R.id.custom_title_back);
        editBtn = (Button) findViewById(R.id.custom_title_edit);

        backBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

        editBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"You click Edit button",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
