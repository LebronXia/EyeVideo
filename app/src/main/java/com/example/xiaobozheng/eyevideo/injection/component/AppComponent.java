package com.example.xiaobozheng.eyevideo.injection.component;

import android.content.Context;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.injection.module.ApiModule;
import com.example.xiaobozheng.eyevideo.injection.module.AppModule;

import dagger.Component;

/**
 * Created by xiaobozheng on 11/25/2016.
 */
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    Context getContext();

    Api getApi();
}
