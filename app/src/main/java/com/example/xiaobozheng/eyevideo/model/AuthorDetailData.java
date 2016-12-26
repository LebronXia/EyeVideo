package com.example.xiaobozheng.eyevideo.model;

import java.util.List;

/**
 * Created by xiaobozheng on 12/22/2016.
 */

public class AuthorDetailData {
    public List<ItemList> itemList;
    public PgcInfo pgcInfo;
    public String nextPageUrl;

    public static class PgcInfo{
        public int id;
        public String icon;
        public String name;
        public String brief;
        public String description;
    }
}
