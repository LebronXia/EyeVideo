package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.Interesting;
import com.example.xiaobozheng.eyevideo.model.ItemList;

import java.util.List;

/**
 * Created by xiaobozheng on 12/15/2016.
 */

public interface MainContentContract {

    interface View extends BaseContract.BaseView{
        void showInterestingData(List<ItemList> interestingLists, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getInteresting(int start, int categoryId, String strategy, boolean isRefresh);
    }

}
