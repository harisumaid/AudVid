package com.firstone.audvid;

import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.media.MediaPlayer;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ImageView ta, tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_main);

        ta = (ImageView) findViewById(R.id.imageView);
        tv = (ImageView) findViewById(R.id.imageView2);

        ta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    audio();
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video();
            }
        });
    }

    public void video()
    {
        Intent intent  = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
    public void audio()
    {
        Intent intent = new Intent(this , AudioActivity.class);
        startActivity(intent);
    }
}
