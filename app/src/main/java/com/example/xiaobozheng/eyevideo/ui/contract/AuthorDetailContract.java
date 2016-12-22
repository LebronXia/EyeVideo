package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.AuthorDetailData;

/**
 * Created by xiaobozheng on 12/22/2016.
 */
public interface AuthorDetailContract {
    interface View extends BaseContract.BaseView{
        void showAuthorDetailData(AuthorDetailData authorDetailData, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getAuthorDetaukData(int authorId, String strategy, boolean isRefresh);
    }
}
