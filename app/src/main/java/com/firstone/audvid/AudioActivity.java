package com.firstone.audvid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AudioActivity extends AppCompatActivity {

    Boolean isplaying=false;
    MediaPlayer mplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }
    public void audio(final View view)
    {

        final TextView curdur;
        final TextView findur;
        final AudioManager audioManager;
        final SeekBar scrubber = (SeekBar) findViewById(R.id.scrubber);
        scrubber.setMax(mplayer.getDuration());




        curdur = (TextView) findViewById(R.id.curdur);
        findur = (TextView) findViewById(R.id.findur);
        findur.setText(Integer.toString(mplayer.getDuration()));
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int MaxVol =audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int CurVol =audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volbar = (SeekBar) findViewById(R.id.volbar);
        volbar.setMax(MaxVol);
        volbar.setProgress(CurVol);

        volbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        },0,50);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                curdur.setText(Integer.toString(scrubber.getProgress()));
                if(fromUser==true)
                {
                    mplayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                audp(view);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                aud(view);
            }
        });
    }

    public void aud(View view)
    {
        mplayer.start();
    }

    public void audp(View view) {
        mplayer.pause();

    }

    public void play(View view)
    {
        if(isplaying == true)
            mplayer.stop();

        isplaying=true;
        int id = view.getId();
        String songId = "";
        songId = getResources().getResourceEntryName(id);

        int id1 = getResources().getIdentifier(songId,"raw" ,"com.firstone.audvid");

        mplayer = MediaPlayer.create(this,id1);
        mplayer.start();
        audio(view);

    }

    public void home(View view)
    {
        setContentView(R.layout.activtiy_main);
        mplayer.stop();
        isplaying=false;
    }


}



