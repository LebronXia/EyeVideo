package com.example.xiaobozheng.eyevideo.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Switch;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.ui.adapter.ListRecyclerAdapter;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.FindFragment;
import com.example.xiaobozheng.eyevideo.ui.support.ScaleDownShowBehavior;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager linearLayoutManager;
    private List<String> list;
    private BottomSheetBehavior mBottomSheetBehavior;

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
        // recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

//        list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add("我是第" + i + "个");
//        }


    }

    @Override
    public void initViews() {
//        ScaleDownShowBehavior scaleDownShowFab = ScaleDownShowBehavior.from(mFloatingActionButton);
//        scaleDownShowFab.setOnStateChangedListener(onStateChangedListener);
//        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
//        recyclerView.setHasFixedSize(true);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
//        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle sacedInstanceState){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
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

    }

    /**
     * 切换
     * @param position
     */
    private void SwitchTo(int position){

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
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


}
