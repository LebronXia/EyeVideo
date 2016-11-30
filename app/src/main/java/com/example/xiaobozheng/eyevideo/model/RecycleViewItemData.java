package com.example.xiaobozheng.eyevideo.model;

import com.example.xiaobozheng.eyevideo.ui.view.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by xiaobozheng on 11/30/2016.
 */

public class RecycleViewItemData<T> {
    T t;
    int dataType;

    public RecycleViewItemData(){

    }

    public RecycleViewItemData(T t, int dataType){
        this.t = t;
        this.dataType = dataType;
    }

    public T getT(){
        return  t;
    }

    public void setT(T t){
        this.t = t;
    }
    public int getDataType(){
        return dataType;
    }

    public void setDataType(int dataType){
        this.dataType = dataType;
    }
}
