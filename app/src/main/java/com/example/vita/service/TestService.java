package com.example.vita.service;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vita on 2017/6/26.
 */

public class TestService extends Service {
   private final String Tag = "TestService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(Tag,"onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(Tag,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Tag,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(Tag,"onDestroy");
        super.onDestroy();
    }
}
