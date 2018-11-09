package com.renyu.administrator.myapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.renyu.administrator.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NewsViewModel newsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注意这里不会自己引用
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        newsViewModel = new NewsViewModel(this, binding);
        ;
    }
}
