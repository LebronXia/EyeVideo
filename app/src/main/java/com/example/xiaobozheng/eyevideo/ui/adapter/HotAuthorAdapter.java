package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.common.OnRvItemClickListener;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.EasyRecyclerView;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

import static com.example.xiaobozheng.eyevideo.R.layout.item;

/**
 * Created by xiaobozheng on 12/14/2016.
 */

public class HotAuthorAdapter extends EasyRVAdapter<ItemList>{

    private GalleryAdapter mGalleryAdapter;
    private OnRvItemClickListener itemClickListener;
    public HotAuthorAdapter(Context context, List<ItemList> list) {
        super(context, list, R.layout.item_hotauthor_content);

    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, ItemList item) {
        viewHolder.setImageUrl(R.id.iv_hotauthor_avatar, item.data.header.icon)
                .setText(R.id.tv_hotauthor_name, item.data.header.title)
                .setText(R.id.tv_hotauthor_des, item.data.header.description);

        viewHolder.getView(R.id.rl_anthor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(viewHolder.getView(R.id.rl_anthor), position, item);
            }
        });

        RecyclerView mRecyclerView = viewHolder.getView(R.id.rv_hotauthor);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mGalleryAdapter = new GalleryAdapter(mContext, item.data.itemList);
        mRecyclerView.setAdapter(mGalleryAdapter);
    }

    public void setOnItemClickListener(OnRvItemClickListener listener){
        this.itemClickListener = listener;
    }
}
