package com.renyu.administrator.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 新闻网络请求model
 * Created by Administrator on 2018/11/7.
 */

public class NewsModel {
    NewslistBean bean;
    Reasult result;
    ArrayList<String> datalist = new ArrayList<String>();
    String dataUril="";


    public NewsModel(NewslistBean bean,Reasult result) {
        this.bean = bean;
        this.result = result;
    }

    public void getData(){

        double data = Math.random() * 9;
       if(data>5) {
           datalist.add(String.valueOf(data));
           datalist.add(dataUril);
         //  bean.setCtime(String.valueOf(data));
           result.onSuccess(datalist);
       }else{
         //  bean.setCtime("fail");
           result.onFail();
       }

    }

    interface Reasult{

        void onSuccess(ArrayList<String> list);

        void onFail();

    }
}
