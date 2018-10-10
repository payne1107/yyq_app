package com.yyq58.activity.bean;

import java.util.List;

public class ScheduleListBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"month":10,"createTime":"2018-10-10 14:50:50","detailTime":"14:50","year":2018,"consumerId":"cc8c6f9fa1574a519dc5bdd63c6af8ba","id":"e0cd1fed8f784235929c8be2130cfd24","place":"测试","day":10,"linkMan":"测试","content":"测试"}]
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
         * month : 10
         * createTime : 2018-10-10 14:50:50
         * detailTime : 14:50
         * year : 2018
         * consumerId : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * id : e0cd1fed8f784235929c8be2130cfd24
         * place : 测试
         * day : 10
         * linkMan : 测试
         * content : 测试
         */

        private int month;
        private String createTime;
        private String detailTime;
        private int year;
        private String consumerId;
        private String id;
        private String place;
        private int day;
        private String linkMan;
        private String content;

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetailTime() {
            return detailTime;
        }

        public void setDetailTime(String detailTime) {
            this.detailTime = detailTime;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
