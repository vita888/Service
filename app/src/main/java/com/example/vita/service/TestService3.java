package com.example.vita.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vita on 2017/6/26.
 */

public class TestService3 extends IntentService {
    private final String TAG = "TestService3";

    public TestService3() {
        super("TestService3");
    }//????

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getExtras().getString("params");//intent来自于activity，携带识别参数，根据参数的不同执行不同的任务；
        switch (action){
            case "s1":
                Log.i(TAG,"start service 1");
                break;
            case "s2":
                Log.i(TAG,"start service 2");
                break;
            case "s3":
                Log.i(TAG,"start service 3");
                break;
            default:
                Log.i(TAG,"启动哪个service");
        }
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");

    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        Log.i(TAG,"setIntentRedelivery");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        super.onDestroy();
    }
}
