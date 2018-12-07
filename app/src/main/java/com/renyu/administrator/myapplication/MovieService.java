package com.renyu.administrator.myapplication;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Administrator on 2018/11/24.
 */

public interface MovieService {


        @GET("api/weather/city/{city}")
                //index会拼接到BASE_URL中，此时这个URL已经拼接完毕
                //http://t.weather.sojson.com/api/weather/city/101030100
        Call<WeatherResp> cityNameQueryWeather(@Path("city") String city);
}
