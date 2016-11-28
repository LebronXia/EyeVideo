package com.example.xiaobozheng.eyevideo.api;

import com.example.xiaobozheng.eyevideo.model.Daily;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 统一管理API
 * Created by xiaobozheng on 11/24/2016.
 */

public interface ApiService {

    //获取日常视频
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily(@Query("date") long date);

    //获取今天日常视频
    @GET("v2/feed?num=2")
    Observable<Daily> getDaily();
}
