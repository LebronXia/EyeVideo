package com.example.xiaobozheng.eyevideo.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.ui.contract.MovieDetailContract;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.presenter.MovieDetailPresenter;
import com.example.xiaobozheng.eyevideo.util.LogUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by xiaobozheng on 12/1/2016.
 */

public class MovieDetailActivity extends BaseRVActivity<ItemList> implements MovieDetailContract.View{

    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard jcVideoPlayerStandard;

    @Inject
    MovieDetailPresenter mMovieDetailPresenter;

    private ItemList item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_moviedetail;
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
        item = getIntent().getParcelableExtra(ChoiceFragment.EXTRA_ITEM);
        mMovieDetailPresenter.getMovieDetailReplies(item.data.id);
        boolean setUp = jcVideoPlayerStandard.setUp(item.data.playUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST,
                item.data.title);
        if (setUp) {
            //视频图的载入
            Picasso.with(this)
                    .load(item.data.cover.detail)
                    .into(jcVideoPlayerStandard.thumbImageView);
        }
        jcVideoPlayerStandard.looping = true;

    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }

    @OnClick(R.id.videoplayer)
    public void videoClick(){
        LogUtils.d("你好");
        jcVideoPlayerStandard.startWindowTiny();
    }

    @Override
    public void attachView() {
        if (mMovieDetailPresenter != null)
            mMovieDetailPresenter.attachView(this);
    }

    @Override
    public void showMovieDetailRplies(List<Replies.ReplyListBean> replyList) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMovieDetailPresenter != null)
            mMovieDetailPresenter.detachView();
    }
}
