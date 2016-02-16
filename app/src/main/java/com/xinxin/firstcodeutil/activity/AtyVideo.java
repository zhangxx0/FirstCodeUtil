package com.xinxin.firstcodeutil.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

import java.io.File;

/**
 * Created by xinxin on 2016/2/11.
 *
 * 视频
 */
public class AtyVideo extends BaseActivity implements View.OnClickListener {

    private VideoView videoView;

    private Button video_play,video_pause,video_replay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video_play = (Button) findViewById(R.id.video_play);
        video_pause = (Button) findViewById(R.id.video_pause);
        video_replay= (Button) findViewById(R.id.video_replay);

        video_play.setOnClickListener(this);
        video_pause.setOnClickListener(this);
        video_replay.setOnClickListener(this);

        initVideoPath();
    }

    public void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(),"movie.3gp");
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.video_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.video_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
