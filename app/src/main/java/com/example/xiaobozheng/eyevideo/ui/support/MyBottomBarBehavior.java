package com.example.xiaobozheng.eyevideo.ui.support;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xiaobozheng on 12/19/2016.
 */

public class MyBottomBarBehavior extends CoordinatorLayout.Behavior<View>{

    public MyBottomBarBehavior(Context context, AttributeSet attrs){
        super();
    }

    //确定所提供的子视图是否有另一个特定的同级视图作为布局从属
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    //用于响应从属布局的变化
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTop());  //获取跟随布局的顶部位置

        child.setTranslationY(translationY);
        return true;
    }
}
