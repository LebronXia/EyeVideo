package com.example.xiaobozheng.eyevideo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseRVActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.adapter.SearchResultAdapter;
import com.example.xiaobozheng.eyevideo.ui.contract.SearchContract;
import com.example.xiaobozheng.eyevideo.ui.presenter.SearchPresenter;
import com.example.xiaobozheng.eyevideo.util.RxBus;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Observable;

/**
 * Created by xiaobozheng on 12/16/2016.
 */

public class SearchActivity extends BaseRVActivity<ItemList> implements SearchContract.View{

    public static final String KEYWORD = "keyword";
    @Bind(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.ll_hotsearch)
    LinearLayout mLlHotSearch;
    @Bind(R.id.fl_searchword)
    TagFlowLayout mTagFlowLayout;
    @Bind(R.id.fl_toolbar_search)
    FrameLayout mFlSearch;
    private List<String> tags = new ArrayList<>();
    private TagAdapter<String> mTagAdapter;
    //当前Fragment
    Fragment mSearchFragment;
    Fragment mSearchResultFragment;
    private int start = 0;
    private String keyword;

    @Inject
    SearchPresenter mSearchPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
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
        if (mSearchPresenter != null){
            mSearchPresenter.attachView(this);
        }
    }

    @Override
    public void initToolBar() {
        mTvToolbarTitle.setVisibility(View.GONE);
        mFlSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Observable<String> observable = RxBus.getInstance().register(this);
        //设置透明栏
        SetTranslanteBar();
        //隐藏标题栏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initAdapter(SearchResultAdapter.class, false, true);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //mEtSearch.setFocusable(true);
        //设置EditExt监听
        mEtSearch.setOnKeyListener((v, keyCode, event)->{
            if (event.getAction() != KeyEvent.ACTION_DOWN) return true;

            if (keyCode == KeyEvent.KEYCODE_ENTER){
                if (mEtSearch.getText().toString().isEmpty()){
                    Toast.makeText(SearchActivity.this,
                            "Keyword must not empty!", Toast.LENGTH_SHORT).show();
                } else{
                    mSearchPresenter.getSearchResult(mEtSearch.getText().toString().trim(), start);
                }
            } else if (keyCode == KeyEvent.KEYCODE_BACK){
                finish();
            }
            return false;
        });
        mEtSearch.requestFocus();
        showKeyboard();
        //绑定流布局
        mTagAdapter = new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String data) {
                TextView tag = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_tag, parent, false);
                tag.setText(data);
                return tag;
            }
        };

        mTagFlowLayout.setOnTagClickListener((view, position, parent) -> {
            keyword = tags.get(position);
            mEtSearch.setText(keyword);
            mSearchPresenter.getSearchResult(keyword, start);
            return true;
        });
        mTagFlowLayout.setAdapter(mTagAdapter);
        mSearchPresenter.getTrendingTags();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return false;
    }

    //获得热搜词
    @Override
    public void showTrendingTags(List<String> TrendingTags) {
        if (TrendingTags == null || TrendingTags.size() < 0) return;
        tags.addAll(TrendingTags);
        mTagAdapter.notifyDataChanged();
    }

    //获得搜索结果
    @Override
    public void showSearchResult(List<ItemList> searchResults) {
       // mAdapter.clear();
        mAdapter.addAll(searchResults);
        mAdapter.notifyDataSetChanged();
        start += 10;
        initSearchResultView();
    }

    //初始化搜索结果
    private void initSearchResultView(){
        mLlHotSearch.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    //软键盘的自动弹出
    private void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mEtSearch,  InputMethodManager.SHOW_FORCED);
       // inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mSearchPresenter.getSearchResult(keyword, start);
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    @Override
    public void onItemClick(int position) {

    }

    public  void switchContent(String word){
        Bundle args = new Bundle();
        args.putString(KEYWORD, word);
        mSearchResultFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mSearchResultFragment).commitAllowingStateLoss();
    }
}
