package com.renyu.administrator.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


/**
 * Created by Administrator on 2018/12/7.
 */

public class DataBaseService extends Service {

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
            Log.i("11", "log");
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
