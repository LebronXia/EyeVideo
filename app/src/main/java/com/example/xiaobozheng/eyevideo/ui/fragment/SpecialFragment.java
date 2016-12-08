package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.adapter.SpecialItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SpecialPresenter;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.EasyRecyclerView;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.example.xiaobozheng.eyevideo.widget.banner.Banner;
import com.sivin.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 专题界面类是开眼的发现
 * Created by xiaobozheng on 12/6/2016.
 */

public class SpecialFragment extends BaseFragment implements SpecialContract.View{

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    //http://www.jianshu.com/p/a5655934f622
    private Banner mBanner;
    private View headerView;
    @Inject
    SpecialPresenter mSpecialPresenter;
    private SpecialItemAdapter mSpecialItemAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMianVideoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initView() {
        showDialog();
        headerView = LayoutInflater.from(mContext).inflate(R.layout.header_banner, null);
        mBanner = ButterKnife.findById(headerView, R.id.banner);
        mSpecialItemAdapter = new SpecialItemAdapter(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        //给九宫格添加头部的方法
        gridLayoutManager.setSpanSizeLookup(mSpecialItemAdapter.obtainGridSpanSizeLookUp(gridLayoutManager.getSpanCount()));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemDecoration(ContextCompat.getColor(mContext, R.color.common_divider_narrow), 8, 4, 4);
        mRecyclerView.setErrorView(R.layout.common_error_view);
        mRecyclerView.setAdapterWithProgress(mSpecialItemAdapter);
        //在mRecyclerView位于顶部的时候，让banner重新轮播滑动
        mRecyclerView.setStartBannerOnClick(new EasyRecyclerView.StartBannerOnClick() {
            @Override
            public void startBanner() {
                mBanner.goScroll();
            }
        });
        mRecyclerView.setIsSpecialFragment(true);
        mSpecialPresenter.getDiscoverData();
        mSpecialItemAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return headerView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    private void initBanner(List<ItemList> itemLists){
        mBanner.setBannerAdapter(new BannerAdapter<ItemList>(itemLists) {
            @Override
            protected void bindTips(TextView tv, ItemList itemList) {
                tv.setText("");
            }

            @Override
            public void bindImage(ImageView imageView, ItemList itemList) {
                Glide.with(mContext)
                        .load(itemList.data.image)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .into(imageView);
            }
        });

    }

    @Override
    public void attachView() {
        if (mSpecialPresenter != null){
            mSpecialPresenter.attachView(this);
        }
    }

    @Override
    public void showDiscoverData(List<ItemList> itemLists) {
        List<ItemList> mTagItemLists = new ArrayList<>();
        if (itemLists != null && itemLists.size() > 0){
            mSpecialItemAdapter.clear();

            for (int i = 4; i < itemLists.size(); i++){
                mTagItemLists.add(itemLists.get(i));
            }
            mSpecialItemAdapter.addAll(mTagItemLists);
            //广告栏的list数组
            ItemList mBannerList = itemLists.get(0);
            initBanner(mBannerList.data.itemList);
            mBanner.notifiDataHasChanged();
        }
    }

    @Override
    public void showError() {
        dismissDialog();
    }

    @Override
    public void complete() {
        dismissDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSpecialPresenter != null)
            mSpecialPresenter.detachView();
    }
}
