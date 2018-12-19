package com.renyu.administrator.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableBoolean;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.renyu.administrator.myapplication.activity.MainActivity;
import com.renyu.administrator.myapplication.activity.WelcomeActivity;
import com.renyu.administrator.myapplication.data.WeatherDB;
import com.renyu.administrator.myapplication.util.CityCodeManager;
import com.renyu.administrator.myapplication.util.GetProvider;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

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
                public void call(final Subscriber<? super Integer> subscriber) {

             //具体的访问数据库操作
                    GetProvider getLocalCity = new GetProvider();
                   getLocalCity.getLocalCity(new GetProvider.IResultWetherListener() {
                       @Override
                       public boolean dealData(AMapLocation aMapLocation) {
                           if (aMapLocation != null) {

                               CityCodeManager cityCodeManager = new CityCodeManager();
                               String cityCode =cityCodeManager.cityCodeMap.get(aMapLocation.getCity());


                               //写入数据库

                               SharedPreferences sp = getSharedPreferences("citycode", 0);
                               SharedPreferences.Editor editor = sp.edit();
                               editor.putString("cityCode", cityCode);
                               editor.commit();


                                       subscriber.onNext(9);//这里可以添加网络访问
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

                      // 打开main activity
                      Intent intent = new Intent(DataBaseService.this,MainActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//不许加 不然跳转不了
                      startActivity(intent);
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
