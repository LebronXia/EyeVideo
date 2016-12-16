package com.example.xiaobozheng.eyevideo.api;

import com.example.xiaobozheng.eyevideo.app.Constant;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.Discover;
import com.example.xiaobozheng.eyevideo.model.Interesting;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 管理Api
 * Created by xiaobozheng on 11/25/2016.
 */

public class Api {
    public static Api instance;
    private ApiService mApiService;

    public Api(OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create())     //添加Gson适配器
                .build();

        mApiService = retrofit.create(ApiService.class);
    }

    public static Api getInstance(OkHttpClient okHttpClient){
        if (instance == null){
            instance = new Api(okHttpClient);
        }
        return instance;
    }

    /**
     * 获取日常视频
     * @param date
     * @return
     */
    public Observable<Daily> getDaily(long date){
        return mApiService.getDaily(date);
    }

    /**
     * 获取当天的视频
     * @return
     */
    public Observable<Daily> getDaily(){
        return mApiService.getDaily();
    }

    /**
     * 获取视频回复
     * @param id
     * @return
     */
    public Observable<Replies> getReplies(int id){
        return mApiService.getReplies(id);
    }

    /**
     * 获取视频更多回复
     * @param id
     * @return
     */
    public Observable<Replies> getReplies(int id, int lastId){
        return mApiService.getReplies(id, lastId);
    }

    /**
     * 获取发现界面的数据
     * @return
     */
    public Observable<Discover> getDiscover(){
        return mApiService.getDiscover();
    }

    /**
     * 获取发现专题下的各个小专题
     * @param id
     * @return
     */
    public Observable<SpecialData> getSpecialData(int id){
        return mApiService.getSpecialData(id);
    }

    /**
     * 获取每个专题的内容
     * @param start
     * @param category
     * @param strategy
     * @return
     */
    public Observable<Interesting> getInteresting(int start, int category, String strategy){
        return mApiService.getInteresting(start, category, strategy);
    }

    /**
     * 获取热门搜索词
     * @return
     */
    public Observable<List<String>> getTrendingTag(){
        return mApiService.getTrendingTag();
    }
}
