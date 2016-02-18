package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/18.
 *
 * 位置
 */
public class AtyLocation extends BaseActivity implements View.OnClickListener {

    private Button position_location,position_baidumap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        position_location = (Button) findViewById(R.id.position_location);
        position_baidumap = (Button) findViewById(R.id.position_baidumap);

        position_location.setOnClickListener(this);
        position_baidumap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.position_location:
                Intent i = new Intent(this, AtyLocationPosition.class);
                startActivity(i);
                break;
            case R.id.position_baidumap:

                break;
            default:
                break;
        }
    }
}
