package com.renyu.administrator.myapplication;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2018/11/24.
 */

public interface MovieService {


        @GET("index")
        //index会拼接到BASE_URL中，此时这个URL已经拼接完毕
        Call<WeatherResp> cityNameQueryWeather(@Query("key") String key,
                                               @Query("cityname") String cityname,
                                               @Query("format") int format);
}
