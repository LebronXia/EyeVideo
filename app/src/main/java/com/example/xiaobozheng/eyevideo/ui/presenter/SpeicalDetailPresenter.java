package com.example.xiaobozheng.eyevideo.ui.presenter;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.base.BaseRxPresenter;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialContract;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialDetailContract;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.id;

/**
 * Created by xiaobozheng on 12/12/2016.
 */

public class SpeicalDetailPresenter extends BaseRxPresenter<SpecialDetailContract.View> implements SpecialDetailContract.Presenter{

    private Api mApi;

    @Inject
    public SpeicalDetailPresenter(Api api){
        this.mApi = api;
    }

    @Override
    public void getSpecialDetailData(int id) {
        Subscription rxSubscription = mApi.getSpecialData(id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialData>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                        Logger.d(e.toString());
                    }

                    @Override
                    public void onNext(SpecialData specialData) {
                        mView.showSpecialDetailData(specialData);
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
