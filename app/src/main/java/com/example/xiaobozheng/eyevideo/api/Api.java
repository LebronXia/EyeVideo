package com.example.xiaobozheng.eyevideo.api;

import com.example.xiaobozheng.eyevideo.app.Constant;
import com.example.xiaobozheng.eyevideo.model.Daily;

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
}
