package com.example.xiaobozheng.eyevideo.ui.presenter;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.base.BaseRxPresenter;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.ui.contract.MovieDetailContract;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by xiaobozheng on 12/2/2016.
 */

public class MovieDetailPresenter extends BaseRxPresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter{

   private Api mApi;

    @Inject
    public MovieDetailPresenter(Api api){
        this.mApi = api;
    }

    @Override
    public void getMovieDetailReplies(int id) {
        Subscription rxSubscription = mApi.getReplies(id)
                .map(replies -> replies.getReplyList())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Replies.ReplyListBean> >() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<Replies.ReplyListBean> replyList) {
                        mView.showMovieDetailRplies(replyList);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getMovieDetailReplies(int id, int lastId) {

    }
}
