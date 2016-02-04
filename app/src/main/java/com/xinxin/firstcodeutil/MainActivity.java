package com.xinxin.firstcodeutil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.activity.AtySecond;

/**
 * 阅读郭霖的第一行代码，将其中的一些实用的代码汇集与此；
 * 可作为之后的代码工具查阅；
 *
 * 2016年1月21日15:30:15
 * zhang.xx
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mainbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainbtn = (Button) findViewById(R.id.mainbtn);

        mainbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainbtn:
                Bundle b = new Bundle();
                b.putString("testStr","testStr");
                AtySecond.actionStart(this, b);
                break;
        }
    }
}
