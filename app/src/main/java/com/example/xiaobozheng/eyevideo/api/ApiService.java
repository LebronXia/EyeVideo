package com.example.xiaobozheng.eyevideo.api;

import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.Discover;
import com.example.xiaobozheng.eyevideo.model.Interesting;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;

import java.util.List;

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

    //获取视频底下回复
    @GET("v1/replies/video")
    Observable<Replies> getReplies(@Query("id") int id);

    //获取更多视频底下回复
    @GET("v1/replies/video?num=10")
    Observable<Replies> getReplies(@Query("id")int id, @Query("lastId") int lastId);

    //获取发现界面的数据
    @GET("v3/discovery")
    Observable<Discover> getDiscover();

    //获取发现专题下的各个小专题
    @GET("v3/categories/detail")
    Observable<SpecialData> getSpecialData(@Query("id") int id);

    //获取最近更新或分享最多的数据
    @GET("v3/videos?num=10")
    Observable<Interesting> getInteresting(
            @Query("start") int start, @Query("categoryId") int categoryId,
            @Query("strategy") String strategy);

    //获取热门搜索词
    @GET("v3/queries/hot")
    Observable<List<String>> getTrendingTag();

//    @GET("v1/search?num=10")
//    Observable<>
}
