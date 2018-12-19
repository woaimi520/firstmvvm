package com.renyu.administrator.myapplication.viewmodel;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.renyu.administrator.myapplication.data.CityIdConstance;
import com.renyu.administrator.myapplication.model.MainModel;
import com.renyu.administrator.myapplication.util.NewslistBean;
import com.renyu.administrator.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/11/7.
 */

public class MainViewModel implements MainModel.Reasult {


    public Activity activity;
    public ActivityMainBinding activityMainBinding;
    public NewslistBean newslistBean;
    public MainModel model;

    private int num = 1;
    String outter = "get data";
    private String cityCode;

    public MainViewModel(Activity activity, final ActivityMainBinding activityMainBinding) {
        this.activity = activity;
        this.activityMainBinding = activityMainBinding;

        newslistBean = new NewslistBean("renyu", "titler", "des");

        //读取到citycode start
        SharedPreferences sp = activity.getSharedPreferences("citycode", 0);
        cityCode = sp.getString("cityCode", CityIdConstance.BEIJING);

        //end
        model = new MainModel(newslistBean,this);
        activityMainBinding.setNews(newslistBean);//设置初始值
        newslistBean.setCtime("hahah");
        //Button Btn = (Button) activity.findViewById(R.id.button0);
        activityMainBinding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), outter, Toast.LENGTH_SHORT).show();
                model.getData();
            }
        });
    }


    @Override
    public void onSuccess(ArrayList<String> list) {
        if(list !=null && list.size()!=0) {
            String data = list.get(0);
            list.remove(data);
            newslistBean.setCtime(data);


        }else {
            newslistBean.setCtime("fail");
        }
    }

    @Override
    public void onFail() {
        newslistBean.setDescription("fail");

    }
}
