package com.example.xiaobozheng.eyevideo.ui.fragment;


import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.Category;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.RecycleViewItemData;
import com.example.xiaobozheng.eyevideo.ui.adapter.MultipleItemAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.ChoiceVideoContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.ChoicePresenter;
import com.example.xiaobozheng.eyevideo.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.drakeet.multitype.Item;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 精选页面
 * Created by xiaobozheng on 11/24/2016.
 */

public class ChoiceFragment extends BaseRVFragment<ChoicePresenter, RecycleViewItemData> implements ChoiceVideoContract.View{

    private List<RecycleViewItemData> mItems;
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
        mItems = new ArrayList<RecycleViewItemData>();
        mPresenter.getChoiceDailyData();
    }

    @Override
    public void initView() {

        //mAdapter = new MultiTypeAdapter(mItems);
       // mAdapter.register(Category.class, new CategoryViewProvider());
        //mAdapter.register(ItemList.class, new DailyItemViewHolder());

       initAdapter(MultipleItemAdapter.class, true, true);

    }

    @Override
    public void showChoiceDailyData(Daily daily) {
        LogUtils.d(daily.issueList.get(0).itemList.get(0).data.duration + "持续时间");
        int time = daily.issueList.get(0).itemList.get(0).data.duration;
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
           // mItems.addAll(issueList.itemList);
        }
        String nextPageUrl = daily.nextPageUrl;
        dateTime = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1, nextPageUrl.indexOf("&"));
        mAdapter.addAll(mItems);

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
