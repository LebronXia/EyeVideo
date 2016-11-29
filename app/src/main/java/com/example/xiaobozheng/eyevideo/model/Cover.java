package com.example.xiaobozheng.eyevideo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public class Cover implements Parcelable {
    public String feed;
    public String detail;
    public String blurred;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.feed);
        dest.writeString(this.detail);
        dest.writeString(this.blurred);
    }

    protected Cover(Parcel in) {
        this.feed = in.readString();
        this.detail = in.readString();
        this.blurred = in.readString();
    }

    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel source) {
            return new Cover(source);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };
}
