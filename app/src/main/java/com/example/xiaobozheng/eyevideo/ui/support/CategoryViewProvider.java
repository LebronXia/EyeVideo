//package com.example.xiaobozheng.eyevideo.ui.support;
//
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.xiaobozheng.eyevideo.R;
//import com.example.xiaobozheng.eyevideo.model.Category;
//
//import me.drakeet.multitype.ItemViewProvider;
//
///**
// * Created by Riane on 2016/11/29.
// */
//
//public class CategoryViewProvider extends ItemViewProvider<Category, CategoryViewProvider.ViewHolder>{
//
//    @NonNull
//    @Override
//    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
//        View root = inflater.inflate(R.layout.item_date, parent, false);
//
//        return new ViewHolder(root);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
//        holder.category.setText(category.text);
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder{
//        private final TextView category;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            this.category = (TextView) itemView.findViewById(R.id.tv_date);
//        }
//    }
//}
