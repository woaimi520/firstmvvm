package com.renyu.administrator.myapplication.model;

import android.util.Log;

import com.renyu.administrator.myapplication.data.CityIdConstance;
import com.renyu.administrator.myapplication.util.GetProvider;
import com.renyu.administrator.myapplication.util.MovieService;
import com.renyu.administrator.myapplication.util.NewslistBean;
import com.renyu.administrator.myapplication.util.WeatherResp;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

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
    //http://t.weather.sojson.com/api/weather/city/101030100
    final static String BASE_URL = "http://t.weather.sojson.com";
    private static String city = "101030100";
    WeatherResp beanResp;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    MovieService mApi = retrofit.create(MovieService.class);

    public NewsModel(NewslistBean bean,Reasult result) {
        this.bean = bean;
        this.result = result;
    }

    public void getData(){

        Observable.create(new Observable.OnSubscribe<Integer>() {


            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                //调用方法得到一个Call
                Call<WeatherResp> call = mApi.cityNameQueryWeather(CityIdConstance.CHONGQIONG);

                call.enqueue(new Callback<WeatherResp>() {
                    @Override
                    public void onResponse(Response<WeatherResp> response, Retrofit retrofit) {

                        if(response.isSuccess()){

                            try {

                                //这里用bean来接收进过GSON 转化后的数据
                                 beanResp = response.body();
                                String wendu =beanResp.getData().getWendu();
                                Log.i("wendu", wendu);
                            } catch (Exception e) {

                            }

                        }
                     else {
                            Log.i("response", "response fail");
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("response", "response onFailure");
                    }
                });
                subscriber.onNext(9);//这里可以添加网络访问

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
                Log.d(TAG, "对Next事件"+ integer +"作出响应"  );
                Integer data = integer;
                if(data>5) {
                    if(beanResp!=null){

                        String cityInfo = "CITY:" + beanResp.getCityInfo().getCity() + "\n"+"time:" + beanResp.getCityInfo().getUpdateTime();
                        String wetherInfo = "温度:" + beanResp.getData().getWendu() + "\n" +
                                "湿度:" + beanResp.getData().getShidu() + "\n" +
                                "PM2.5:" + beanResp.getData().getPm25();
                        String jingweidu = new GetProvider().getLocalCity();
                        String wether = cityInfo +"\n"+ wetherInfo+"\n" +jingweidu;
                                datalist.add(wether);

                    }
                    //  bean.setCtime(String.valueOf(data));
                    result.onSuccess(datalist);
                }else{
                    //  bean.setCtime("fail");
                    result.onFail();
                }
            }
        });




    }

   public interface Reasult{

        void onSuccess(ArrayList<String> list);

        void onFail();

    }
}
