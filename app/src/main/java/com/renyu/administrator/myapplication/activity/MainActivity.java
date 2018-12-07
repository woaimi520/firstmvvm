package com.renyu.administrator.myapplication.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.renyu.administrator.myapplication.viewmodel.NewsViewModel;
import com.renyu.administrator.myapplication.R;
import com.renyu.administrator.myapplication.databinding.ActivityMainBinding;

import yu.com.Aad;

public class MainActivity extends AppCompatActivity {
    private NewsViewModel newsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意这里不会自己引用
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        newsViewModel = new NewsViewModel(this, binding);
        Aad aad = new Aad();
    }
}
