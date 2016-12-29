package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.model.TabEntity;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.FindFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.SpecialFragment;
import com.example.xiaobozheng.eyevideo.ui.support.ScaleDownShowBehavior;
import com.example.xiaobozheng.eyevideo.util.AnimatorUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @Bind(R.id.tab_layout)
    CommonTabLayout mCommonTabLayout;
    private LinearLayoutManager linearLayoutManager;
    private List<String> list;
    private BottomSheetBehavior mBottomSheetBehavior;
    private ScaleDownShowBehavior scaleDownShowFab;
    private int currentTabPosition;

    private ChoiceFragment mChoiceFragment;
    private SpecialFragment mSpecialFragment;
    private FindFragment mFindFragment;

    private String[] mTitles = {"精选", "专题", "发现"};
    private int[] mIconUnselectIds = {R.mipmap.found, R.mipmap.special, R.mipmap.fancy};
    private int[] mIconSeclectIds = {R.mipmap.found_select, R.mipmap.special_select, R.mipmap.fancy_select};

    //底部的高度
    private static int tablayoutHeight;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("开眼视频");
        mCommonToolbar.setTitleTextColor(getResources().getColor(R.color.background_light));
    }

    @Override
    public void initDatas() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //设置透明栏
        SetTranslanteBar();
        //根据悬浮按钮来隐藏底部栏
        scaleDownShowFab = ScaleDownShowBehavior.from(mFloatingActionButton);
       scaleDownShowFab.setOnStateChangedListener(onStateChangedListener);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        //初始化菜单
        initTab();
        //初始化Fragment
        initFragment(savedInstanceState);

    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++){
            mTabEntities.add(new TabEntity(mTitles[i], mIconSeclectIds[i], mIconUnselectIds[i]));
        }
        mCommonTabLayout.setTabData(mTabEntities);

        mCommonTabLayout.setOnTabSelectListener(new com.flyco.tablayout.listener.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle sacedInstanceState){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        currentTabPosition = 0;
        if (sacedInstanceState != null){
            mChoiceFragment = (ChoiceFragment) getSupportFragmentManager().findFragmentByTag("choiceFragment");
            mSpecialFragment = (SpecialFragment) getSupportFragmentManager().findFragmentByTag("specialFragment");
            mFindFragment = (FindFragment) getSupportFragmentManager().findFragmentByTag("findFragment");

        } else {
            mChoiceFragment = new ChoiceFragment();
            mSpecialFragment = new SpecialFragment();
            mFindFragment = new FindFragment();

            transaction.add(R.id.frame_content, mChoiceFragment, "choiceFragment");
            transaction.add(R.id.frame_content, mSpecialFragment, "specialFragment");
            transaction.add(R.id.frame_content, mFindFragment, "findFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        mCommonTabLayout.setCurrentTab(currentTabPosition);
    }

    @OnClick(R.id.fab)
    public void setFabOnClick(){
        if (currentTabPosition == 0){
            mChoiceFragment.linearLayoutManager.scrollToPosition(0);
            hideFAB();
        }
    }

    /**
     * 切换
     * @param position
     */
    private void SwitchTo(int position){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position){
            //精选
            case 0:
                currentTabPosition = 0;
                transaction.hide(mFindFragment);
                transaction.hide(mSpecialFragment);
                transaction.show(mChoiceFragment);
                transaction.commitAllowingStateLoss();
                break;
            //专题
            case 1:
                currentTabPosition = 1;
                transaction.hide(mChoiceFragment);
                transaction.hide(mFindFragment);
                transaction.show(mSpecialFragment);
                transaction.commitAllowingStateLoss();
                break;
            //发现
            case 2:
                currentTabPosition = 2;
                transaction.hide(mChoiceFragment);
                transaction.hide(mSpecialFragment);
                transaction.show(mFindFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }

    private ScaleDownShowBehavior.OnStateChangedListener onStateChangedListener = new ScaleDownShowBehavior.OnStateChangedListener() {
        @Override
        public void onChanged(boolean isShow) {
            mBottomSheetBehavior.setState(isShow ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

    private boolean initialize = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!initialize) {
            initialize = true;

            hideFAB();
           mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }
    }

    private void hideFAB() {
        mFloatingActionButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorUtil.scaleHide(mFloatingActionButton, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                    }
                    @Override
                    public void onAnimationEnd(View view) {
                        mFloatingActionButton.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationCancel(View view) {
                    }
                });
            }
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search){
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
