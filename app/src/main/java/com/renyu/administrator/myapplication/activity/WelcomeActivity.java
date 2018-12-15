package com.renyu.administrator.myapplication.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.renyu.administrator.myapplication.R;
import com.renyu.administrator.myapplication.service.DataBaseService;

import java.util.ServiceConfigurationError;

/**
 * Created by Administrator on 2018/12/7.
 */

public class WelcomeActivity extends Activity{

    Myconn myconn;
    DataBaseService.DataBaseServiceBinder mDataBaseServiceBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_welcome);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //开启一服务
        Intent intent = new Intent(this, DataBaseService.class);
        ///开启服务
        startService(intent);
        //绑定服务
        myconn = new Myconn();
        bindService(intent, myconn, Context.BIND_AUTO_CREATE);





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myconn);
        myconn = null;
        mDataBaseServiceBinder = null;
    }

    private class Myconn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
       //获得服务代理
            mDataBaseServiceBinder = (DataBaseService.DataBaseServiceBinder) service;

           //获取定位信息
            mDataBaseServiceBinder.addToBp();


            // 打开main activity
            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
