package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.RatioImageView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by xiaobozheng on 12/12/2016.
 */

public class GalleryPageAdapter extends PagerAdapter{

    private List<ItemList> itemLists  = new ArrayList<>();
    private Context context;

    public GalleryPageAdapter(Context context) {
        super();
        this.context = context;
    }

    public void setItemLists(List<ItemList> list){
        if (list.size() <= 0){
            itemLists.clear();
            notifyDataSetChanged();
            return;
        }
        itemLists.clear();
        itemLists.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (itemLists.size() <= 0){
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //
        position %= itemLists.size();

        if (position < 0){
            position = itemLists.size() + position;
        }

        ItemList itemList = itemLists.get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.item_update_content, null);
        view.setTag(position);
        RatioImageView mRivSpeicalDetail = (RatioImageView) view.findViewById(R.id.riv_specialdetail);
        TextView mTvSpecialDetailTitle = (TextView) view.findViewById(R.id.tv_specialdatail_title);
        TextView mTvSpeicalDetailType = (TextView) view.findViewById(R.id.tv_specialdatail_type);
        Glide.with(context)
                .load(itemList.data.cover.detail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mRivSpeicalDetail);
        mRivSpeicalDetail.setOriginalSize(16,9);
        mTvSpecialDetailTitle.setText(itemList.data.title);
        mTvSpeicalDetailType.setText("#" + itemList.data.category + " / " + TimeUtils.secToTime(itemList.data.duration));
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
