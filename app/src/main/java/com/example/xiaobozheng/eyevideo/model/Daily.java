package com.example.xiaobozheng.eyevideo.model;

import java.util.List;

/**
 * Created by xiaobozheng on 11/25/2016.
 */

public class Daily {
    public List<IssueList> issueList;
    public String nextPageUrl;

    public static class IssueList{
        public long releaseTime;
        public String type;
        public long date;
        public long publishTime;
        private int count;
        public List<ItemList> itemList;
    }
}
