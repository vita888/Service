package com.example.vita.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mStart;
    private Button mStop;
    private Button mBind;
    private Button mUnBind;
    private Button mStatus;
    private Button mIntnetService;
    private Button mMusic;
    TestService2.MyBinder mBinder;
    Boolean musicTogle = false;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("#####", "------Service Connected-------");
            mBinder = (TestService2.MyBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("#####", "------Service DisConnected-------");
        }
    };

    MusicService.MusicBinder mMusicBinder;
    private ServiceConnection musicConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("#####", "------Service Connected-------");
            mMusicBinder = (MusicService.MusicBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("#####", "------Service DisConnected-------");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStart = (Button) findViewById(R.id.btn1);
        mStop = (Button) findViewById(R.id.btn2);
        mBind = (Button) findViewById(R.id.btn3);
        mUnBind = (Button) findViewById(R.id.btn4);
        mStatus = (Button) findViewById(R.id.btn5);
        mIntnetService = (Button) findViewById(R.id.btn6);
        mMusic = (Button)findViewById(R.id.btn7);

        final Intent intent = new Intent(this, TestService.class);
        final Intent intent1 = new Intent(this, TestService2.class);


        intent.setAction("com.example.vita.service.TestService");
        intent1.setAction("com.example.vita.service.TestService2");

        final Intent it1 = new Intent();
        it1.setAction("android.intent.service.TEST_SERVICE3");
        it1.setPackage("com.example.vita.service");
        Bundle b1 = new Bundle();
        b1.putString("params", "s1");
        it1.putExtras(b1);

        final Intent it2 = new Intent();
        it2.setAction("android.intent.service.TEST_SERVICE3");
        it2.setPackage("com.example.vita.service");
        Bundle b2 = new Bundle();
        b2.putString("params", "s2");
        it2.putExtras(b2);

        final Intent it3 = new Intent();
        it3.setAction("android.intent.service.TEST_SERVICE3");
        it3.setPackage("com.example.vita.service");
        Bundle b3 = new Bundle();
        b3.putString("params", "s3");
        it3.putExtras(b3);

        //final Intent itmusic = new Intent(this,MusicService.class);
        final Intent intentmusic =new Intent(MainActivity.this,MusicService.class);

        mMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicTogle=!musicTogle;
                if (musicTogle){
                    //Intent intentmusic =new Intent(MainActivity.this,MusicService.class);
                    Toast.makeText(MainActivity.this,"正在播放："+mMusicBinder.getName(),Toast.LENGTH_SHORT).show();
                    startService(intentmusic);
                }
                else {
                  //  Intent intentmusic =new Intent(MainActivity.this,MusicService.class);
                    Toast.makeText(MainActivity.this,"结束播放",Toast.LENGTH_SHORT).show();
                    stopService(intentmusic);
                }
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                Log.i("########", "点击了开始按钮");
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                Log.i("###############", "点击了停止按钮");
            }
        });

        mBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent1, mConn, Service.BIND_AUTO_CREATE);//绑定service、
            }
        });
        mUnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConn);
            }
        });
        mStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Service 的 count值是：" + mBinder.getCount(), Toast.LENGTH_SHORT).show();
            }
        });

        mIntnetService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(it1);
                startService(it2);
                startService(it3);

            }
        });


    }
}
