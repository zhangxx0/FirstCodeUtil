package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;


/**
 * Created by xinxin on 2016/2/17.
 *
 * handler
 */
public class AtyHandler extends BaseActivity {
    public static final int UPDATE_TEXT = 1;
    private Button handler_btn;
    private TextView handler_text;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    handler_text.setText("changed text");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler_btn = (Button) findViewById(R.id.handler_btn);
        handler_text = (TextView) findViewById(R.id.handler_text);

        handler_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = UPDATE_TEXT;
                handler.sendMessage(msg);
            }
        });

    }
}
