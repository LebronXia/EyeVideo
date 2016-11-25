package com.example.xiaobozheng.eyevideo.app;

import android.app.Application;

import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerAppComponent;
import com.example.xiaobozheng.eyevideo.injection.module.ApiModule;
import com.example.xiaobozheng.eyevideo.injection.module.AppModule;

/**
 * Created by xiaobozheng on 11/22/2016.
 */

public class EyeVideoApplication extends Application {

    private static EyeVideoApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        initCompoent();
    }

    public void initCompoent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public static EyeVideoApplication getsInstance() {
        return sInstance;
    }

    private AppComponent getAppComponent(){
        return appComponent;
    }
}
