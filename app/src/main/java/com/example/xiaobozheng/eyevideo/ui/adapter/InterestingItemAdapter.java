package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.BaseViewHolder;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.IntentManager;

/**
 * Created by xiaobozheng on 12/15/2016.
 */

public class InterestingItemAdapter extends RecyclerArrayAdapter<ItemList>{

    public InterestingItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new BaseViewHolder<ItemList>( parent, R.layout.item_movie) {
            @Override
            public void setData(ItemList item) {
                holder.setRationImageViewOriginalSize(R.id.riv_movie,16,9);
                holder.setRationImageImageUrl(R.id.riv_movie, item.data.cover.detail);
                holder.setText(R.id.tv_movietitle,item.data.title);
                holder.setText(R.id.tv_movietype, "#" + item.data.category + " / " + TimeUtils.secToTime(item.data.duration));
                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fly(holder.getView(R.id.riv_movie), item);
                    }
                });
            }
        };
    }

    private void fly(View view, ItemList item){
        IntentManager.flyToMovieDetail((Activity) mContext, item, view);
    }
}
