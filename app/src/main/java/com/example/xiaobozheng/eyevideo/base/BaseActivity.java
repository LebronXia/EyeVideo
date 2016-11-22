package com.example.xiaobozheng.eyevideo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.xiaobozheng.eyevideo.R;

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
        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (mCommonToolbar != null){
            initToolBar();
            setSupportActionBar(mCommonToolbar);
        }
        initDatas();
        initViews();

    }

    public abstract int getLayoutId();

    protected abstract void setupActivityComponent();

    public abstract void initToolBar();

    public abstract void initDatas();

    public abstract void initViews();
}
