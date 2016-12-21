package com.example.xiaobozheng.eyevideo.ui.activity;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.base.BaseActivity;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.injection.component.DaggerMianVideoComponent;
import com.example.xiaobozheng.eyevideo.ui.contract.SearchContract;
import com.example.xiaobozheng.eyevideo.ui.fragment.SearchFragment;
import com.example.xiaobozheng.eyevideo.ui.fragment.SearchResultFragment;
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
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static android.R.attr.data;

/**
 * Created by xiaobozheng on 12/16/2016.
 */

public class SearchActivity extends BaseActivity {

    public static final String KEYWORD = "keyword";
    @Bind(R.id.tv_toolbar_title)
    TextView mTvToolbarTitle;
    @Bind(R.id.fl_toolbar_search)
    FrameLayout mFlToolBarSearch;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    //当前Fragment
    Fragment mContent;
    Fragment to;
    Fragment mSearchRedultFragment;
    private String keyword;





    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void initToolBar() {

    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        Observable<String> observable = RxBus.getInstance().register(this);
        //设置透明栏
        SetTranslanteBar();
        //隐藏标题栏标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTvToolbarTitle.setVisibility(View.GONE);
        mFlToolBarSearch.setVisibility(View.VISIBLE);

      //  mContent = new SearchFragment();

        if (savedInstanceState == null){
            mContent = new SearchFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_content, mContent).commit();
        }


        //设置EditExt监听
        mEtSearch.setOnKeyListener((v, keyCode, event)->{
            if (event.getAction() != KeyEvent.ACTION_DOWN) return true;

            if (keyCode == KeyEvent.KEYCODE_ENTER){
                if (mEtSearch.getText().toString().isEmpty()){
                    Toast.makeText(SearchActivity.this,
                            "Keyword must not empty!", Toast.LENGTH_SHORT).show();
                } else{
                    startResultActivity(mEtSearch.getText().toString());
                }
            } else if (keyCode == KeyEvent.KEYCODE_BACK){
                finish();
            }
            return false;
        });


        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>(){
            @Override
            public void call(String s) {
                switchContent(s);
            }
        });

    }

    @Override
    public void initDatas() {

    }


    public  void switchContent(String word){
        mSearchRedultFragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(KEYWORD, word);
        mSearchRedultFragment.setArguments(args);
        to = mSearchRedultFragment;
        if (mContent != to){
            mContent = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {	// 先判断是否被add过
                transaction.hide(mContent).add(R.id.frame_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
       // getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mSearchRedultFragment).commitAllowingStateLoss();

    }

//    public void switchContent(Fragment from, Fragment to) {
//        if (mContent != to) {
//            mContent = to;
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            if (!to.isAdded()) {	// 先判断是否被add过
//                transaction.hide(from).add(R.id.frame_content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//            } else {
//                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//            }
//        }
//    }

    private void startResultActivity(String keyword){
        Intent intent = new Intent(this,SearchResultActivity.class);
        intent.putExtra(KEYWORD, keyword);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(this);
        super.onDestroy();
    }
}
