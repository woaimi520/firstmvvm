package com.renyu.administrator.myapplication;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2018/11/24.
 */

public interface MovieService {

        //获取豆瓣Top250 榜单
        @GET("top250")
        Call<MovieSubject> getTop250(@Query("start") int start, @Query("count")int count);

}
