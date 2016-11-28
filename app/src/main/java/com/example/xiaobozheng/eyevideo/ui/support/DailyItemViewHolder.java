package com.example.xiaobozheng.eyevideo.ui.support;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.widget.RatioImageView;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Riane on 2016/11/29.
 */

public class DailyItemViewHolder extends ItemViewProvider<ItemList,DailyItemViewHolder.ViewHolder>{

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemList itemList) {
        //
        holder.mTvMovieTitle.setText(itemList.data.title);
       // holder.mTvMovieType.setText("#" = itemList.data.category + " / " +  itemList.data);
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
