package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.ItemList;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.BaseViewHolder;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by xiaobozheng on 12/7/2016.
 */

public class SpecialItemAdapter extends RecyclerArrayAdapter<ItemList>{

    public SpecialItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<ItemList>(parent, R.layout.item_squarecard) {
            @Override
            public void setData(ItemList item) {
                if (item.type.equals("squareCard")){
                    holder.setText(R.id.tv_special_card, item.data.title);
                    ImageView imgPicture = holder.getView(R.id.iv_special_card);
                    ViewGroup.LayoutParams params = imgPicture.getLayoutParams();

                    DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
                    int width = dm.widthPixels / 2;//宽度为屏幕宽度一半
                    params.height = width;
                    imgPicture.setLayoutParams(params);
                    Glide.with(mContext).load(item.data.image).
                            diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgPicture);
                    //holder.setImageUrl(R.id.iv_special_card, item.data.image);
                }
            }
        };
    }
}
