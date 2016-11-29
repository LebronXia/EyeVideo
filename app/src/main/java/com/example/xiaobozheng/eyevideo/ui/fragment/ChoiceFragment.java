package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseFragment;
import com.example.xiaobozheng.eyevideo.base.BaseRVFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.Category;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.contract.ChoiceVideoContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.ChoicePresenter;
import com.example.xiaobozheng.eyevideo.ui.support.CategoryViewProvider;
import com.example.xiaobozheng.eyevideo.ui.support.DailyItemViewHolder;
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

public class ChoiceFragment extends BaseRVFragment<ChoicePresenter, Daily> implements ChoiceVideoContract.View{

    private List<Item> mItems;
    private String dateTime = "";
    private MultiTypeAdapter mAdapter;

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
        mItems = new ArrayList<Item>();
        mPresenter.getChoiceDailyData();
    }

    @Override
    public void initView() {

        mAdapter = new MultiTypeAdapter(mItems);
        mAdapter.register(Category.class, new CategoryViewProvider());
        mAdapter.register(ItemList.class, new DailyItemViewHolder());

       // initAdapter(MultiTypeAdapter.class, true, true);

    }

    @Override
    public void showChoiceDailyData(Daily daily) {
        LogUtils.d(daily.issueList.get(0).itemList.get(0).data.duration + "持续时间");
        int time = daily.issueList.get(0).itemList.get(0).data.duration;
        //将数据置于同一个item数列中
        for (Daily.IssueList issueList:daily.issueList){
            String date = issueList.itemList.get(0).data.text;
            mItems.add(new Category( date == null ?  "Today" : date));
            mItems.addAll(issueList.itemList);
        }
        String nextPageUrl = daily.nextPageUrl;
        dateTime = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1, nextPageUrl.indexOf("&"));

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
