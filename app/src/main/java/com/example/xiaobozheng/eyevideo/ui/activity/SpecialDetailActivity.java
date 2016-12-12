package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;
import com.example.xiaobozheng.eyevideo.ui.adapter.GalleryPageAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialDetailContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SpeicalDetailPresenter;
import com.example.xiaobozheng.eyevideo.ui.support.ZoomOutPageTransformer;
import com.example.xiaobozheng.eyevideo.util.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static android.R.attr.id;
import static java.security.AccessController.getContext;

/**
 * Created by Riane on 2016/12/11.
 */

public class SpecialDetailActivity extends BaseActivity implements SpecialDetailContract.View, ViewPager.OnPageChangeListener{
    public static final String EXTRA_SPECIAL_ITEMLIST = "extra_special_itemlist";

    @Bind(R.id.vp_update_content)
    ViewPager mViewPager;
    @Bind(R.id.ll_viewpager)
    LinearLayout mLlViewPager;
    private ItemList mItemList;
    private GalleryPageAdapter mPageAdapter;
    //最近更新内容
    private List<ItemList> mLatelyItemLists = new ArrayList<ItemList>();
    //最受欢迎内容
    private List<ItemList> mLikedItemLists = new ArrayList<ItemList>();

    @Inject
    SpeicalDetailPresenter mSpeicalDetailPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special;
    }

    @Override
    public void initToolBar() {

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
        mItemList = getIntent().getParcelableExtra(EXTRA_SPECIAL_ITEMLIST);

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
//        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
//        int width = ScreenUtil.getScreenWidth(SpecialDetailActivity.this) * (7/8);
//        params.width = width;
//        mViewPager.setLayoutParams(params);
        mPageAdapter = new GalleryPageAdapter(SpecialDetailActivity.this, mLatelyItemLists);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.addOnPageChangeListener(this);
        mSpeicalDetailPresenter.getSpecialDetailData(mItemList.data.id);
    }

    @Override
    public void attachView() {
        if (mSpeicalDetailPresenter != null){
            mSpeicalDetailPresenter.attachView(this);
        }
    }

    @Override
    public void showSpecialDetailData(SpecialData specialData) {
        if (specialData == null) return;
        mLatelyItemLists.addAll(specialData.sectionList.get(0).itemList.get(0).data.itemList);
        mLikedItemLists.addAll(specialData.sectionList.get(1).itemList);
        mPageAdapter.setItemLists(mLatelyItemLists);
        mViewPager.setAdapter(mPageAdapter);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            mLlViewPager.invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
