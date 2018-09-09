package com.yyq58.activity.bean;

import java.util.List;

public class MineAttentionBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"qq":null,"notlikes":0,"city":"合肥市","consumerId":"2ae76af2db8f4a728fb66e1db0d5bf0c","sex":"2","county":"包河区","changes":0,"weight":null,"phoneNum":"18202716403","avatar":"http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKPJeOfAOn6Gprp8BaeR99VPzfFI3Z1E9eMaIl7z1yGfTjY9ahZIZkr9KibbzhU9Jg1eqDwVAjqpuA/132","labels":[],"trueName":null,"starTime":"2018-01-23","isFollow":true,"weixin":null,"province":"安徽省","motto":null,"account":"7th dance crew-Kaya","views":301,"likes":0,"height":null},{"qq":null,"notlikes":0,"city":"合肥市","consumerId":"1867d581b753431ba7deb309781cbceb","sex":null,"county":null,"changes":0,"weight":null,"phoneNum":"15375130009","avatar":"https://wxapi.yyq58.com//static/images/avator.jpg","labels":[],"trueName":null,"starTime":"2018-05-25","isFollow":true,"weixin":null,"province":"安徽省","motto":null,"account":"手机用户0009","views":35,"likes":0,"height":null}]
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
         * qq : null
         * notlikes : 0
         * city : 合肥市
         * consumerId : 2ae76af2db8f4a728fb66e1db0d5bf0c
         * sex : 2
         * county : 包河区
         * changes : 0.0
         * weight : null
         * phoneNum : 18202716403
         * avatar : http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKPJeOfAOn6Gprp8BaeR99VPzfFI3Z1E9eMaIl7z1yGfTjY9ahZIZkr9KibbzhU9Jg1eqDwVAjqpuA/132
         * labels : []
         * trueName : null
         * starTime : 2018-01-23
         * isFollow : true
         * weixin : null
         * province : 安徽省
         * motto : null
         * account : 7th dance crew-Kaya
         * views : 301
         * likes : 0
         * height : null
         */

        private Object qq;
        private int notlikes;
        private String city;
        private String consumerId;
        private String sex;
        private String county;
        private double changes;
        private Object weight;
        private String phoneNum;
        private String avatar;
        private Object trueName;
        private String starTime;
        private boolean isFollow;
        private Object weixin;
        private String province;
        private Object motto;
        private String account;
        private int views;
        private int likes;
        private Object height;
        private List<?> labels;

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public int getNotlikes() {
            return notlikes;
        }

        public void setNotlikes(int notlikes) {
            this.notlikes = notlikes;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public double getChanges() {
            return changes;
        }

        public void setChanges(double changes) {
            this.changes = changes;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getTrueName() {
            return trueName;
        }

        public void setTrueName(Object trueName) {
            this.trueName = trueName;
        }

        public String getStarTime() {
            return starTime;
        }

        public void setStarTime(String starTime) {
            this.starTime = starTime;
        }

        public boolean isIsFollow() {
            return isFollow;
        }

        public void setIsFollow(boolean isFollow) {
            this.isFollow = isFollow;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public Object getMotto() {
            return motto;
        }

        public void setMotto(Object motto) {
            this.motto = motto;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
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

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public List<?> getLabels() {
            return labels;
        }

        public void setLabels(List<?> labels) {
            this.labels = labels;
        }
    }
}
