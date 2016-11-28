package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseFragment;
import com.example.xiaobozheng.eyevideo.base.BaseRVFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.ui.contract.ChoiceVideoContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.ChoicePresenter;
import com.example.xiaobozheng.eyevideo.util.LogUtils;

import butterknife.Bind;

/**
 * 精选页面
 * Created by xiaobozheng on 11/24/2016.
 */

public class ChoiceFragment extends BaseRVFragment<ChoicePresenter, Daily> implements ChoiceVideoContract.View{

    private String dateTime = "";

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_choice;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMianVideoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void initDatas() {
        mPresenter.getChoiceDailyData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void showChoiceDailyData(Daily daily) {

        LogUtils.d(daily.issueList.size()+ "数量~~~");
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    @Override
    protected void loaddingError() {
        super.loaddingError();
    }

    @Override
    public void onItemClick(int position) {

    }
}
