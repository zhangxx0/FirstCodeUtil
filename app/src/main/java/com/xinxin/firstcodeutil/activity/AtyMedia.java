package com.xinxin.firstcodeutil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

/**
 * Created by xinxin on 2016/2/10.
 * <p/>
 * 多媒体
 */
public class AtyMedia extends BaseActivity implements View.OnClickListener {

    private Button media_notification, media_sms, media_playaudio, media_playvideo, media_Choosepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        media_notification = (Button) findViewById(R.id.media_notification);
        media_sms = (Button) findViewById(R.id.media_sms);
        media_playaudio = (Button) findViewById(R.id.media_playaudio);
        media_playvideo = (Button) findViewById(R.id.media_playvideo);
        media_Choosepic = (Button) findViewById(R.id.media_Choosepic);

        media_notification.setOnClickListener(this);
        media_sms.setOnClickListener(this);
        media_playaudio.setOnClickListener(this);
        media_playvideo.setOnClickListener(this);
        media_Choosepic.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.media_notification:
                Intent i = new Intent(this,AtyNotification.class);
                startActivity(i);
                break;
            case R.id.media_sms:
                Intent i1 = new Intent(this,AtySms.class);
                startActivity(i1);
                break;
            case R.id.media_playaudio:
                Intent i2 = new Intent(this,AtyAudio.class);
                startActivity(i2);
                break;
            case R.id.media_playvideo:
                Intent i3 = new Intent(this,AtyVideo.class);
                startActivity(i3);
                break;
            case R.id.media_Choosepic:
                Intent i4 = new Intent(this,AtyChoosePic.class);
                startActivity(i4);
                break;
        }

    }
}
