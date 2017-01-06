package com.example.xiaobozheng.eyevideo.ui.fragment;


import android.app.ActivityOptions;
import android.content.Intent;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.Category;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.RecycleViewItemData;
import com.example.xiaobozheng.eyevideo.ui.activity.MovieDetailActivity;
import com.example.xiaobozheng.eyevideo.ui.adapter.MultipleItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.ChoiceVideoContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.ChoicePresenter;
import com.example.xiaobozheng.eyevideo.util.LogUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 精选页面
 * Created by xiaobozheng on 11/24/2016.
 */
public class ChoiceFragment extends BaseRVFragment<ChoicePresenter, RecycleViewItemData> implements ChoiceVideoContract.View{

    public static final String EXTRA_ITEM = "extra_item";
    private List<RecycleViewItemData> mItems = new ArrayList<>();
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
    public void initDatas() {

    }

    @Override
    public void initView() {
        initAdapter(MultipleItemAdapter.class, true, true);

        onRefresh();
    }

    @Override
    public void showChoiceDailyData(Daily daily, boolean isRefresh) {
        LogUtils.d(daily.issueList.get(0).itemList.get(0).data.duration + "持续时间");
        mItems.clear();
        if (isRefresh){
            mAdapter.clear();
            LogUtils.d(mAdapter.getCount());
        }
        //int time = daily.issueList.get(0).itemList.get(0).data.duration;
        //将数据置于同一个item数列中
        for (Daily.IssueList issueList:daily.issueList){
            String date = issueList.itemList.get(0).data.text;
            RecycleViewItemData<Category> recycleViewItemData1 = new RecycleViewItemData<>();
            recycleViewItemData1.setT(new Category( date == null ?  "Today" : date));
            recycleViewItemData1.setDataType(MultipleItemAdapter.ITEM_TYPE.ITEM_TYPE_DATE.ordinal());
            mItems.add(recycleViewItemData1);
            for (ItemList itemList : issueList.itemList){
                RecycleViewItemData<ItemList> recycleViewItemData2 = new RecycleViewItemData<>();
                recycleViewItemData2.setT(itemList);
                recycleViewItemData2.setDataType(MultipleItemAdapter.ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal());
                mItems.add(recycleViewItemData2);
            }
        }
        String nextPageUrl = daily.nextPageUrl;
        dateTime = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1, nextPageUrl.indexOf("&"));
        mAdapter.addAll(mItems);

    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mPresenter.getChoiceDailyData(Long.decode(dateTime), false);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getChoiceDailyData(true);
    }


    @Override
    public void onItemClick(int position) {
       // RecycleViewItemData recycleViewItemData = mAdapter.getItem(position);
       // final ItemList itemList = (ItemList) recycleViewItemData.getT();

    }
}
