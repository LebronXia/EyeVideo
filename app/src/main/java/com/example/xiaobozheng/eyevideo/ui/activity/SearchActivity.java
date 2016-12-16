package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.ui.contract.SearchContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SearchPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by xiaobozheng on 12/16/2016.
 */

public class SearchActivity extends BaseActivity implements SearchContract.View{

    @Bind(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @Bind(R.id.fl_toolbar_search)
    FrameLayout mFlToolBarSearch;

    @Inject
    SearchPresenter mSearchPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMianVideoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    public void attachView() {
        if (mSearchPresenter != null){
            mSearchPresenter.attachView(this);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //设置透明栏
        SetTranslanteBar();
        //隐藏标题栏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvToolbarTitle.setVisibility(View.GONE);
        mFlToolBarSearch.setVisibility(View.VISIBLE);

        mSearchPresenter.getTrendingTags();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void showTrendingTags(List<String> TrendingTags) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }
}
