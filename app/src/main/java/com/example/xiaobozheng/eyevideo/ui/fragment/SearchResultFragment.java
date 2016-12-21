package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.os.Bundle;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.ui.activity.SearchActivity;
import com.orhanobut.logger.Logger;

/**
 * Created by xiaobozheng on 12/21/2016.
 */

public class SearchResultFragment extends BaseFragment{
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_searchresult;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initDatas() {
        String keyword = getArguments().getString(SearchActivity.KEYWORD);

        Logger.d(keyword);
    }

    @Override
    public void initView() {

    }

    @Override
    public void attachView() {

    }
}
