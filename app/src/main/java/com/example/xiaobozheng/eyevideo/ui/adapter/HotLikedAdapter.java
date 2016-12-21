package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.view.View;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.common.OnRvItemClickListener;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.RatioImageView;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * Created by Riane on 2016/12/14.
 */

public class HotLikedAdapter extends EasyRVAdapter<ItemList>{

    private OnRvItemClickListener itemClickListener;

    public HotLikedAdapter(Context context, List<ItemList> list) {
        super(context, list, R.layout.item_movie);
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, ItemList item) {
        viewHolder.setImageUrl(R.id.riv_movie, item.data.cover.detail)
                .setText(R.id.tv_movietitle, item.data.title)
                .setText(R.id.tv_movietype,"#" + item.data.category + " / " + TimeUtils.secToTime(item.data.duration));
        RatioImageView mRivMovie = viewHolder.getView(R.id.riv_movie);
        mRivMovie.setOriginalSize(16, 9);

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // itemClickListener.onItemClick();
            }
        });

    }
}
