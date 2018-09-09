package com.yyq58.activity.bean;

import java.util.List;

public class DynamicFragmentBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"flag":"1","comments":[],"create_time":"2018-06-25 14:16:52","city":"合肥市","consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","county":"蜀山区","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg","content":"说点什么","url":[{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/25/69acf0e2-b234-40a0-ae27-7f086836be10.jpeg"},{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/25/9a3aa0fd-d370-4079-8ca8-b7a10c2a34f0.jpeg"}],"province":"安徽省","id":"f8863d8e89474c44a055ee0e5f72d989","views":0,"account":"微乐","likes":0},{"flag":"1","comments":[],"create_time":"2018-06-25 14:10:52","city":"合肥市","consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","county":"蜀山区","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg","content":"说点什么","url":[{"url":"(\n    \"http://wxapi.yyq58.com/static/images/zone/2018/6/25/48180b17-5f1f-4a98-babe-3d9b2df6d21e.jpeg\"\n)"},{"url":"(\n    \"http://wxapi.yyq58.com/static/images/zone/2018/6/25/ef98685f-1a40-4062-85d4-85b8388fce9b.jpeg\"\n)"}],"province":"安徽省","id":"966bca7d52c64f6b9852cb2fbf49d4cb","views":0,"account":"微乐","likes":0},{"flag":"1","comments":[],"create_time":"2018-06-25 14:05:57","city":"合肥市","consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","county":"蜀山区","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg","content":"说点什么","url":[{"url":"(\n    \"http://wxapi.yyq58.com/static/images/zone/2018/6/25/9a1a9693-b2fa-4449-bcd5-d4db9259017a.jpeg\"\n)"},{"url":"(\n    \"http://wxapi.yyq58.com/static/images/zone/2018/6/25/55458e6a-a814-4834-b96d-06cd6e14232e.jpeg\"\n)"},{"url":"(\n    \"http://wxapi.yyq58.com/static/images/zone/2018/6/25/498e870b-c377-46ee-a84e-1c6eb5afbd25.jpeg\"\n)"}],"province":"安徽省","id":"3074dae44feb46c28cd6f82ce054135a","views":0,"account":"微乐","likes":0},{"flag":"0","comments":[],"create_time":"2018-06-25 13:31:43","city":"合肥市","consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","county":"蜀山区","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg","content":"经常","url":[{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/25/98be191a-ea93-4ac7-92be-8518edc83932.jpeg"}],"province":"安徽省","id":"683379fae57d479da0e45c7799d8e51c","views":0,"account":"微乐","likes":0},{"flag":"0","comments":[],"create_time":"2018-06-02 22:49:52","city":"合肥市","consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","county":"蜀山区","avatar":"http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg","content":"来着美图看看","url":[{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/2/1d95869b-b702-407b-946f-00d3d99d6ba6.jpeg"},{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/2/497e1cc5-3d21-41c9-a6dc-766e7736e2da.jpeg"},{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/2/3489e5cb-2139-4141-81fe-3814b4a7fd10.jpeg"},{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/2/0e84aa66-29e3-46f0-8144-dec08775dd26.jpeg"}],"province":"安徽省","id":"d4d5f83e668f4bd3ab1ecdf928271f35","views":0,"account":"微乐","likes":0}]
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
         * flag : 1
         * comments : []
         * create_time : 2018-06-25 14:16:52
         * city : 合肥市
         * consumerId : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * county : 蜀山区
         * avatar : http://wxapi.yyq58.com/static/images/avatar/2018/9/9/27af5a62-640b-4253-bc7c-5ae8fe702169.jpeg
         * content : 说点什么
         * url : [{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/25/69acf0e2-b234-40a0-ae27-7f086836be10.jpeg"},{"url":"http://wxapi.yyq58.com/static/images/zone/2018/6/25/9a3aa0fd-d370-4079-8ca8-b7a10c2a34f0.jpeg"}]
         * province : 安徽省
         * id : f8863d8e89474c44a055ee0e5f72d989
         * views : 0
         * account : 微乐
         * likes : 0
         */

        private String flag;
        private String create_time;
        private String city;
        private String consumerId;
        private String county;
        private String avatar;
        private String content;
        private String province;
        private String id;
        private int views;
        private String account;
        private int likes;
        private List<?> comments;
        private List<UrlBean> url;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public List<?> getComments() {
            return comments;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public List<UrlBean> getUrl() {
            return url;
        }

        public void setUrl(List<UrlBean> url) {
            this.url = url;
        }

        public static class UrlBean {
            /**
             * url : http://wxapi.yyq58.com/static/images/zone/2018/6/25/69acf0e2-b234-40a0-ae27-7f086836be10.jpeg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
