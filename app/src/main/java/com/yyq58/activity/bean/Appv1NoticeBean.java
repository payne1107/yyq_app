package com.yyq58.activity.bean;

import java.util.List;

public class Appv1NoticeBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"reason":null,"mail":"","title":"8月31日发布会开幕表演","type":0,"content":"开幕表演，需要有一定舞台表演经验和二次元出演经验，身高168以上。","rewardStatus":0,"isadmin":null,"price":"自报价","place":"安徽省 合肥市 包河区","labelName":"COS","views":18,"likes":0,"qq":null,"isQiudan":0,"images":"","haveHB":0,"consumerId":"9f8b33d2f85f486084e8d63e9183ddb8","deadTime":true,"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIloO6dAOJyib2LSzWFOWFwC1aLQjK2QE8hwylIRbZlXAhgUt3PIsbHVMYBaGDmoAqzru51v5XM8rQ/132","mianyi":"1","xingyong":"0","noticeId":"81970c8be1dd4b37ae86b81d73012c41","weixin":"","phone":"15665511575","createTime":"2018-08-27 17:03:57","manyuan":"0","time":"2018-08-28 17:02","account":"张琨"},{"reason":null,"mail":"","title":"9.21-9.24四天，合肥银泰男礼仪，300一个人一天，空的给我卡加介绍视频！","type":0,"content":"9.21-9.24四天，合肥银泰男礼仪，300一个人一天，空的给我卡加介绍视频！\r\n微信 fanxiansheng2474","rewardStatus":0,"isadmin":"0","price":"自报价","place":"安徽省 合肥市 瑶海区","labelName":"礼仪","views":13,"likes":0,"qq":null,"isQiudan":0,"images":null,"haveHB":null,"consumerId":"a8ace04b317a4005a0efe3781b99351f","deadTime":true,"avatar":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzPWdFuSro6cic4S26BVGctU853hOxmdeibzPK9xSrzXibO36n5QOlFpib2Qmu1VgyGMeKuLgNOdgcPA/0","mianyi":"1","xingyong":"0","noticeId":"a79fa17ce77c45f8958daa05d08a1da2","weixin":"fanxiansheng2474","phone":"fanxianshen","createTime":"2018-08-27 17:03:03","manyuan":"0","time":"2018-08-28","account":"阿思"},{"reason":null,"mail":"","title":"激光舞联系我","type":1,"content":"激光舞联系我\r\n\r\n微信 fanxiansheng2474","rewardStatus":0,"isadmin":"0","price":"自报价","place":"安徽省 合肥市 庐阳区","labelName":"舞蹈","views":15,"likes":0,"qq":null,"isQiudan":0,"images":null,"haveHB":null,"consumerId":"878f17e75fff4ff7bf6947f94bf151a2","deadTime":true,"avatar":"http://wx.qlogo.cn/mmopen/vi_32/vzoIDC8461lMiaz5fiaxrkZGTgHT473Kmu4UcufdQxDS8hF1icImNDuV0HmVw7dTakqUTl9k1qdHkIy7GJhe4sQYw/0","mianyi":"1","xingyong":"0","noticeId":"4d928e2bf3424528a01158370dd11956","weixin":"fanxiansheng2474","phone":"fanxianshen","createTime":"2018-08-27 16:52:49","manyuan":"0","time":"2018-08-28","account":"Lvd"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reason : null
         * mail :
         * title : 8月31日发布会开幕表演
         * type : 0
         * content : 开幕表演，需要有一定舞台表演经验和二次元出演经验，身高168以上。
         * rewardStatus : 0
         * isadmin : null
         * price : 自报价
         * place : 安徽省 合肥市 包河区
         * labelName : COS
         * views : 18
         * likes : 0
         * qq : null
         * isQiudan : 0
         * images :
         * haveHB : 0
         * consumerId : 9f8b33d2f85f486084e8d63e9183ddb8
         * deadTime : true
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIloO6dAOJyib2LSzWFOWFwC1aLQjK2QE8hwylIRbZlXAhgUt3PIsbHVMYBaGDmoAqzru51v5XM8rQ/132
         * mianyi : 1
         * xingyong : 0
         * noticeId : 81970c8be1dd4b37ae86b81d73012c41
         * weixin :
         * phone : 15665511575
         * createTime : 2018-08-27 17:03:57
         * manyuan : 0
         * time : 2018-08-28 17:02
         * account : 张琨
         */

        private String reason;
        private String mail;
        private String title;
        private int type;
        private String content;
        private int rewardStatus;
        private String isadmin;
        private String price;
        private String place;
        private String labelName;
        private int views;
        private int likes;
        private String qq;
        private int isQiudan;
        private String images;
        private int haveHB;
        private String consumerId;
        private boolean deadTime;
        private String avatar;
        private String mianyi;
        private String xingyong;
        private String noticeId;
        private String weixin;
        private String phone;
        private String createTime;
        private String manyuan;
        private String time;
        private String account;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getRewardStatus() {
            return rewardStatus;
        }

        public void setRewardStatus(int rewardStatus) {
            this.rewardStatus = rewardStatus;
        }

        public String getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(String isadmin) {
            this.isadmin = isadmin;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getIsQiudan() {
            return isQiudan;
        }

        public void setIsQiudan(int isQiudan) {
            this.isQiudan = isQiudan;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getHaveHB() {
            return haveHB;
        }

        public void setHaveHB(int haveHB) {
            this.haveHB = haveHB;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public boolean isDeadTime() {
            return deadTime;
        }

        public void setDeadTime(boolean deadTime) {
            this.deadTime = deadTime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMianyi() {
            return mianyi;
        }

        public void setMianyi(String mianyi) {
            this.mianyi = mianyi;
        }

        public String getXingyong() {
            return xingyong;
        }

        public void setXingyong(String xingyong) {
            this.xingyong = xingyong;
        }

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getManyuan() {
            return manyuan;
        }

        public void setManyuan(String manyuan) {
            this.manyuan = manyuan;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
