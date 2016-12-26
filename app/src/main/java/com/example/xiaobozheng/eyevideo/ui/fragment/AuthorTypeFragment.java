package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.AuthorDetailData;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.activity.AuthorActivity;
import com.example.xiaobozheng.eyevideo.ui.adapter.InterestingItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.AuthorDetailContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.AuthorDetailPresenter;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static android.R.attr.type;

/**
 * 共用的Fragment
 * Created by xiaobozheng on 12/23/2016.
 */
public class AuthorTypeFragment extends BaseRVFragment<AuthorDetailPresenter, ItemList> implements AuthorDetailContract.View{
    private static final String EXTRA_TYPE = "maincontent_extra_type";

    private List<ItemList> mAuthorList = new ArrayList<>();
    //作者Id
    private int categoryId;
    //Fragment的类型
    private String strategy;
    private int start = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_authortype;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMianVideoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    public static AuthorTypeFragment newInstance(int mAuthorID, String stategy){
        Bundle args = new Bundle();
        args.putInt(AuthorActivity.EXTRA_SPECIAL_AUTHOR_ID, mAuthorID);
        args.putString(EXTRA_TYPE, stategy);
        AuthorTypeFragment fragment = new AuthorTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initDatas() {
        categoryId = getArguments().getInt(AuthorActivity.EXTRA_SPECIAL_AUTHOR_ID);
        strategy = getArguments().getString(EXTRA_TYPE);
    }

    @Override
    public void initView() {
        initAdapter(InterestingItemAdapter.class, false, true);
        onRefresh();
    }

    @Override
    public void showAuthorDetailData(AuthorDetailData authorDetailData, boolean isRefresh) {
        if (authorDetailData == null ) return;
        mAuthorList.clear();
        if (isRefresh){
            mAdapter.clear();
        }

        mAuthorList.addAll(authorDetailData.itemList);
        mAdapter.addAll(mAuthorList);
        start += 10;

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPresenter.getAuthorDetaukData(start, categoryId, strategy, false);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getAuthorDetaukData(start, categoryId, strategy, true);
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
