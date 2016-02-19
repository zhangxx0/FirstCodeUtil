package com.xinxin.firstcodeutil.activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView lightLevel;
    private ImageView compassImg;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensor_accelerometer = (Button) findViewById(R.id.sensor_accelerometer);
        sensor_compass = (Button) findViewById(R.id.sensor_compass);
        sensor_light = (Button) findViewById(R.id.sensor_light);
        lightLevel = (TextView) findViewById(R.id.sensor_light_level);

        sensor_accelerometer.setOnClickListener(this);
        sensor_compass.setOnClickListener(this);
        sensor_light.setOnClickListener(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
            sensorManager.unregisterListener(listener2);
            sensorManager.unregisterListener(listener3);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sensor_accelerometer:
                Sensor sensor = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(listener, sensor,
                        SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.sensor_compass:
                Sensor magneticSensor = sensorManager
                        .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                Sensor accelerometerSensor = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(listener3, magneticSensor,
                        SensorManager.SENSOR_DELAY_GAME);
                sensorManager.registerListener(listener3, accelerometerSensor,
                        SensorManager.SENSOR_DELAY_GAME);

                break;
            case R.id.sensor_light:
                Sensor sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                sensorManager.registerListener(listener2, sensor2, SensorManager.SENSOR_DELAY_NORMAL);

                break;
            default:
                break;
        }

    }

    private SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 加速度可能会是负值，所以要取它们的绝对值
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);
            if (xValue > 15 || yValue > 15 || zValue > 15) {
                // 认为用户摇动了手机，触发摇一摇逻辑
                Toast.makeText(AtySensor.this, "摇一摇", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private SensorEventListener listener2 = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第一个下标的值就是当前的光照强度
            float value = event.values[0];
            lightLevel.setText("Current light level is " + value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

    private SensorEventListener listener3 = new SensorEventListener() {

        float[] accelerometerValues = new float[3];

        float[] magneticValues = new float[3];

        private float lastRotateDegree;

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 判断当前是加速度传感器还是地磁传感器
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                // 注意赋值时要调用clone()方法
                accelerometerValues = event.values.clone();
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                // 注意赋值时要调用clone()方法
                magneticValues = event.values.clone();
            }
            float[] values = new float[3];
            float[] R = new float[9];
            SensorManager.getRotationMatrix(R, null, accelerometerValues,
                    magneticValues);
            SensorManager.getOrientation(R, values);
            float rotateDegree = -(float) Math.toDegrees(values[0]);
            if (Math.abs(rotateDegree - lastRotateDegree) > 1) {
                RotateAnimation animation = new RotateAnimation(
                        lastRotateDegree, rotateDegree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setFillAfter(true);
                compassImg.startAnimation(animation);
                lastRotateDegree = rotateDegree;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };
}
