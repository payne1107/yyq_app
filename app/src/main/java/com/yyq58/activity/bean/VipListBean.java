package com.yyq58.activity.bean;

import java.util.List;

public class VipListBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"money":128,"create_time":"2017-08-20 06:34:27","VIP_ID":"21d629e809174f48bcf51fb2a8b44e8d","name":"会员A套餐","pull_num":700,"day":60,"described":"会员享有积分加权；排名靠前；抢单无限量；尊贵标识；通告优先推送。"},{"money":998,"create_time":"2017-08-20 08:26:02","VIP_ID":"cffb3c1f309f4791a5bc2e0b2642a666","name":"会员B套餐","pull_num":1000,"day":364,"described":"会员享有积分加权；排名靠前；抢单无限量；尊贵标识；通告优先推送。"}]
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
         * money : 128.0
         * create_time : 2017-08-20 06:34:27
         * VIP_ID : 21d629e809174f48bcf51fb2a8b44e8d
         * name : 会员A套餐
         * pull_num : 700
         * day : 60
         * described : 会员享有积分加权；排名靠前；抢单无限量；尊贵标识；通告优先推送。
         */

        private double money;
        private String create_time;
        private String VIP_ID;
        private String name;
        private String pull_num;
        private String day;
        private String described;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getVIP_ID() {
            return VIP_ID;
        }

        public void setVIP_ID(String VIP_ID) {
            this.VIP_ID = VIP_ID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPull_num() {
            return pull_num;
        }

        public void setPull_num(String pull_num) {
            this.pull_num = pull_num;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDescribed() {
            return described;
        }

        public void setDescribed(String described) {
            this.described = described;
        }
    }
}
