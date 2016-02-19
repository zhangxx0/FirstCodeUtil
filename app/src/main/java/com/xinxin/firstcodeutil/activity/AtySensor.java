package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/19.
 *
 * 传感器
 *
 */
public class AtySensor extends BaseActivity implements View.OnClickListener {

    private Button sensor_accelerometer,sensor_compass,sensor_light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensor_accelerometer = (Button) findViewById(R.id.sensor_accelerometer);
        sensor_compass = (Button) findViewById(R.id.sensor_compass);
        sensor_light = (Button) findViewById(R.id.sensor_light);

        sensor_accelerometer.setOnClickListener(this);
        sensor_compass.setOnClickListener(this);
        sensor_light.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sensor_accelerometer:

                break;
            case R.id.sensor_compass:

                break;
            case R.id.sensor_light:

                break;
            default:
                break;
        }

    }
}
