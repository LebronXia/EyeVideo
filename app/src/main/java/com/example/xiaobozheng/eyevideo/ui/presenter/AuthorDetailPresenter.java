package com.example.xiaobozheng.eyevideo.ui.presenter;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.base.BaseRxPresenter;
import com.example.xiaobozheng.eyevideo.model.AuthorDetailData;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.ui.contract.AuthorDetailContract;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobozheng on 12/22/2016.
 */

public class AuthorDetailPresenter extends BaseRxPresenter<AuthorDetailContract.View> implements  AuthorDetailContract.Presenter{

    private Api mApi;

    @Inject
    public AuthorDetailPresenter(Api api){
        this.mApi = api;
    }

    @Override
    public void getAuthorDetaukData(int start, int authorId, String strategy, boolean isRefresh) {

        Logger.d("获取作者列表数据");
        Subscription rxSubscription = mApi.getAuthorDetailData(start, authorId, strategy)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuthorDetailData>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                        Logger.d("成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                        Logger.d("失败" + e.toString());
                    }

                    @Override
                    public void onNext(AuthorDetailData mAuthorDetailData) {
                        mView.showAuthorDetailData(mAuthorDetailData, isRefresh);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
