package com.yyq58.activity.bean;

import java.util.List;

public class RecommendNoticeBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"images":null,"mail":"","haveHB":null,"TYPENAME":"模特","consumerId":"e5b126e992c24d0883f97e5ce74d723a","title":"10.1-10.3 三天全天，早上10.30到晚上7.00结束静态和动态巡游一天六次左右，来183-185男模 价8  无锡","type":2,"mianyi":"1","LIKES":2,"content":"10.1-10.3 三天全天，早上10.30到晚上7.00结束静态和动态巡游一天六次左右，来183-185男模 价8  无锡\r\n微信 w15055163756","noticeId":"2a382974e48d40048ee195d65a4d5241","weixin":"w15055163756","isadmin":"0","phone":"w1505516375","createTime":"2018-08-31 15:10:54","price":"自报价","manyuan":"0","place":"安徽省 合肥市 瑶海区","time":"2018-09-06","AVATAR":"http://wx.qlogo.cn/mmopen/nOZyqsWXRYoCOYmG1xLzqctM5KgJ7oXVxPL0qMoXWoia6rZyFlLjibwLP6pdM9duPao36XVB6Tv8k1d37iaPgD0Kn4Yicy93Q6l0/0","reward_status":0,"views":9,"account":"奇奇","xingYong":"0"},{"images":null,"mail":"","haveHB":null,"TYPENAME":"礼仪","consumerId":"b92c2d51179345919006f3a99a608b76","title":"滨湖国庆车展玛莎拉蒂礼仪  9.30-10.5 六天 有意私模卡和小视频 2个，很轻松","type":0,"mianyi":"1","LIKES":0,"content":"滨湖国庆车展玛莎拉蒂礼仪  9.30-10.5 六天\r\n有意私模卡和小视频\r\n2个，很轻松\r\n\r\n微信  wangruyan1995","noticeId":"4017a80019a44bd3a7a38af6f4bf7bcb","weixin":"wangruyan1995","isadmin":"0","phone":"wangruyan19","createTime":"2018-08-31 14:33:17","price":"自报价","manyuan":"0","place":"安徽省 合肥市 包河区","time":"2018-09-03","AVATAR":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ9Z0OiaOC0m1v5paHHRwDldmblAibRriaZjvHOU2sSSdibPxIicEnnZx7ice4ATxKqbEO6UyyUxf6RT06A/132","reward_status":0,"views":18,"account":"期待","xingYong":"0"},{"images":null,"mail":"","haveHB":null,"TYPENAME":"其他","consumerId":"3c778c53b4ae456f9752a8ad60e50318","title":"寻合肥实力活动策划公司或领队 节目预算：2万左右 时间：9月24日 中秋晚宴 节目 时长 1个小时左右 带节目单私 微信 yanyiquan888","type":0,"mianyi":"1","LIKES":0,"content":"寻合肥实力活动策划公司或领队\r\n节目预算：2万左右\r\n时间：9月24日 中秋晚宴\r\n节目 时长 1个小时左右\r\n带节目单私 微信 yanyiquan888","noticeId":"f22c14f77c7f41daa29a391a7bc27296","weixin":"yanyiquan888","isadmin":"0","phone":"2945664029","createTime":"2018-08-27 11:27:08","price":"自报价","manyuan":"0","place":"安徽省 合肥市 蜀山区","time":"2018-09-20","AVATAR":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqhcDNjBLO724J6UN38I8GnEHqUrXZZ0vJh0zrodcdAKrMdWFaLH9SaFpewxdTUlLUWcpI4jPT9eQ/132","reward_status":0,"views":27,"account":"大白","xingYong":"0"},{"images":null,"mail":null,"haveHB":0,"TYPENAME":"导演/ 制片人","consumerId":"3c778c53b4ae456f9752a8ad60e50318","title":"招聘导演/ 制片人1人地点：合肥酒店","type":1,"mianyi":null,"LIKES":0,"content":"寻合肥实力活动策划公司或领队\n节目预算：2万左右\n时间：9月24日 中秋晚宴\n节目时长 ：1个小时左右\n带节目单私 微信 yanyiquan888","noticeId":"112cdd06e2f44af4a2b62a1163699670","weixin":null,"isadmin":null,"phone":"1","createTime":"2018-08-27 11:23:14","price":"面议","manyuan":"0","place":"安徽省 合肥市 蜀山区","time":"2018-09-24 11:21","AVATAR":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqhcDNjBLO724J6UN38I8GnEHqUrXZZ0vJh0zrodcdAKrMdWFaLH9SaFpewxdTUlLUWcpI4jPT9eQ/132","reward_status":0,"views":2,"account":"大白","xingYong":"0"}]
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
         * images : null
         * mail :
         * haveHB : null
         * TYPENAME : 模特
         * consumerId : e5b126e992c24d0883f97e5ce74d723a
         * title : 10.1-10.3 三天全天，早上10.30到晚上7.00结束静态和动态巡游一天六次左右，来183-185男模 价8  无锡
         * type : 2
         * mianyi : 1
         * LIKES : 2
         * content : 10.1-10.3 三天全天，早上10.30到晚上7.00结束静态和动态巡游一天六次左右，来183-185男模 价8  无锡
         微信 w15055163756
         * noticeId : 2a382974e48d40048ee195d65a4d5241
         * weixin : w15055163756
         * isadmin : 0
         * phone : w1505516375
         * createTime : 2018-08-31 15:10:54
         * price : 自报价
         * manyuan : 0
         * place : 安徽省 合肥市 瑶海区
         * time : 2018-09-06
         * AVATAR : http://wx.qlogo.cn/mmopen/nOZyqsWXRYoCOYmG1xLzqctM5KgJ7oXVxPL0qMoXWoia6rZyFlLjibwLP6pdM9duPao36XVB6Tv8k1d37iaPgD0Kn4Yicy93Q6l0/0
         * reward_status : 0
         * views : 9
         * account : 奇奇
         * xingYong : 0
         */

        private Object images;
        private String mail;
        private Object haveHB;
        private String TYPENAME;
        private String consumerId;
        private String title;
        private int type;
        private String mianyi;
        private int LIKES;
        private String content;
        private String noticeId;
        private String weixin;
        private String isadmin;
        private String phone;
        private String createTime;
        private String price;
        private String manyuan;
        private String place;
        private String time;
        private String AVATAR;
        private int reward_status;
        private int views;
        private String account;
        private String xingYong;

        public Object getImages() {
            return images;
        }

        public void setImages(Object images) {
            this.images = images;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public Object getHaveHB() {
            return haveHB;
        }

        public void setHaveHB(Object haveHB) {
            this.haveHB = haveHB;
        }

        public String getTYPENAME() {
            return TYPENAME;
        }

        public void setTYPENAME(String TYPENAME) {
            this.TYPENAME = TYPENAME;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
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

        public String getMianyi() {
            return mianyi;
        }

        public void setMianyi(String mianyi) {
            this.mianyi = mianyi;
        }

        public int getLIKES() {
            return LIKES;
        }

        public void setLIKES(int LIKES) {
            this.LIKES = LIKES;
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

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getIsadmin() {
            return isadmin;
        }

        public void setIsadmin(String isadmin) {
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

        public String getAVATAR() {
            return AVATAR;
        }

        public void setAVATAR(String AVATAR) {
            this.AVATAR = AVATAR;
        }

        public int getReward_status() {
            return reward_status;
        }

        public void setReward_status(int reward_status) {
            this.reward_status = reward_status;
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

        public String getXingYong() {
            return xingYong;
        }

        public void setXingYong(String xingYong) {
            this.xingYong = xingYong;
        }
    }
}
