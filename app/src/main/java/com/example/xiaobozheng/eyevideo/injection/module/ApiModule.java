package com.example.xiaobozheng.eyevideo.injection.module;


import com.example.xiaobozheng.eyevideo.api.Api;

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
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Provides
    protected Api providerEyeVideoService(OkHttpClient okHttpClient){
        return Api.getInstance(okHttpClient);
    }
}
