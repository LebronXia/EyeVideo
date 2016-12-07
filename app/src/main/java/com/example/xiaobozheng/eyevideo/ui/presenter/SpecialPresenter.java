package com.example.xiaobozheng.eyevideo.ui.presenter;

import com.example.xiaobozheng.eyevideo.api.Api;
import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.base.BaseRxPresenter;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.contract.SpecialContract;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xiaobozheng on 12/7/2016.
 */

public class SpecialPresenter extends BaseRxPresenter<SpecialContract.View> implements SpecialContract.Presenter{

    private Api mApi;

    @Inject
    public SpecialPresenter(Api api){
        this.mApi = api;
    }

    @Override
    public void getDiscoverData() {
        Subscription rxSubscription = mApi.getDiscover()
                .map(discover -> discover.getItemList())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemList>>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                    }

                    @Override
                    public void onNext(List<ItemList> itemLists) {
                        mView.showDiscoverData(itemLists);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
