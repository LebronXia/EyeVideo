package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.CategoryInfo;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;
import com.example.xiaobozheng.eyevideo.ui.adapter.GalleryPageAdapter;
import com.example.xiaobozheng.eyevideo.ui.adapter.HotAuthorAdapter;
import com.example.xiaobozheng.eyevideo.ui.adapter.HotLikedAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialDetailContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SpeicalDetailPresenter;
import com.example.xiaobozheng.eyevideo.ui.support.ZoomOutPageTransformer;
import com.example.xiaobozheng.eyevideo.util.ScreenUtil;
import com.example.xiaobozheng.eyevideo.widget.RatioImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;

import static android.R.attr.id;
import static android.R.attr.tabStripEnabled;
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
    @Bind(R.id.rv_hotlikedcontent)
    RecyclerView mHotLikedRecyclerView;
    @Bind(R.id.rv_hotauthorcontent)
    RecyclerView mHotAuthorRecycleView;
    @Bind(R.id.tv_category_title)
    TextView mTvCategoryTitle;
    @Bind(R.id.tv_category_des)
    TextView mTvCategoryDes;
    @Bind(R.id.iv_category_pic)
    RatioImageView mIvCategoryPic;
    private ItemList mItemList;
    private GalleryPageAdapter mPageAdapter;
    //头部图片
    CategoryInfo mCategoryInfo = new CategoryInfo();
    //最近更新内容
    private List<ItemList> mLatelyItemLists = new ArrayList<ItemList>();
    //最受欢迎内容
    private List<ItemList> mLikedItemLists = new ArrayList<ItemList>();
    //最热门作者
    private List<ItemList> mHotAuthorLists = new ArrayList<>();
    private HotLikedAdapter mHotLikedAdapter;
    private HotAuthorAdapter mHotAuthorAdapter;

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

        mPageAdapter = new GalleryPageAdapter(SpecialDetailActivity.this);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.default_margin));
        //限定预加载的页面个数
        mViewPager.setOffscreenPageLimit(3);
        // mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
       // mViewPager.addOnPageChangeListener(this);

        //setHasFixedSize()方法用来使RecyclerView保持固定的大小，该信息被用于自身的优化
        mHotLikedRecyclerView.setHasFixedSize(true);
        mHotLikedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHotLikedAdapter = new HotLikedAdapter(mContext, mLikedItemLists);
        mHotLikedRecyclerView.setAdapter(mHotLikedAdapter);
       // 外层的NestedScrollView就无法滚动了,加了这句把滑动事件给了外层
        mHotLikedRecyclerView.setNestedScrollingEnabled(false);
        mHotAuthorRecycleView.setHasFixedSize(true);
        mHotAuthorRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mHotAuthorAdapter = new HotAuthorAdapter(mContext, mHotAuthorLists);
        mHotAuthorRecycleView.setAdapter(mHotAuthorAdapter);
        mHotAuthorRecycleView.setNestedScrollingEnabled(false);
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
        mCategoryInfo = specialData.categoryInfo;
        mLatelyItemLists.addAll(specialData.sectionList.get(0).itemList.get(0).data.itemList);
        mLikedItemLists.addAll(specialData.sectionList.get(1).itemList);
        mHotAuthorLists.addAll(specialData.sectionList.get(2).itemList);
        mPageAdapter.setItemLists(mLatelyItemLists);
        mViewPager.setAdapter(mPageAdapter);
        mHotLikedAdapter.notifyDataSetChanged();
        mHotAuthorAdapter.notifyDataSetChanged();
        Glide.with(mContext)
                .load(mCategoryInfo.getHeaderImage())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mIvCategoryPic);

        mTvCategoryTitle.setText(mCategoryInfo.getName());
        mTvCategoryDes.setText(mCategoryInfo.getDescription());

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
