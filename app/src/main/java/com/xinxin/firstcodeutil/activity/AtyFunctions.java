package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/4.
 *
 * 功能页面
 *
 */
public class AtyFunctions extends BaseActivity implements View.OnClickListener {

    private Button fragmentbtn,storagetbtn,receiverbtn
            ,providerbtn,mediabtn,servicebtn
            ,webbtn,locationbtn,senserbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);

        init();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragmentbtn:
                Intent i0 = new Intent(this,AtyFragment.class);
                startActivity(i0);
                break;
            case R.id.storagetbtn:
                Intent i1 = new Intent(this,AtyStorage.class);
                startActivity(i1);
                break;
            case R.id.receiverbtn:
                Intent i2 = new Intent(this,AtyBroadcast.class);
                startActivity(i2);
                break;
            case R.id.providerbtn:
                Intent i3 = new Intent(this,AtyProvider.class);
                startActivity(i3);
                break;
            case R.id.mediabtn:
                Intent i4 = new Intent(this,AtyMedia.class);
                startActivity(i4);
                break;
            case R.id.servicebtn:
                Intent i5 = new Intent(this,AtyService.class);
                startActivity(i5);
                break;
            case R.id.webbtn:
                Intent i6 = new Intent(this, AtyWeb.class);
                startActivity(i6);
                break;
            case R.id.locationbtn:
                break;
            case R.id.senserbtn:
                break;
        }

    }

    private void init() {
        fragmentbtn = (Button) findViewById(R.id.fragmentbtn);
        storagetbtn = (Button) findViewById(R.id.storagetbtn);
        receiverbtn = (Button) findViewById(R.id.receiverbtn);
        providerbtn = (Button) findViewById(R.id.providerbtn);
        mediabtn = (Button) findViewById(R.id.mediabtn);
        servicebtn = (Button) findViewById(R.id.servicebtn);
        webbtn = (Button) findViewById(R.id.webbtn);
        locationbtn = (Button) findViewById(R.id.locationbtn);
        senserbtn  = (Button) findViewById(R.id.senserbtn);

        fragmentbtn.setOnClickListener(this);
        storagetbtn.setOnClickListener(this);
        receiverbtn.setOnClickListener(this);
        providerbtn.setOnClickListener(this);
        mediabtn.setOnClickListener(this);
        servicebtn.setOnClickListener(this);
        webbtn.setOnClickListener(this);
        locationbtn.setOnClickListener(this);
        senserbtn.setOnClickListener(this);
    }
}
