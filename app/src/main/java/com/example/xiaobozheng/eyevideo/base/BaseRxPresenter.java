package com.example.xiaobozheng.eyevideo.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public class BaseRxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>{

    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe(){
        if (mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription){
        if (mCompositeSubscription == null){
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
