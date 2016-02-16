package com.xinxin.firstcodeutil.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.xinxin.firstcodeutil.BaseActivity;
import com.xinxin.firstcodeutil.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by xinxin on 2016/2/11.
 * <p/>
 * 音频
 */
public class AtyAudio extends BaseActivity implements View.OnClickListener {

    private Button audio_play, audio_pause, audio_stop;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        audio_play = (Button) findViewById(R.id.audio_play);
        audio_pause = (Button) findViewById(R.id.audio_pause);
        audio_stop = (Button) findViewById(R.id.audio_stop);

        audio_play.setOnClickListener(this);
        audio_pause.setOnClickListener(this);
        audio_stop.setOnClickListener(this);

        initMediaPlayer();

    }

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "TeamVoice.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.audio_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.audio_stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}

