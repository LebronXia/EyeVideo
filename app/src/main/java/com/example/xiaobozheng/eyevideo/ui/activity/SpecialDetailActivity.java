package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.common.OnRvItemClickListener;
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
import com.orhanobut.logger.Logger;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.OnClick;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.tabStripEnabled;
import static java.security.AccessController.getContext;

/**
 * Created by Riane on 2016/12/11.
 */

public class SpecialDetailActivity extends BaseActivity implements SpecialDetailContract.View, OnRvItemClickListener{
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
    @Bind(R.id.tv_toolbar_title)
    TextView mTvToolBarTitle;
    @Bind(R.id.nsv_speicaldetail)
    NestedScrollView mNestedScrollView;
    @Bind(R.id.ll_nestedScrollView)
    LinearLayout mLlNestedScrollView;
    @Bind(R.id.toolbar_detail)
    Toolbar mDetailToolbar;

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

    private int height;
    private int width;

    @Inject
    SpeicalDetailPresenter mSpeicalDetailPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_special;
    }

    @Override
    public void initToolBar() {
        mDetailToolbar.setTitle("");
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
        //设置透明栏
        SetTranslanteBar();
        //mCommonToolbar.setVisibility(View.GONE);
        //隐藏标题栏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvToolBarTitle.setText(mItemList.data.title);
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        showDialog();
        mPageAdapter = new GalleryPageAdapter(SpecialDetailActivity.this);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.default_margin));
        //限定预加载的页面个数
        mViewPager.setOffscreenPageLimit(3);
        // mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
       // mViewPager.addOnPageChangeListener(this);
       // mViewPager.setOnClickListener();

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
        mHotAuthorAdapter.setOnItemClickListener(this);
        mHotAuthorRecycleView.setAdapter(mHotAuthorAdapter);
        mHotAuthorRecycleView.setNestedScrollingEnabled(false);

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
               // Logger.d(getHeaderLinearlayout() + "LinearLayout的底部高度");

                if (scrollY <= 0) {   //设置标题的背景颜色
                    mDetailToolbar.setBackgroundColor(Color.argb((int) 0, 255,87,34));
                } else if (scrollY > 0 && scrollY <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) scrollY / height;
                    float alpha = (255 * scale);
                    mTvToolBarTitle.setTextColor(Color.argb((int) alpha, 255,255,255));
                    mDetailToolbar.setBackgroundColor(Color.argb((int) alpha, 255,87,34));
                } else {    //滑动到banner下面设置普通颜色
                    mDetailToolbar.setBackgroundColor(Color.argb((int) 255, 255,87,34));
                }
            }
        });
        mSpeicalDetailPresenter.getSpecialDetailData(mItemList.data.id);
        initListerners();

    }

    private void initListerners() {
        ViewTreeObserver vto = mIvCategoryPic.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTvToolBarTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = mIvCategoryPic.getHeight();

                //scrollView.setScrollViewListener(QQSpeakActivity.this);
            }
        });

    }

    @OnClick(R.id.rv_lately_content)
    public void OnRvLatelyContentClick(){
        startActivity(MainContentActivity.newIntent(mContext, mItemList.data.id, "date"));
    }

    @OnClick(R.id.ll_liked_content)
    public void OnLlLikedCOntentClick(){
        startActivity(MainContentActivity.newIntent(mContext, mItemList.data.id, "shareCount"));
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
    public void onItemClick(View view, int position, Object data) {
        if (data instanceof ItemList){
            startActivity(AuthorActivity.newIntent(SpecialDetailActivity.this, ((ItemList)data).data.header.id));
            Logger.d(((ItemList)data).data.header.id + "ItemList的作者Id");
        }

    }



//    /**
//     * 获取scrollView里的布局高度
//     * @return
//     */
//    private int getHeaderLinearlayout(){
//        int distance = mLlNestedScrollView.getTop();
//        distance = Math.abs(distance);
//        return distance;
//    }
    @Override
    public void showError() {
        dismissDialog();
    }

    @Override
    public void complete() {
        dismissDialog();
    }


}
