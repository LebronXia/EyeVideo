package com.example.xiaobozheng.eyevideo.ui.support;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;

import com.example.xiaobozheng.eyevideo.util.AnimatorUtil;

/**
 * Created by xiaobozheng on 11/23/2016.
 */

public class ScaleUpShowBehavior extends FloatingActionButton.Behavior {

    private boolean isAnimatingOut = false;

    public ScaleUpShowBehavior(Context context, AttributeSet attrs){
        super();
    }

    /**
     * 页面开始滑动
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed > 0)) && child.getVisibility() != View.VISIBLE) {// 显示
            AnimatorUtil.scaleShow(child, null);
        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0 && dyUnconsumed < 0)) && child.getVisibility() != View.GONE && !isAnimatingOut) {
            AnimatorUtil.scaleHide(child, viewPropertyAnimatorListener);
        }
    }

    /**
     * 页面停止滑动
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOut = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };
}
