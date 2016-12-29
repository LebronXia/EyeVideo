package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.BaseViewHolder;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;
import com.example.xiaobozheng.eyevideo.util.TimeUtils;
import com.example.xiaobozheng.eyevideo.widget.IntentManager;

import static com.example.xiaobozheng.eyevideo.R.layout.item;

/**
 * Created by xiaobozheng on 12/29/2016.
 */

public class SearchResultAdapter extends RecyclerArrayAdapter<ItemList> {
    private GalleryAdapter mGalleryAdapter;

    public SearchResultAdapter(Context context){
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_AUTHOR.ordinal()){
            return new BaseViewHolder<ItemList>(parent, R.layout.item_hotauthor_content) {
                @Override
                public void setData(ItemList item) {
                    super.setData(item);
                    holder.setImageUrl(R.id.iv_hotauthor_avatar, item.data.header.icon)
                            .setText(R.id.tv_hotauthor_name, item.data.header.title)
                            .setText(R.id.tv_hotauthor_des, item.data.header.description);

                    RecyclerView mRecyclerView = holder.getView(R.id.rv_hotauthor);
                    //设置布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    //设置适配器
                    mGalleryAdapter = new GalleryAdapter(mContext, item.data.itemList);
                    mRecyclerView.setAdapter(mGalleryAdapter);
                }
            };
        } else if(viewType == ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal()){
            return new BaseViewHolder<ItemList>(parent, R.layout.item_movie) {
                @Override
                public void setData(ItemList item) {
                    super.setData(item);
                    holder.setRationImageViewOriginalSize(R.id.riv_movie,16,9);
                    holder.setRationImageImageUrl(R.id.riv_movie, item.data.cover.detail);
                    holder.setText(R.id.tv_movietitle,item.data.title);
                    holder.setText(R.id.tv_movietype, "#" + item.data.category + " / " + TimeUtils.secToTime(item.data.duration));
//                    holder.getItemView().setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            fly(holder.getView(R.id.riv_movie), item);
//                        }
//                    });
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
        if (mObjects.get(position).type.equals("videoCollectionWithBrief")){
            return ITEM_TYPE.ITEM_TYPE_AUTHOR.ordinal();
        } else if (mObjects.get(position).type.equals("video")){
            return ITEM_TYPE.ITEM_TYPE_MOVIE.ordinal();
        }
        return 0;
    }

    public enum ITEM_TYPE{
        ITEM_TYPE_MOVIE,
        ITEM_TYPE_AUTHOR;
    }
}
