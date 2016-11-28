package com.example.xiaobozheng.eyevideo.injection.module;


import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.app.Constant;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


/**
 * Created by xiaobozheng on 11/25/2016.
 */
@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        return  httpClientBuilder.build();
    }

    @Provides
    protected Api providerEyeVideoService(OkHttpClient okHttpClient){
        return Api.getInstance(okHttpClient);
    }
}
