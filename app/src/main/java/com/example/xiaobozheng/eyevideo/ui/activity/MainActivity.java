package com.example.xiaobozheng.eyevideo.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.ui.adapter.ListRecyclerAdapter;
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
    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<String> list;
    private BottomSheetBehavior mBottomSheetBehavior;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

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

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }


    }

    @Override
    public void initViews() {
        ScaleDownShowBehavior scaleDownShowFab = ScaleDownShowBehavior.from(mFloatingActionButton);
        scaleDownShowFab.setOnStateChangedListener(onStateChangedListener);
        mBottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.tab_layout));
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
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
