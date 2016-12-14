package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.RatioImageView;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * Created by xiaobozheng on 12/14/2016.
 */

public class GalleryAdapter extends EasyRVAdapter<ItemList>{

    public GalleryAdapter(Context context, List<ItemList> list) {
        super(context, list, R.layout.item_author_gallery);
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, ItemList item) {
        viewHolder.setImageUrl(R.id.riv_specialdetail, item.data.cover.detail)
                .setText(R.id.tv_specialdatail_title, item.data.title)
                .setText(R.id.tv_specialdatail_type,"#" + item.data.category + " / " + TimeUtils.secToTime(item.data.duration));
        RatioImageView mRivMovie = viewHolder.getView(R.id.riv_specialdetail);
        mRivMovie.setOriginalSize(16, 9);

    }
}
