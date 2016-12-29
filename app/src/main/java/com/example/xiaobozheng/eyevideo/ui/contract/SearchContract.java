package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.SearchResult;

import java.util.List;

/**
 * Created by xiaobozheng on 12/16/2016.
 */

public interface SearchContract {

    interface  View extends BaseContract.BaseView{
        void showTrendingTags(List<String> TrendingTags);

        void showSearchResult(List<ItemList> searchResults);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getTrendingTags();

        void getSearchResult(String query, int start);
    }
}
