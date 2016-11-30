package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.Daily;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public interface ChoiceVideoContract {
    interface View extends BaseContract.BaseView{
        void showChoiceDailyData(Daily daily, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getChoiceDailyData(long date, boolean isRefresh);

        void getChoiceDailyData(boolean isRefresh);
    }
}
