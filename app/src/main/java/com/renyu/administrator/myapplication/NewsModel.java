package com.renyu.administrator.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

import static android.content.ContentValues.TAG;

/**
 * 新闻网络请求model
 * Created by Administrator on 2018/11/7.
 */

public class NewsModel {
    NewslistBean bean;
    Reasult result;
    ArrayList<String> datalist = new ArrayList<String>();
    String dataUril="";
    final static String TAG = "NewsModel";

    public NewsModel(NewslistBean bean,Reasult result) {
        this.bean = bean;
        this.result = result;
    }

    public void getData(){

        Observable.create(new Observable.OnSubscribe<Integer>() {


            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(9);


            }
        }).subscribe(new Subscriber<Integer>() {



            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "对Next事件"+ integer +"作出响应"  );
                double data = integer;
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
        });




    }

    interface Reasult{

        void onSuccess(ArrayList<String> list);

        void onFail();

    }
}
