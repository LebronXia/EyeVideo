package com.example.xiaobozheng.eyevideo.ui.contract;

import com.example.xiaobozheng.eyevideo.base.BaseContract;
import com.example.xiaobozheng.eyevideo.model.Replies;

import java.util.List;

/**
 * Created by xiaobozheng on 12/2/2016.
 */

public interface MovieDetailContract {

    interface View extends BaseContract.BaseView{
        void showMovieDetailRplies(List<Replies.ReplyListBean> replyList);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getMovieDetailReplies(int id);

        void getMovieDetailReplies(int id, int lastId);
    }
}
