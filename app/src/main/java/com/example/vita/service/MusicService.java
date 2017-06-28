package com.example.vita.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vita on 2017/6/26.
 */

public class MusicService extends Service {
    private MediaPlayer mMediaPlayer;
    private final String TAG ="MusicService";

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
        Log.i(TAG, "onBind方法被调用!");
        return mMusicBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
         Log.i(TAG, "onCreate方法被调用!");
         mMediaPlayer= MediaPlayer.create(MusicService.this,R.raw.lala);
          mMusicBinder.setMusicName("lalala");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        mMediaPlayer.start();
        Log.i(TAG, "onStartCommand方法被调用!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer=null;
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

}
