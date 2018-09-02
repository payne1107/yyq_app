package com.yyq58.activity.bean;

import java.util.List;

public class AnnuciteFragmentBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"detailPlace":"测试","reason":null,"city":"北京市","num":1,"typeName":"选秀/ 赛事","county":"东城区","title":"招聘选秀/ 赛事1人地点：测试","type":1,"content":"通告内容简介","province":"北京市","labelId":"b9fcb5d015384d98a5589f287a84e221","price":"100","place":"北京市 北京市 东城区","labelName":"选秀/ 赛事","views":13,"likes":30,"isQiudan":0,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":null,"xingyong":"2.5","noticeId":"1086962a3ac246feae99c52ddbffa4f7","createTime":"2018-07-18 10:48:01","manyuan":"0","time":"2018-07-28 10:47","account":"微乐"},{"detailPlace":"测试","reason":null,"city":"北京市","num":1,"typeName":"COS","county":"东城区","title":"招聘COS1人地点：测试","type":1,"content":"通告内容简介","province":"北京市","labelId":"cb26ab29a3b14a2eb26a79e9ddf3a089","price":"100","place":"北京市 北京市 东城区","labelName":"COS","views":2,"likes":30,"isQiudan":0,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":null,"xingyong":"2.5","noticeId":"241beab7f03a4b07b67a75b54b03666f","createTime":"2018-07-18 10:48:01","manyuan":"0","time":"2018-07-28 10:47","account":"微乐"},{"detailPlace":"测试","reason":null,"city":"北京市","num":1,"typeName":"运动","county":"东城区","title":"招聘运动1人地点：测试","type":1,"content":"通告内容提要","province":"北京市","labelId":"a597e3509ad242d38bd85d2d300fb1ad","price":"100","place":"北京市 北京市 东城区","labelName":"运动","views":1,"likes":30,"isQiudan":0,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":null,"xingyong":"2.5","noticeId":"96ffa8255d3c42eeb42835867e1a7c73","createTime":"2018-07-18 10:33:19","manyuan":"0","time":"2018-07-22 10:32","account":"微乐"},{"detailPlace":"测试","reason":null,"city":"北京市","num":1,"typeName":"模特","county":"东城区","title":"招聘模特1人地点：测试","type":1,"content":"通告内容提要","province":"北京市","labelId":"7f57918b55304288a652f764905822a3","price":"100","place":"北京市 北京市 东城区","labelName":"模特","views":1,"likes":30,"isQiudan":0,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":null,"xingyong":"2.5","noticeId":"1bbc65d2bbea40b0b7d00b0d2e9fd010","createTime":"2018-07-18 10:33:04","manyuan":"0","time":"2018-07-22 10:32","account":"微乐"},{"detailPlace":"测试","reason":null,"city":"北京市","num":1,"typeName":"运动","county":"东城区","title":"招聘运动1人地点：测试","type":1,"content":"通告内容提要","province":"北京市","labelId":"a597e3509ad242d38bd85d2d300fb1ad","price":"100","place":"北京市 北京市 东城区","labelName":"运动","views":0,"likes":30,"isQiudan":0,"tureName":"魏乐","images":null,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","phonenum":"13966658584","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","mianyi":null,"xingyong":"2.5","noticeId":"c225603a612c4591a55e43614de3a039","createTime":"2018-07-18 10:33:04","manyuan":"0","time":"2018-07-22 10:32","account":"微乐"}]
     */

    private int code;
    private Object msg;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * detailPlace : 测试
         * reason : null
         * city : 北京市
         * num : 1
         * typeName : 选秀/ 赛事
         * county : 东城区
         * title : 招聘选秀/ 赛事1人地点：测试
         * type : 1
         * content : 通告内容简介
         * province : 北京市
         * labelId : b9fcb5d015384d98a5589f287a84e221
         * price : 100
         * place : 北京市 北京市 东城区
         * labelName : 选秀/ 赛事
         * views : 13
         * likes : 30
         * isQiudan : 0
         * tureName : 魏乐
         * images : null
         * consumerId : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * phonenum : 13966658584
         * avatar : http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png
         * mianyi : null
         * xingyong : 2.5
         * noticeId : 1086962a3ac246feae99c52ddbffa4f7
         * createTime : 2018-07-18 10:48:01
         * manyuan : 0
         * time : 2018-07-28 10:47
         * account : 微乐
         */

        private String detailPlace;
        private Object reason;
        private String city;
        private int num;
        private String typeName;
        private String county;
        private String title;
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
        private Object mianyi;
        private String xingyong;
        private String noticeId;
        private String createTime;
        private String manyuan;
        private String time;
        private String account;

        public String getDetailPlace() {
            return detailPlace;
        }

        public void setDetailPlace(String detailPlace) {
            this.detailPlace = detailPlace;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
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

        public Object getMianyi() {
            return mianyi;
        }

        public void setMianyi(Object mianyi) {
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
