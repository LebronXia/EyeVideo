package com.example.xiaobozheng.eyevideo.ui.support;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
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

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Riane on 2016/11/29.
 */

public class DailyItemViewHolder extends ItemViewProvider<ItemList,DailyItemViewHolder.ViewHolder>{

    private static final String VIDEO_TAG = "video";

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemList itemList) {
        //video和textHeader两种类型
        if (itemList.type.contains(VIDEO_TAG)){
            holder.mRivMovie.setVisibility(View.VISIBLE);
            holder.mTvMovieTitle.setVisibility(View.VISIBLE);
            holder.mTvMovieType.setVisibility(View.VISIBLE);
            holder.mRivMovie.setOriginalSize(16,9);
            Glide.with(holder.mRivMovie.getContext())
                    .load(itemList.data.cover.detail)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.mRivMovie);
            holder.mTvMovieTitle.setText(itemList.data.title);
            holder.mTvMovieType.setText("#" + itemList.data.category + " / " + TimeUtils.secToTime(itemList.data.duration));
        } else {
            holder.mRivMovie.setVisibility(View.GONE);
            holder.mTvMovieTitle.setVisibility(View.GONE);
            holder.mTvMovieType.setVisibility(View.GONE);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final RatioImageView mRivMovie;
        private final TextView mTvMovieTitle;
        private final TextView mTvMovieType;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mRivMovie = (RatioImageView) itemView.findViewById(R.id.riv_movie);
            this.mTvMovieTitle = (TextView) itemView.findViewById(R.id.tv_movietitle);
            this.mTvMovieType = (TextView) itemView.findViewById(R.id.tv_movietype);
        }
    }
}
