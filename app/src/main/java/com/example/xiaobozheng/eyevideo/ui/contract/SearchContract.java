package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;

import java.util.List;

/**
 * Created by xiaobozheng on 12/16/2016.
 */

public interface SearchContract {

    interface  View extends BaseContract.BaseView{
        void showTrendingTags(List<String> TrendingTags);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getTrendingTags();
    }
}
