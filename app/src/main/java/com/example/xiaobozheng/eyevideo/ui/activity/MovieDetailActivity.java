package com.example.xiaobozheng.eyevideo.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.ui.adapter.ReplyItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.MovieDetailContract;
import com.example.xiaobozheng.eyevideo.ui.fragment.ChoiceFragment;
import com.example.xiaobozheng.eyevideo.ui.presenter.MovieDetailPresenter;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.yuyh.easyadapter.glide.GlideCircleTransform;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by xiaobozheng on 12/1/2016.
 */
public class MovieDetailActivity extends BaseRVActivity<Replies.ReplyListBean> implements MovieDetailContract.View{

    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard jcVideoPlayerStandard;
    private int lastId = 0;
    private HeaderViewHolder mHeaderViewHolder;

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

    static class HeaderViewHolder{
        @Bind(R.id.tv_movie_title)
        TextView mTvMovieTitle;
        @Bind(R.id.tv_movie_type)
        TextView mTvMovieType;
        @Bind(R.id.rl_autor_view)
        RelativeLayout mRlAuthorView;
        @Bind(R.id.iv_movie_author_avatar)
        ImageView mIvAuthorAvatar;
        @Bind(R.id.tv_movie_author_name)
        TextView mTvAuthorName;
        @Bind(R.id.tv_movie_author_counts)
        TextView mTvAuthorCounts;
        @Bind(R.id.tv_movie_author_destription)
        TextView mTvAuthorDescription;
        @Bind(R.id.tv_movie_destription)
        TextView mTvMovieDescription;

        public HeaderViewHolder(View view) {
            //view绑定
            ButterKnife.bind(this, view);
        }
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
        boolean setUp = jcVideoPlayerStandard.setUp(item.data.playUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
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
        SetTranslanteBar();
        initAdapter(ReplyItemAdapter.class, false, true);

        //给RecycleView插入头部
        mAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_replyitem, parent, false);
                return headerView;
            }

            @Override
            public void onBindView(View headerView) {
                mHeaderViewHolder = new HeaderViewHolder(headerView);
            }
        });
        onRefresh();
    }

    //初始化头部
    private void initHeaderView() {
        if (item.data.author == null){
            mHeaderViewHolder.mRlAuthorView.setVisibility(View.GONE);
        } else {
            Glide.with(mContext)
                    .load(item.data.author.getIcon())
                    .placeholder(R.mipmap.avatar_default)
                    .transform(new GlideCircleTransform(MovieDetailActivity.this))
                    .into(mHeaderViewHolder.mIvAuthorAvatar);
            mHeaderViewHolder.mTvAuthorName.setText(item.data.author.getName());
            mHeaderViewHolder.mTvAuthorCounts.setText(item.data.author.getVideoNum() + "个视频");
            mHeaderViewHolder.mTvAuthorDescription.setText(item.data.author.getDescription());
        }
        mHeaderViewHolder.mTvMovieTitle.setText(item.data.title);
        mHeaderViewHolder.mTvMovieType.setText(item.data.category + " / " + TimeUtils.secToTime(item.data.duration));
        mHeaderViewHolder.mTvMovieDescription.setText(item.data.description);
    }

    //点击播放视频
    @OnClick(R.id.videoplayer)
    public void videoClick(){
      //  jcVideoPlayerStandard.startFullscreen();
    }

    @OnClick(R.id.ib_back)
    public void ibBackClick(){
        onBackPressed();
    }

    @Override
    public void attachView() {
        if (mMovieDetailPresenter != null)
            mMovieDetailPresenter.attachView(this);
    }

    @Override
    public void showMovieDetailRplies(List<Replies.ReplyListBean> replyList) {

        mAdapter.addAll(replyList);
        initHeaderView();
        if (replyList.size() != 0){
            lastId = replyList.get(replyList.size() - 1).getSequence();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mMovieDetailPresenter.getMovieDetailReplies(item.data.id);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mMovieDetailPresenter.getMovieDetailReplies(item.data.id, lastId);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //页面停止时销毁视频
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(mHeaderViewHolder);
        if (mMovieDetailPresenter != null)
            mMovieDetailPresenter.detachView();
    }
}
