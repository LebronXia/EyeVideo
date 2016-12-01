package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.Category;
import com.example.xiaobozheng.eyevideo.model.Daily;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.model.RecycleViewItemData;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.BaseViewHolder;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.IntentManager;

import java.util.List;

import me.drakeet.multitype.Item;

import static android.R.attr.category;

/**
 * Created by xiaobozheng on 11/28/2016.
 */

public class MultipleItemAdapter extends RecyclerArrayAdapter<RecycleViewItemData>{

    private static final String VIDEO_TAG = "video";
    public MultipleItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_DATE.ordinal()){
            return new BaseViewHolder<RecycleViewItemData>(parent, R.layout.item_date) {
                @Override
                public void setData(RecycleViewItemData item) {
                    super.setData(item);
                    Category category = (Category) item.getT();
                    holder.setText(R.id.tv_date,category.text);
                }
            } ;
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal()){
            return new BaseViewHolder<RecycleViewItemData>(parent, R.layout.item_movie) {
                @Override
                public void setData(RecycleViewItemData item) {
                    super.setData(item);
                    final ItemList itemList = (ItemList) item.getT();
                    //video和textHeader两种类型
                    if (itemList.type.contains(VIDEO_TAG)){
                        holder.setVisible(R.id.riv_movie, View.VISIBLE);
                        holder.setVisible(R.id.tv_movietitle,View.VISIBLE);
                        holder.setVisible(R.id.tv_movietype, View.VISIBLE);
                        holder.setRationImageViewOriginalSize(R.id.riv_movie,16,9);
                        holder.setRationImageImageUrl(R.id.riv_movie, itemList.data.cover.detail);
                        holder.setText(R.id.tv_movietitle,itemList.data.title);
;                       holder.setText(R.id.tv_movietype, "#" + itemList.data.category + " / " + TimeUtils.secToTime(itemList.data.duration));
                       //holder.getItemView(R.id.riv_movie)
                        holder.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                fly(holder.getView(R.id.riv_movie), itemList);
                            }
                        });

                    } else {
                        holder.setVisible(R.id.riv_movie, View.GONE);
                        holder.setVisible(R.id.tv_movietitle,View.GONE);
                        holder.setVisible(R.id.tv_movietype, View.GONE);
                    }
                }
            };
        }
        return null;
    }


    private void fly(View view, ItemList item){
        IntentManager.flyToMovieDetail((Activity) mContext, item, view);
    }

    @Override
    public int getViewType(int position) {
        return mObjects.get(position).getDataType();
    }



    public enum ITEM_TYPE{
        ITEM_TYPE_DATE,
        ITEM_TYPE_MOVIE;
    }
}
