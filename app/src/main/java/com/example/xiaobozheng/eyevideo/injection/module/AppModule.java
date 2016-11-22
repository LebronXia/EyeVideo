package com.example.xiaobozheng.eyevideo.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xiaobozheng on 11/22/2016.
 */
@Module
public class AppModule {
    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }
}
