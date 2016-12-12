package com.example.xiaobozheng.eyevideo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

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
    //发现的图片
    public String image;
    public String actionUrl;
    //图片
    public Cover cover;
    //类型
    public String category;
    public Author author;
    public String playUrl;
    //持续时间
    public int duration;
    public Header header;
    //发现广告栏里面的Banner
    public List<ItemList> itemList;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.description);
        dest.writeString(this.image);
        dest.writeString(this.actionUrl);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.category);
        dest.writeParcelable(this.author, flags);
        dest.writeString(this.playUrl);
        dest.writeInt(this.duration);
        dest.writeParcelable(this.header, flags);
        dest.writeTypedList(this.itemList);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.text = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.actionUrl = in.readString();
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.category = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.playUrl = in.readString();
        this.duration = in.readInt();
        this.header = in.readParcelable(Header.class.getClassLoader());
        this.itemList = in.createTypedArrayList(ItemList.CREATOR);
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

    public static class Header implements Parcelable {
        public int id;
        public String title;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
        }

        public Header() {
        }

        protected Header(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
        }

        public static final Creator<Header> CREATOR = new Creator<Header>() {
            @Override
            public Header createFromParcel(Parcel source) {
                return new Header(source);
            }

            @Override
            public Header[] newArray(int size) {
                return new Header[size];
            }
        };
    }
}
