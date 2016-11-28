package com.example.xiaobozheng.eyevideo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.xiaobozheng.eyevideo.model.Daily;

import java.util.List;

/**
 * Created by xiaobozheng on 11/28/2016.
 */

public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Daily> items;
    private LayoutInflater inflater;

    public MultipleItemAdapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
