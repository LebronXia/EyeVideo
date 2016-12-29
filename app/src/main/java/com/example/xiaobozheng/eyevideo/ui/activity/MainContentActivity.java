package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.adapter.InterestingItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.MainContentContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.MainContentPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 最新更新内容和最受欢迎内容
 * Created by xiaobozheng on 12/15/2016.
 */
public class MainContentActivity extends BaseRVActivity<ItemList> implements MainContentContract.View {

    private static final String EXTRA_Id = "maincontent_extra_id";
    private static final String EXTRA_TYPE = "maincontent_extra_type";
    private List<ItemList> mInterestingLists = new ArrayList<>();
    private int categoryId;
    private String strategy;
    private int start = 0;
    @Inject
    MainContentPresenter mContentPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_maincontent;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMianVideoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {
        categoryId = getIntent().getIntExtra(EXTRA_Id , 0);
        strategy = getIntent().getStringExtra(EXTRA_TYPE);

        Logger.d(strategy);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //设置透明栏
        SetTranslanteBar();
        initAdapter(InterestingItemAdapter.class, true, true);
        onRefresh();
    }


    @Override
    public void showInterestingData(List<ItemList> interestingLists, boolean isRefresh) {
        if (interestingLists == null || interestingLists.size() < 0) return;
        mInterestingLists.clear();
        if(isRefresh){
            start = 0;
            mAdapter.clear();
        }
        mInterestingLists.addAll(interestingLists);
        mAdapter.addAll(mInterestingLists);
        start += 10;
    }

    @Override
    public void attachView() {
        if (mContentPresenter != null){
            mContentPresenter.attachView(this);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mContentPresenter.getInteresting(start, categoryId, strategy, false);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mContentPresenter.getInteresting(start, categoryId, strategy, true);

    }

    public static Intent newIntent(Context context, int id, String strategy){
        Intent intent = new Intent(context, MainContentActivity.class);
        intent.putExtra(EXTRA_Id, id);
        intent.putExtra(EXTRA_TYPE, strategy);
        return intent;
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

}
