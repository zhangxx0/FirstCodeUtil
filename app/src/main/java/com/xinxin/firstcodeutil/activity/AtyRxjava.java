package com.xinxin.firstcodeutil.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xinxin on 2016/4/18.
 * RxJava学习
 */
public class AtyRxjava extends BaseActivity implements View.OnClickListener {

    private Button rx_btn1, rx_btn2;
    private TextView rx_text1;
    private ImageView rx_img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        initView();

        rx_btn1.setOnClickListener(this);
        rx_btn2.setOnClickListener(this);
    }

    private void initView() {
        rx_btn1 = (Button) findViewById(R.id.rx_btn1);
        rx_btn2 = (Button) findViewById(R.id.rx_btn2);
        rx_text1 = (TextView) findViewById(R.id.rx_text1);
        rx_img1 = (ImageView) findViewById(R.id.rx_img1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rx_btn1:
                // 打印字符串数组
                printStringArray();
                break;
            case R.id.rx_btn2:
                // 由id取得图片并显示
                showPicById();
                break;
            default:
                break;
        }

    }


    private void printStringArray() {
        String[] names = {"name1", "name2", "name3", "name4", "name5"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                rx_text1.setText(s);
            }
        });
    }

    private void showPicById() {
        final int picId = R.drawable.subscribe;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.d("Thread", String.valueOf(Thread.currentThread().getId()));
                // Thread: 10969
                Drawable d = getTheme().getDrawable(picId);
                subscriber.onNext(d);
                subscriber.onCompleted();
            }
        })
        .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
        .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
        .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(AtyRxjava.this, "Complete!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(AtyRxjava.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        Log.d("Thread", String.valueOf(Thread.currentThread().getId()));
                        // Thread: 1
                        rx_img1.setImageDrawable(drawable);
                    }
                });
    }
}
