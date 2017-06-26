package com.example.vita.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Vita on 2017/6/26.
 */

public class MusicService extends Service {
    private MediaPlayer mMediaPlayer;

    public class MusicBinder extends Binder{
        String name;
        public void setMusicName(String na){
            this.name =na;
        }
        public String getName(){
            return this.name;
        }
    }

    MusicBinder mMusicBinder= new MusicBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMusicBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mMediaPlayer = new MediaPlayer();
            MediaPlayer.create(MusicService.this,R.mipmap.ic_launcher);///yaoxiugai

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        mMusicBinder.setMusicName("lalala");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mMediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer=null;
        super.onDestroy();
    }
}
