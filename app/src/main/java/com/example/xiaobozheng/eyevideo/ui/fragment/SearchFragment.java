package com.example.xiaobozheng.eyevideo.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseFragment;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.ui.activity.SearchActivity;
import com.example.xiaobozheng.eyevideo.ui.contract.SearchContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SearchPresenter;
import com.example.xiaobozheng.eyevideo.util.RxBus;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by xiaobozheng on 12/20/2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View{

    @Bind(R.id.fl_searchword)
    TagFlowLayout mTagFlowLayout;
    private List<String> tags = new ArrayList<>();
    private TagAdapter<String> mTagAdapter;

    @Inject
    SearchPresenter mSearchPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_search;
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

        mTagAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String data) {
                TextView tag = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_tag, parent, false);
                tag.setText(data);
                return tag;
            }
        };

        mTagFlowLayout.setOnTagClickListener((view, position, parent) -> {
           // startResultActivity(tags.get(position));
            RxBus.getInstance().post(tags.get(position));
            return true;
        });
        mTagFlowLayout.setAdapter(mTagAdapter);
        mSearchPresenter.getTrendingTags();
    }

    @Override
    public void attachView() {
        if (mSearchPresenter != null){
            mSearchPresenter.attachView(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showTrendingTags(List<String> TrendingTags) {
        if (TrendingTags == null || TrendingTags.size() < 0) return;
            tags.addAll(TrendingTags);
            mTagAdapter.notifyDataChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }


}
