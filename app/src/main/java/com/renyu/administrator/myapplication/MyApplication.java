package com.renyu.administrator.myapplication;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by Administrator on 2018/12/6.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LitePal.initialize(this);

    }
    public static Context getAppContext(){
        return context;
    }
}
