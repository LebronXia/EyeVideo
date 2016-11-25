package com.example.xiaobozheng.eyevideo.base;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public interface BaseContract {
    interface BasePresenter<T>{
        void attachView(T view);

        void detachView();
    }


    interface BaseView{
        void showError();

        void complete();
    }

}
