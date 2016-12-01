package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.FindFragment;
import com.example.xiaobozheng.eyevideo.ui.support.ScaleDownShowBehavior;
import com.example.xiaobozheng.eyevideo.util.AnimatorUtil;
import com.example.xiaobozheng.eyevideo.util.LogUtils;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @Bind(R.id.tab_layout)
    BottomBar mBottomBar;
    private LinearLayoutManager linearLayoutManager;
    private List<String> list;
    private BottomSheetBehavior mBottomSheetBehavior;
    private ScaleDownShowBehavior scaleDownShowFab;
    private int currentTabPosition;


    private ChoiceFragment mChoiceFragment;
    private FindFragment mFindFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent() {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        //根据悬浮按钮来隐藏底部栏
        scaleDownShowFab = ScaleDownShowBehavior.from(mFloatingActionButton);
        scaleDownShowFab.setOnStateChangedListener(onStateChangedListener);
          mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        //初始化Fragment
        initFragment(savedInstanceState);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_choice){
                    SwitchTo(0);
                } else if (tabId == R.id.tab_find){
                    SwitchTo(1);
                }
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
            mFindFragment = (FindFragment) getSupportFragmentManager().findFragmentByTag("findFragment");

        } else {
            mChoiceFragment = new ChoiceFragment();
            mFindFragment = new FindFragment();

            transaction.add(R.id.frame_content, mChoiceFragment, "choiceFragment");
            transaction.add(R.id.frame_content, mFindFragment, "findFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);

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
                transaction.show(mChoiceFragment);
                transaction.commitAllowingStateLoss();
                break;
            //发现
            case 1:
                currentTabPosition = 1;
                transaction.hide(mChoiceFragment);
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

}
