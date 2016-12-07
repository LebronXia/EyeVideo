package com.example.xiaobozheng.eyevideo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaobozheng.eyevideo.app.EyeVideoApplication;
import com.example.xiaobozheng.eyevideo.injection.component.AppComponent;
import com.example.xiaobozheng.eyevideo.widget.loadding.CustomDialog;

import butterknife.ButterKnife;

/**
 * Created by Riane on 2016/11/25.
 */

public abstract class BaseFragment extends Fragment {

    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater mInflater;

    protected Context mContext;
    private CustomDialog dialog;

    public abstract int getLayoutResId();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.mInflater = inflater;
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupActivityComponent(EyeVideoApplication.getsInstance().getAppComponent());
        attachView();
        initDatas();
        initView();
    }

    public abstract void attachView();

    public abstract void initDatas();

    public abstract void initView();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }

    protected LayoutInflater getLayoutInflater() {
        return mInflater;
    }

    protected View getParentView() {
        return parentView;
    }

    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(getActivity());
            dialog.setCancelable(false);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
