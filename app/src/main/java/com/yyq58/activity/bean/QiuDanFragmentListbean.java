package com.yyq58.activity.bean;

import java.util.List;

public class QiuDanFragmentListbean {

    /**
     * code : 1000
     * msg : null
     * data : [{"detailPlace":null,"reason":2,"city":"北京市","num":null,"typeName":"演员","county":"东城区","title":null,"type":0,"content":"通告内容","province":"北京市","labelId":"31a2cd63731a4ea4b1d82d9c93bdfcb0","price":"","place":"北京市 北京市 东城区","labelName":"演员","views":2,"likes":30,"isQiudan":1,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":"1","xingyong":"2.5","noticeId":"77aea0ce1b8446708be6ecb849220820","createTime":"2018-06-20 14:39:21","manyuan":"0","time":"2018-06-20 14:39","account":"微乐"}]
     * result : true
     */

    private int code;
    private Object msg;
    private boolean result;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detailPlace : null
         * reason : 2
         * city : 北京市
         * num : null
         * typeName : 演员
         * county : 东城区
         * title : null
         * type : 0
         * content : 通告内容
         * province : 北京市
         * labelId : 31a2cd63731a4ea4b1d82d9c93bdfcb0
         * price :
         * place : 北京市 北京市 东城区
         * labelName : 演员
         * views : 2
         * likes : 30
         * isQiudan : 1
         * tureName : 魏乐
         * images : null
         * consumerId : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * phonenum : 13966658584
         * avatar : http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png
         * mianyi : 1
         * xingyong : 2.5
         * noticeId : 77aea0ce1b8446708be6ecb849220820
         * createTime : 2018-06-20 14:39:21
         * manyuan : 0
         * time : 2018-06-20 14:39
         * account : 微乐
         */

        private Object detailPlace;
        private int reason;
        private String city;
        private Object num;
        private String typeName;
        private String county;
        private Object title;
        private int type;
        private String content;
        private String province;
        private String labelId;
        private String price;
        private String place;
        private String labelName;
        private int views;
        private int likes;
        private int isQiudan;
        private String tureName;
        private Object images;
        private String consumerId;
        private String phonenum;
        private String avatar;
        private String mianyi;
        private String xingyong;
        private String noticeId;
        private String createTime;
        private String manyuan;
        private String time;
        private String account;

        public Object getDetailPlace() {
            return detailPlace;
        }

        public void setDetailPlace(Object detailPlace) {
            this.detailPlace = detailPlace;
        }

        public int getReason() {
            return reason;
        }

        public void setReason(int reason) {
            this.reason = reason;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getNum() {
            return num;
        }

        public void setNum(Object num) {
            this.num = num;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLabelId() {
            return labelId;
        }

        public void setLabelId(String labelId) {
            this.labelId = labelId;
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

        public int getIsQiudan() {
            return isQiudan;
        }

        public void setIsQiudan(int isQiudan) {
            this.isQiudan = isQiudan;
        }

        public String getTureName() {
            return tureName;
        }

        public void setTureName(String tureName) {
            this.tureName = tureName;
        }

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
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
