package com.example.xiaobozheng.eyevideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Riane on 2017/6/19.
 */

public class GradationScrollView extends ScrollView{

    public interface ScrollViewListener{
        void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    private ScrollViewListener mScrollViewListener = null;

    public GradationScrollView(Context context) {
        super(context);
    }

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradationScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        this.mScrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener != null){
            mScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
