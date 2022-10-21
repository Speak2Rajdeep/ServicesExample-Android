package com.example.servicesexample;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

public class MyService extends Service {
    /*
    * About This App
    * ---------------------------------
    * Created a Service Call in Background
    * This App Plays Music in background
    */

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //To Start The Service this method is Called!
    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        Toast.makeText(this, "Background Service Started", Toast.LENGTH_SHORT).show();
        //Plays the Music even the app Destroys!
        return Service.START_CONTINUATION_MASK;
    }

    //To Destroy the service this method is Called
    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        super.onDestroy();
        Toast.makeText(this, "Background Service Stopped", Toast.LENGTH_SHORT).show();
    }
}