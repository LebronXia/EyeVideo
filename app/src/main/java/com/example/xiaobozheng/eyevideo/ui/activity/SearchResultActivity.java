package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Bundle;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.contract.SearchResultContract;

import java.util.List;

/**
 * Created by xiaobozheng on 12/20/2016.
 */

public class SearchResultActivity extends BaseRVActivity<ItemList> implements SearchResultContract.View{

    @Override
    public int getLayoutId() {
        return R.layout.fragment_searchresult;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void showSearchResult(List<ItemList> SearchResult) {

    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }
}
