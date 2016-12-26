package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.xiaobozheng.eyevideo.ui.fragment.AuthorTypeFragment;

/**
 * Created by xiaobozheng on 12/23/2016.
 */

public class AuthorTypeItemAdapter extends FragmentPagerAdapter{
    private static final int PAGE_COUNT = 2;
    private int mAuthorId;
    private Context mContext;

    public AuthorTypeItemAdapter(Context context, FragmentManager fm, int authorId) {
        super(fm);
        this.mAuthorId = authorId;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        String type = null;
        switch (position){
            case 0:
                type = "date";
                break;
            case 1:
                type = "shareCount";
                break;
        }
        return AuthorTypeFragment.newInstance(mAuthorId, type);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "按时间分享";
            case 1:
                return "分享排行榜";
        }
        return null;
    }
}
