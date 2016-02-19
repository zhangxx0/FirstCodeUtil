package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/18.
 * <p/>
 * 百度地图
 * 按照百度的文档进行地图的配置；
 */
public class AtyLocationBaiduMap extends BaseActivity {
    MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location_baidu_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
