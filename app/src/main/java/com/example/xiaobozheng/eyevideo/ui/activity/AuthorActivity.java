package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.AuthorDetailData;
import com.example.xiaobozheng.eyevideo.ui.adapter.AuthorTypeItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.AuthorDetailContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.AuthorDetailPresenter;
import com.yuyh.easyadapter.glide.GlideCircleTransform;

import javax.inject.Inject;
import butterknife.Bind;

import static android.R.attr.id;

/**
 * Created by xiaobozheng on 12/21/2016.
 */

public class AuthorActivity extends BaseActivity implements AuthorDetailContract.View{
    public static final String EXTRA_SPECIAL_AUTHOR_ID = "extra_special_author_id";

    @Bind(R.id.tb_author)
    Toolbar mToolbar;
    @Bind(R.id.iv_author_avatar)
    ImageView mIvAuthorAvatar;
    @Bind(R.id.tv_author_name)
    TextView mTvAuthorName;
    @Bind(R.id.tv_author_detail)
    TextView mTvAuthorDetail;
    @Bind(R.id.tv_author_destription)
    TextView mTvAuthorDestription;
    @Bind(R.id.tl_author)
    TabLayout mAuthorTabLayout;
    @Bind(R.id.vp_author)
    ViewPager mAuthorViewpager;
    private AuthorTypeItemAdapter mAdapter;
    //private ArrayList<Fragment> fragments = new ArrayList<>(2);

    private int mAuthorId;
    private AuthorDetailData.PgcInfo mPgcInfo;

    @Inject
    AuthorDetailPresenter mAuthorDetailPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authordetail;
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
        mAuthorId = getIntent().getIntExtra(EXTRA_SPECIAL_AUTHOR_ID, 0);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //设置透明栏
        SetTranslanteBar();
        if (mToolbar != null){
            initToolBar();
            setSupportActionBar(mToolbar);
        }
        //隐藏标题栏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuthorTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mAuthorTabLayout.addTab(mAuthorTabLayout.newTab().setText("Tab1"));
        mAuthorTabLayout.addTab(mAuthorTabLayout.newTab().setText("Tab2"));
        mAuthorTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mAuthorViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout.TabLayoutOnPageChangeListener listener = new TabLayout.TabLayoutOnPageChangeListener(mAuthorTabLayout);
        mAuthorViewpager.addOnPageChangeListener(listener);

        //滑动的Adapter适配器
        mAdapter = new AuthorTypeItemAdapter(mContext, getSupportFragmentManager(), mAuthorId);
        mAuthorViewpager.setAdapter(mAdapter);
        mAuthorTabLayout.setupWithViewPager(mAuthorViewpager);

        //通过请求预先获得作者列表的信息，所以参数写死
        mAuthorDetailPresenter.getAuthorDetaukData(0, mAuthorId, "date", false);

    }

    @Override
    public void attachView() {
        if (mAuthorDetailPresenter != null){
            mAuthorDetailPresenter.attachView(this);
        }
    }

    public static Intent newIntent(Context context, int authorId){
        Intent intent = new Intent(context, AuthorActivity.class);
        intent.putExtra(EXTRA_SPECIAL_AUTHOR_ID, authorId);
        return intent;
    }


    @Override
    public void showAuthorDetailData(AuthorDetailData authorDetailData, boolean isRefresh) {
        if (authorDetailData == null) return;
        mPgcInfo = authorDetailData.pgcInfo;

        Glide.with(mContext)
                .load(mPgcInfo.icon)
                .placeholder(R.mipmap.avatar_default)
                .transform(new GlideCircleTransform(AuthorActivity.this))
                .into(mIvAuthorAvatar);
        mTvAuthorName.setText(authorDetailData.pgcInfo.name);
        mTvAuthorDetail.setText(authorDetailData.pgcInfo.brief);
        mTvAuthorDestription.setText(authorDetailData.pgcInfo.description);

    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

}
