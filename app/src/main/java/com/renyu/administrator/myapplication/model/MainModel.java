package com.renyu.administrator.myapplication.model;

import android.util.Log;

import com.renyu.administrator.myapplication.data.CityIdConstance;
import com.renyu.administrator.myapplication.util.GetProvider;
import com.renyu.administrator.myapplication.util.WeatherServiceInterface;
import com.renyu.administrator.myapplication.util.NewslistBean;
import com.renyu.administrator.myapplication.util.WeatherResp;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 新闻网络请求model
 * Created by Administrator on 2018/11/7.
 */

public class MainModel {
    NewslistBean bean;
    Reasult result;
    String cityCode;
    ArrayList<String> datalist = new ArrayList<String>();
    String dataUril="";
    final static String TAG = "MainModel";
    //http://t.weather.sojson.com/api/weather/city/101030100
    final static String BASE_URL = "http://t.weather.sojson.com";
    private static String city = "101030100";
    WeatherResp beanResp;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


    WeatherServiceInterface mApi = retrofit.create(WeatherServiceInterface.class);

    public MainModel(NewslistBean bean, Reasult result,String cityCode) {
        this.bean = bean;
        this.result = result;
        this.cityCode = cityCode;
    }

    public void getData(){




        mApi.cityNameQueryWeather(cityCode)
          .subscribeOn(AndroidSchedulers.mainThread())
          .subscribe(
           new Subscriber<WeatherResp>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(WeatherResp weatherResp) {

                beanResp = weatherResp;
                        String cityInfo = "CITY:" + beanResp.getCityInfo().getCity() + "\n"+"time:" + beanResp.getCityInfo().getUpdateTime();
                        String wetherInfo = "温度:" + beanResp.getData().getWendu() + "\n" +
                                "湿度:" + beanResp.getData().getShidu() + "\n" +
                                "PM2.5:" + beanResp.getData().getPm25();
                        //String jingweidu = new GetProvider().getLocalCity();
                        String wether = cityInfo +"\n"+ wetherInfo+"\n" ;
                                datalist.add(wether);


                    //  bean.setCtime(String.valueOf(data));
                    result.onSuccess(datalist);

            }
        });




    }

   public interface Reasult{

        void onSuccess(ArrayList<String> list);

        void onFail();

    }
}
