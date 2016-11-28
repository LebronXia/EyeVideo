package com.example.xiaobozheng.eyevideo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public class Data implements Parcelable {
    public String dataType;
    public int id;
    public String title;
    //日期
    public String text;
    public String description;
    public String category;
    public Cover cover;
    public String playUrl;
    public long duration;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(text);
        dest.writeString(this.description);
        dest.writeString(this.category);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.playUrl);
        dest.writeLong(this.duration);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.text = in.readString();
        this.description = in.readString();
        this.category = in.readString();
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.playUrl = in.readString();
        this.duration = in.readLong();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
