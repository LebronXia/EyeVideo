package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.ItemList;

import java.util.List;

/**
 * Created by xiaobozheng on 12/20/2016.
 */

public interface SearchResultContract {

    interface View extends BaseContract.BaseView{
        void showSearchResult(List<ItemList> SearchResult);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getSearchResult(String key, int start);
    }

}
