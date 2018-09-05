package com.yyq58.activity.bean;

import java.util.List;

public class QiangDanFramgmentBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"images":null,"mail":null,"consumerId":"16de1a85c20e4016927cd2692c6cf539","typeName":"运动","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132","title":"招聘运动1人地点：支付测试","type":1,"mianyi":null,"xingyong":"0","content":"通告内容","noticeId":"05b62050c1254fa989f5e128ae233025","weixin":null,"isadmin":null,"phone":"13665552312","createTime":"2018-07-18 09:19:31","price":"100","manyuan":"0","place":"北京市 北京市 东城区","time":"2018-10-01 00:00","id":"469f7e8d937a4a3e81be2bfa8bbe0d7a","views":0,"account":"sliver_ligh","likes":0,"status":1},{"images":null,"mail":null,"consumerId":"16de1a85c20e4016927cd2692c6cf539","typeName":"运动","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132","title":"招聘运动1人地点：催收款","type":1,"mianyi":null,"xingyong":"0","content":"通告内容","noticeId":"7086ad93555e474cbbe4f15246f98641","weixin":null,"isadmin":null,"phone":"13665552312","createTime":"2018-07-11 09:34:57","price":"面议","manyuan":"0","place":"北京市 北京市 东城区","time":"2018-09-01 00:00","id":"544b193f01e74f6aa1b3c98e57a4cd47","views":3,"account":"sliver_ligh","likes":0,"status":1},{"images":null,"mail":null,"consumerId":"16de1a85c20e4016927cd2692c6cf539","typeName":"运动","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132","title":"招聘运动1人地点：已定档","type":1,"mianyi":null,"xingyong":"0","content":"通告内容","noticeId":"2ee7577f0ed84d0ebdb6c3a375da6592","weixin":null,"isadmin":null,"phone":"13665552312","createTime":"2018-07-11 09:31:28","price":"面议","manyuan":"0","place":"北京市 北京市 东城区","time":"2018-08-01 00:00","id":"5a7c8f5243004ea5a25ff9f1284e5d23","views":2,"account":"sliver_ligh","likes":0,"status":1},{"images":null,"mail":null,"consumerId":"16de1a85c20e4016927cd2692c6cf539","typeName":"演员","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132","title":"招聘演员1人地点：待定档","type":1,"mianyi":null,"xingyong":"0","content":"通告内容","noticeId":"da0c6b41187a4eae9f8d9eb8a460f118","weixin":null,"isadmin":null,"phone":"13665552312","createTime":"2018-07-11 09:29:39","price":"面议","manyuan":"0","place":"北京市 北京市 东城区","time":"2018-10-01 00:00","id":"b138123a13c9435cb8c838a4f35fbb47","views":2,"account":"sliver_ligh","likes":0,"status":1},{"images":null,"mail":null,"consumerId":"385c4ad3be1047eba9443d27674c1cbd","typeName":"画像","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/4/19/064eda22-eed6-4ae9-ac5e-e0196515bb33.jpeg","title":"招聘画像1人地点：金陵大酒店","type":1,"mianyi":null,"xingyong":"2.5","content":"今天在酒店面试","noticeId":"fa96c6fe734744bcad054db7b810b79f","weixin":null,"isadmin":null,"phone":"1","createTime":"2018-06-02 10:27:33","price":"面议","manyuan":"0","place":"安徽省 合肥市 包河区","time":"2018-06-02 13:26","id":"ec3e7eff2be14c2c8ce2606ce40fcc52","views":15,"account":"宇宙无敌超级噼里啪啦小姐姐","likes":37,"status":1},{"images":null,"mail":null,"consumerId":"2253e9efd332419e866b0482424b8263","typeName":"演员","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/6/22/4fc6484f-3146-4454-86dd-1eaad3f7f4bc.jpeg","title":"招聘演员1人地点：希尔顿大酒店","type":1,"mianyi":null,"xingyong":"0","content":"招聘演员一名","noticeId":"bb04b93fb99d4cffb44588f7d6b6630f","weixin":null,"isadmin":null,"phone":"0","createTime":"2018-05-26 10:37:04","price":"100","manyuan":"0","place":"安徽省 合肥市 蜀山区","time":"2018-05-27 10:36","id":"fddb7583d00b4b61842981f1b9a7411b","views":33,"account":"Jacky Tao123","likes":0,"status":1}]
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
         * images : null
         * mail : null
         * consumerId : 16de1a85c20e4016927cd2692c6cf539
         * typeName : 运动
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132
         * title : 招聘运动1人地点：支付测试
         * type : 1
         * mianyi : null
         * xingyong : 0
         * content : 通告内容
         * noticeId : 05b62050c1254fa989f5e128ae233025
         * weixin : null
         * isadmin : null
         * phone : 13665552312
         * createTime : 2018-07-18 09:19:31
         * price : 100
         * manyuan : 0
         * place : 北京市 北京市 东城区
         * time : 2018-10-01 00:00
         * id : 469f7e8d937a4a3e81be2bfa8bbe0d7a
         * views : 0
         * account : sliver_ligh
         * likes : 0
         * status : 1
         */

        private Object images;
        private Object mail;
        private String consumerId;
        private String typeName;
        private String avatar;
        private String title;
        private int type;
        private Object mianyi;
        private String xingyong;
        private String content;
        private String noticeId;
        private Object weixin;
        private Object isadmin;
        private String phone;
        private String createTime;
        private String price;
        private String manyuan;
        private String place;
        private String time;
        private String id;
        private int views;
        private String account;
        private int likes;
        private int status;

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public Object getMail() {
            return mail;
        }

        public void setMail(Object mail) {
            this.mail = mail;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(String noticeId) {
            this.noticeId = noticeId;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public Object getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(Object isadmin) {
            this.isadmin = isadmin;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getManyuan() {
            return manyuan;
        }

        public void setManyuan(String manyuan) {
            this.manyuan = manyuan;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
