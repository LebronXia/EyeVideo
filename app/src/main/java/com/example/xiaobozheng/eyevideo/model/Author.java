package com.example.xiaobozheng.eyevideo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaobozheng on 12/5/2016.
 */

public class Author implements Parcelable {
    //        "author": {
//            "id": 274,
//                    "icon": "http://img.kaiyanapp.com/d6ef9cea89690e59e148519c5640980c.jpeg?imageMogr2/quality/60",
//                    "name": "知了青年",
//                    "description": "纪录片《了不起的匠人》制作团队，专注青年纪录片制作。世界向前，我们向上！",
//                    "link": "",
//                    "latestReleaseTime": 1480694400000,
//                    "videoNum": 11,
//                    "adTrack": null,
//                    "follow": {
//                "itemType": "author",
//                        "itemId": 274,
//                        "followed": false
//            }
        private int id;
        private String icon;
        private String name;
        private String description;
        private int videoNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getVideoNum() {
            return videoNum;
        }

        public void setVideoNum(int videoNum) {
            this.videoNum = videoNum;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.icon);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.videoNum);
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.id = in.readInt();
        this.icon = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.videoNum = in.readInt();
    }

    public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}

