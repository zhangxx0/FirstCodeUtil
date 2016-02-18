package com.xinxin.firstcodeutil.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;
import com.xinxin.firstcodeutil.util.HttpCallbackListener;
import com.xinxin.firstcodeutil.util.HttpUtil;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by xinxin on 2016/2/18.
 * <p/>
 * LocationManager
 */
public class AtyLocationPosition extends BaseActivity {

    public static final int SHOW_LOCATION = 0;

    private TextView position_text_view;

    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_position);

        position_text_view = (TextView) findViewById(R.id.position_text_view);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            // 当没有可用的位置提供器时，弹出Toast提示用户
            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            // 显示当前设备的位置信息
            showLocation(location);
        } else {
            position_text_view.setText("2222");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            // 关闭程序时将监听器移除
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            locationManager.removeUpdates(locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // 更新当前设备的位置信息
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_LOCATION:
                    String currentPosition = (String) msg.obj;
                    position_text_view.setText(currentPosition);
                    break;
                default:
                    break;
            }
        }

    };


    private void showLocation(final Location location) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 组装反向地理编码的接口地址
//                    StringBuilder url = new StringBuilder();
//                    url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
//                    url.append(location.getLatitude()).append(",")
//                            .append(location.getLongitude());
//                    url.append("&sensor=false");

                    StringBuilder url = new StringBuilder();
                    url.append("http://api.map.baidu.com/geocoder/v2/?ak=LsC6apsHPemnyZaN7coVNrMu&location=");
                    url.append(location.getLatitude()).append(",")
                            .append(location.getLongitude());
                    url.append("&output=json&pois=1");

                    // 访问API超时，经测试在手机上访问：http://maps.googleapis.com/maps/api/geocode/json?latlng=36.068776,120.363782&sensor=false
                    //  总是超时的，配置了VPN之后，google也不能访问，不知道是什么原因；
                    //  寻求一个国内能用的反向地理编码接口；http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding

                    HttpUtil.sendHttpRequest(url.toString(), new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {

                            try {
                                // 获取results节点下的位置信息
                                // google的Geocoding API解析
//                                JSONArray resultArray = jsonObject.getJSONArray("results");
//                                if (resultArray.length() > 0) {
//                                    JSONObject subObject = resultArray.getJSONObject(0);
//                                    // 取出格式化后的位置信息
//                                    String address = subObject.getString("formatted_address");
//                                    Message message = new Message();
//                                    message.what = SHOW_LOCATION;
//                                    message.obj = address;
//                                    handler.sendMessage(message);
//                                }

                                // 狗日的百度弄的什么破JSON格式！一点儿都不正规
                                // 带回调函数的返回格式 ？？？ 去掉：&callback=renderReverse
                                // // baidu的Geocoding API解析
                                JSONObject jsonObject = new JSONObject(response);
                                JSONObject resultObj = jsonObject.getJSONObject("result");
                                String formatted_address = resultObj.getString("formatted_address");

                                Message message = new Message();
                                message.what = SHOW_LOCATION;
                                message.obj = formatted_address;
                                handler.sendMessage(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}









