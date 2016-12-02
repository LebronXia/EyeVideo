package com.example.xiaobozheng.eyevideo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.app.EyeVideoApplication;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by xiaobozheng on 11/22/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public Toolbar mCommonToolbar;

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mContext = this;
        ButterKnife.bind(this);
        setupActivityComponent(EyeVideoApplication.getsInstance().getAppComponent());
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null){
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }

        initDatas();
        attachView();
        initViews(savedInstanceState);

    }

    public abstract int getLayoutId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void initToolBar();

    public abstract void initDatas();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void attachView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
       // dismissDialog();
    }
}
