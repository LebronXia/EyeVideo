package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.ItemList;

import java.util.List;

/**
 *
 * Created by xiaobozheng on 12/7/2016.
 */

public interface SpecialContract {
    interface View extends BaseContract.BaseView{
        void showDiscoverData(List<ItemList> itemLists);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getDiscoverData();
    }
}
