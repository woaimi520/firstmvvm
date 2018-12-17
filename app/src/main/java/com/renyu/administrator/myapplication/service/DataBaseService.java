package com.renyu.administrator.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableBoolean;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.renyu.administrator.myapplication.data.WeatherDB;
import com.renyu.administrator.myapplication.util.CityCodeManager;
import com.renyu.administrator.myapplication.util.GetProvider;

import org.litepal.LitePal;

import java.util.Queue;

import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/12/7.
 */

public class DataBaseService extends Service {
    SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();




    }

    @Override
    public IBinder onBind(Intent intent) {
        DataBaseServiceBinder mDataBaseServiceBinder= new DataBaseServiceBinder();

        return mDataBaseServiceBinder;
    }


    public class DataBaseServiceBinder extends Binder implements IUserDataBase {
        @Override
        public boolean addToBp() {

            //开启子线程来写数据库

            Observable.create(new Observable.OnSubscribe<Integer>() {

                @Override
                public void call(Subscriber<? super Integer> subscriber) {

             //具体的访问数据库操作
                    GetProvider getLocalCity = new GetProvider();
                   getLocalCity.getLocalCity(new GetProvider.IResultWetherListener() {
                       @Override
                       public boolean dealData(AMapLocation aMapLocation) {
                           if (aMapLocation != null) {

                               CityCodeManager cityCodeManager = new CityCodeManager();
                               String cityCode =cityCodeManager.cityCodeMap.get(aMapLocation.getCity());
                               System.out.printf("1", cityCode);
                               System.out.printf("1", cityCode);

                               //写入数据库
                               WeatherDB weatherDB = new WeatherDB();
                               weatherDB.setCity(cityCode);
                               weatherDB.save();

                             String city =LitePal.find(WeatherDB.class, 1).getCity();

                               System.out.printf("1", city);

                               return true;
                           }
                           return false;
                       }
                   });



                }
            }).subscribeOn(Schedulers.newThread())
              .subscribe(new Subscriber<Integer>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable throwable) {

                  }

                  @Override
                  public void onNext(Integer integer) {

                      //接收通知 弹出toast


                  }
              });






            return false;
        }

        @Override
        public boolean getoBp() {
            Log.i("11", "log");
            return false;
        }
    }




     public interface IUserDataBase{
         public boolean addToBp();
         public boolean getoBp();
     }

}
