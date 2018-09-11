package com.yyq58.activity.bean;

import java.util.List;

public class MineFansListBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"qq":null,"notlikes":0,"city":"合肥市","consumerId":"16de1a85c20e4016927cd2692c6cf539","sex":"1","county":"蜀山区","changes":1,"phoneNum":"13665552312","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132","labels":[{"labelName":"小品","lableId":"63b449506e0a45288384016a663a1f37"}],"trueName":null,"starTime":"2018-06-06","isFollow":false,"weixin":null,"province":"安徽省","account":"sliver_ligh","views":1649,"likes":0}]
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
         * consumerId : 16de1a85c20e4016927cd2692c6cf539
         * sex : 1
         * county : 蜀山区
         * changes : 1.0
         * phoneNum : 13665552312
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep1axwaMCyAib3LSLWCxsgxtfBwb7N8za7WicA2mdwS1I1Su9xZJhRZDzO6HfycJVDAPGn3MrWrtJsg/132
         * labels : [{"labelName":"小品","lableId":"63b449506e0a45288384016a663a1f37"}]
         * trueName : null
         * starTime : 2018-06-06
         * isFollow : false
         * weixin : null
         * province : 安徽省
         * account : sliver_ligh
         * views : 1649
         * likes : 0
         */

        private Object qq;
        private int notlikes;
        private String city;
        private String consumerId;
        private String sex;
        private String county;
        private double changes;
        private String phoneNum;
        private String avatar;
        private Object trueName;
        private String starTime;
        private boolean isFollow;
        private Object weixin;
        private String province;
        private String account;
        private int views;
        private int likes;
        private List<LabelsBean> labels;

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

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public static class LabelsBean {
            /**
             * labelName : 小品
             * lableId : 63b449506e0a45288384016a663a1f37
             */

            private String labelName;
            private String lableId;

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            public String getLableId() {
                return lableId;
            }

            public void setLableId(String lableId) {
                this.lableId = lableId;
            }
        }
    }
}
