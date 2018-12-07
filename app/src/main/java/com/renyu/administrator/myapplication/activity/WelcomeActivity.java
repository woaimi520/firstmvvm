package com.renyu.administrator.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.renyu.administrator.myapplication.R;

/**
 * Created by Administrator on 2018/12/7.
 */

public class WelcomeActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_welcome);
        } catch (OutOfMemoryError error) {
            Log.i("1", "11");
        }

    }
}
