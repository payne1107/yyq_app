package com.yyq58.activity.bean;

import java.util.List;

public class PersonDetailsBean {

    /**
     * code : 1000
     * msg : null
     * data : {"city":"合肥市","county":"蜀山区","changes":4.54,"phoneNum":"13966658584","bankName":"中国建设银行","trueName":"魏乐","starTime":"2018-06-27","attentionNums":"2","province":"安徽省","vip":null,"views":1491,"likes":30,"height":"15","bankNameNum":"6665465457557546645","xingYong":"2.5","qq":"1737834735","notlikes":1,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","sex":"1","weight":"12","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png","fansNums":"1","weixin":"13966658584","jifen":1420,"motto":"测试","bankPalce":"提线木偶艺术","orderNums":"25","lables":[{"labelName":"主持/ 司仪","lableId":"ff7580349be5413980a6b8ecbec88a27"}],"account":"微乐"}
     * result : true
     */

    private int code;
    private Object msg;
    private DataBean data;
    private boolean result;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * city : 合肥市
         * county : 蜀山区
         * changes : 4.54
         * phoneNum : 13966658584
         * bankName : 中国建设银行
         * trueName : 魏乐
         * starTime : 2018-06-27
         * attentionNums : 2
         * province : 安徽省
         * vip : null
         * views : 1491
         * likes : 30
         * height : 15
         * bankNameNum : 6665465457557546645
         * xingYong : 2.5
         * qq : 1737834735
         * notlikes : 1
         * consumerId : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * sex : 1
         * weight : 12
         * avatar : http://wxapi.yyq58.com/static/images/avatar/2018/5/26/05602780-6c88-4f37-b62e-7f690d2e0fda.png
         * fansNums : 1
         * weixin : 13966658584
         * jifen : 1420.0
         * motto : 测试
         * bankPalce : 提线木偶艺术
         * orderNums : 25
         * lables : [{"labelName":"主持/ 司仪","lableId":"ff7580349be5413980a6b8ecbec88a27"}]
         * account : 微乐
         */

        private String city;
        private String county;
        private double changes;
        private String phoneNum;
        private String bankName;
        private String trueName;
        private String starTime;
        private String attentionNums;
        private String province;
        private Object vip;
        private int views;
        private int likes;
        private String height;
        private String bankNameNum;
        private String xingYong;
        private String qq;
        private int notlikes;
        private String consumerId;
        private String sex;
        private String weight;
        private String avatar;
        private String fansNums;
        private String weixin;
        private String jifen;
        private String motto;
        private String bankPalce;
        private String orderNums;
        private String account;
        private List<LablesBean> lables;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public String getStarTime() {
            return starTime;
        }

        public void setStarTime(String starTime) {
            this.starTime = starTime;
        }

        public String getAttentionNums() {
            return attentionNums;
        }

        public void setAttentionNums(String attentionNums) {
            this.attentionNums = attentionNums;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public Object getVip() {
            return vip;
        }

        public void setVip(Object vip) {
            this.vip = vip;
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

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getBankNameNum() {
            return bankNameNum;
        }

        public void setBankNameNum(String bankNameNum) {
            this.bankNameNum = bankNameNum;
        }

        public String getXingYong() {
            return xingYong;
        }

        public void setXingYong(String xingYong) {
            this.xingYong = xingYong;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public int getNotlikes() {
            return notlikes;
        }

        public void setNotlikes(int notlikes) {
            this.notlikes = notlikes;
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

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getFansNums() {
            return fansNums;
        }

        public void setFansNums(String fansNums) {
            this.fansNums = fansNums;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getJifen() {
            return jifen;
        }

        public void setJifen(String jifen) {
            this.jifen = jifen;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

        public String getBankPalce() {
            return bankPalce;
        }

        public void setBankPalce(String bankPalce) {
            this.bankPalce = bankPalce;
        }

        public String getOrderNums() {
            return orderNums;
        }

        public void setOrderNums(String orderNums) {
            this.orderNums = orderNums;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public List<LablesBean> getLables() {
            return lables;
        }

        public void setLables(List<LablesBean> lables) {
            this.lables = lables;
        }

        public static class LablesBean {
            /**
             * labelName : 主持/ 司仪
             * lableId : ff7580349be5413980a6b8ecbec88a27
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
