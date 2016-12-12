package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.SectionList;
import com.example.xiaobozheng.eyevideo.model.SpecialData;

/**
 * Created by xiaobozheng on 12/12/2016.
 */

public interface SpecialDetailContract {
    interface View extends BaseContract.BaseView{
        void showSpecialDetailData(SpecialData specialData);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getSpecialDetailData(int id);
    }
}
