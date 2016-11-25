package com.example.xiaobozheng.eyevideo.ui.presenter;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.base.BaseRxPresenter;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.ui.contract.ChoiceVideoContract;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public class ChoicePresenter extends BaseRxPresenter<ChoiceVideoContract.View> implements ChoiceVideoContract.Presenter{

    private Api mApi;

    @Inject
    public ChoicePresenter(Api mApi){
        this.mApi = mApi;
    }

    @Override
    public void getChoiceDailyData(long date) {
        Subscription rxSubscription = mApi.getDaily(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Daily>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(Daily daily) {
                        mView.showChoiceDailyData(daily);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
