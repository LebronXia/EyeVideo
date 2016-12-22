package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;

import butterknife.Bind;

import static android.R.attr.id;
import static android.R.id.tabs;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xiaobozheng on 12/21/2016.
 */

public class AuthorActivity extends BaseActivity{
    public static final String EXTRA_SPECIAL_AUTHOR_ID = "extra_special_author_id";
    @Bind(R.id.tb_author)
    Toolbar mToolbar;
    @Bind(R.id.tl_author)
    TabLayout mAuthorTabLayout;
    @Bind(R.id.vp_author)
    ViewPager mAuthorViewpager;

    private int mAuthorId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authordetail;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

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
    }

    @Override
    public void attachView() {

    }

    public static Intent newIntent(Context context, int authorId){
        Intent intent = new Intent(context, AuthorActivity.class);
        intent.putExtra(EXTRA_SPECIAL_AUTHOR_ID, id);
        return intent;
    }

}
