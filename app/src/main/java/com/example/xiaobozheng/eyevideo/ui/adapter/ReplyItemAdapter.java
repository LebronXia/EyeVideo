package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.xiaobozheng.eyevideo.R;
import com.example.xiaobozheng.eyevideo.model.RecycleViewItemData;
import com.example.xiaobozheng.eyevideo.model.Replies;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.BaseViewHolder;
import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by Riane on 2016/12/5.
 */

public class ReplyItemAdapter extends RecyclerArrayAdapter<Replies.ReplyListBean> {

    public ReplyItemAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<Replies.ReplyListBean>(parent, R.layout.item_replies) {
            @Override
            public void setData(Replies.ReplyListBean item) {
                super.setData(item);

                holder.setText( R.id.reply_name, item.getUser().getNickname());
                holder.setText(R.id.reply_time, DateUtils.getRelativeTimeSpanString(item.getCreateTime(), System.currentTimeMillis(),
                        DateUtils.SECOND_IN_MILLIS).toString().toLowerCase());
                holder.setText(R.id.reply_description, item.getMessage());
                holder.setText(R.id.likeCount, item.getLikeCount() + "");
                holder.setCircleImageUrl(R.id.reply_avatar, item.getUser().getAvatar(), R.mipmap.avatar_default);
            }
        };
    }
}
