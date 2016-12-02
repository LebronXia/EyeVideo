package com.example.xiaobozheng.eyevideo.model;

import java.util.List;

/**
 * Created by xiaobozheng on 12/2/2016.
 */

public class Replies {

    /**
     * replyList : [{"id":804510720157810700,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":9,"message":"哈哈，萌，好玩","replyStatus":"PUBLISHED","createTime":1480645277000,"user":{"uid":300149182,"nickname":"Francis","avatar":"http://wx.qlogo.cn/mmopen/UY0uvpSI4sXjc5D1ptlFEhELfC6DhpmXFb8KjAG7S2N4XKb0ppxg5BOoObhgnxXxfAJ18xP9axMGv1f1abgKgoicmXHUfUbLy/0"},"likeCount":0,"liked":false,"hot":false},{"id":804509557484159000,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":8,"message":"有意思，好萌啊","replyStatus":"PUBLISHED","createTime":1480645000000,"user":{"uid":202508350,"nickname":"枪手WYZ","avatar":"http://wx.qlogo.cn/mmopen/UY0uvpSI4sX2EIJufZATgkUVqKgfmeEPc9lbUCDswqEd4n3PmRQ0Qq8fvQZ8rsicRs6Tale2QGicOXj3eNmeCoeJ9N4eGWJ18g/0"},"likeCount":0,"liked":false,"hot":false},{"id":804503403781685200,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":7,"message":"@诚，邀@咑，牸，人，员+好，评，人，员^_^鈤，匴、90，-，360，元^_^+卫，信，号：2\u20e38\u20e30\u20e32\u20e36\u20e31\u20e32\u20e32\u20e35\u20e38\u20e3","replyStatus":"PUBLISHED","createTime":1480643533000,"user":{"uid":300058558,"nickname":"兼直招聘+","avatar":"http://img.kaiyanapp.com/c79b2fe003e8b07683e3192caea9590f.png"},"likeCount":0,"liked":false,"hot":false},{"id":804495205091770400,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":6,"message":"乐高又来咯❗️❗️❗️意义不大","replyStatus":"PUBLISHED","createTime":1480641578000,"user":{"uid":213629136,"nickname":"peter-gan","avatar":"http://tva1.sinaimg.cn/crop.0.0.720.720.180/8c9bb434jw8erqvm26wzuj20k00k0jt2.jpg"},"likeCount":1,"liked":false,"hot":false},{"id":804493988798136300,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":5,"message":"不怕死的蜘蛛，敢情不怕被吃了","replyStatus":"PUBLISHED","createTime":1480641288000,"user":{"uid":300170747,"nickname":"Joker","avatar":"http://wx.qlogo.cn/mmopen/szJD2VLPNJlK9daZ4ctpIyibAkcia8DV5D6qiaelvCDHAicAJyibDDdL22EIkeibHcDibutibmlfF40LOC8tE7xGpZ6VVy5CqZwkNxicK/0"},"likeCount":1,"liked":false,"hot":false},{"id":804492545106116600,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":4,"message":"呵呵\u2026\u2026","replyStatus":"PUBLISHED","createTime":1480640944000,"user":{"uid":300128064,"nickname":"A.暴珠","avatar":"http://wx.qlogo.cn/mmopen/UY0uvpSI4sVxu0JTerhtxUy75XCib42daa4WejaE5jNicG01FJOj90iaDoO08L16rrbJ4qMB3Wy6T8Drr9DPyVZBab2BqJ5IBKY/0"},"likeCount":0,"liked":false,"hot":false},{"id":804492112212000800,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":3,"message":"哈哈哈哈哈哈哈","replyStatus":"PUBLISHED","createTime":1480640841000,"user":{"uid":300141657,"nickname":"Less Talks","avatar":"http://img.kaiyanapp.com/20283d3a7580aeedf77dc7648ffb167c.png"},"likeCount":0,"liked":false,"hot":false},{"id":804491935023628300,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":2,"message":"公鸡\u2026\u2026","replyStatus":"PUBLISHED","createTime":1480640799000,"user":{"uid":225933762,"nickname":"快刀绞肉碎骨魔","avatar":"http://img.wdjimg.com/image/account/9ad9364861b2d880e466341a8d3ea11f_300_300.png"},"likeCount":0,"liked":false,"hot":false},{"id":804491786075504600,"videoId":11351,"videoTitle":"漫威超萌小剧场","parentReplyId":0,"sequence":1,"message":"哈哈哈好萌阿","replyStatus":"PUBLISHED","createTime":1480640763000,"user":{"uid":300076272,"nickname":"阿玥阿玥i","avatar":"http://tva2.sinaimg.cn/crop.0.0.664.664.180/005GFcgvjw8f1ygnkh32aj30ig0igt9x.jpg"},"likeCount":1,"liked":false,"hot":false}]
     * count : 9
     * total : 9
     * nextPageUrl : null
     */

    private int count;
    private int total;
    private Object nextPageUrl;
    public List<ReplyListBean> replyList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public static class ReplyListBean {
        /**
         * id : 804510720157810700
         * videoId : 11351
         * videoTitle : 漫威超萌小剧场
         * parentReplyId : 0
         * sequence : 9
         * message : 哈哈，萌，好玩
         * replyStatus : PUBLISHED
         * createTime : 1480645277000
         * user : {"uid":300149182,"nickname":"Francis","avatar":"http://wx.qlogo.cn/mmopen/UY0uvpSI4sXjc5D1ptlFEhELfC6DhpmXFb8KjAG7S2N4XKb0ppxg5BOoObhgnxXxfAJ18xP9axMGv1f1abgKgoicmXHUfUbLy/0"}
         * likeCount : 0
         * liked : false
         * hot : false
         */

        private long id;
        private int videoId;
        private String videoTitle;
        private int parentReplyId;
        private int sequence;
        private String message;
        private String replyStatus;
        private long createTime;
        private UserBean user;
        private int likeCount;
        private boolean liked;
        private boolean hot;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public int getParentReplyId() {
            return parentReplyId;
        }

        public void setParentReplyId(int parentReplyId) {
            this.parentReplyId = parentReplyId;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getReplyStatus() {
            return replyStatus;
        }

        public void setReplyStatus(String replyStatus) {
            this.replyStatus = replyStatus;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public static class UserBean {
            /**
             * uid : 300149182
             * nickname : Francis
             * avatar : http://wx.qlogo.cn/mmopen/UY0uvpSI4sXjc5D1ptlFEhELfC6DhpmXFb8KjAG7S2N4XKb0ppxg5BOoObhgnxXxfAJ18xP9axMGv1f1abgKgoicmXHUfUbLy/0
             */

            private int uid;
            private String nickname;
            private String avatar;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
