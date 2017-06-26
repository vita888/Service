package com.example.vita.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vita on 2017/6/26.
 */

public class TestService2 extends Service {
    private final String TAG = "TestService2";
    private int mCount;
    private boolean mQuit;

    public class MyBinder extends Binder{
        public int getCount(){
            return mCount;
        }//定义一个计时器方法
    }
    private  MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind方法被调用!");
        return myBinder;
    }//绑定service时回调的方法

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate方法被调用!");

        //创建一个线程
        new Thread(){
            public void run(){
                while (!mQuit){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    mCount++;
                }
            }
        }.start();

    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind方法被调用");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mQuit = true;
        Log.i(TAG,"onDestroy方法被调用");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"onRebind方法被调用");
        super.onRebind(intent);
    }
}
