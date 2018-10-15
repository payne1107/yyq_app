package com.yyq58.activity.bean;

public class BuyVipBean {

    /**
     * code : 1000
     * msg : 订单生成去第三方支付
     * data : {"orderNo":"1051825811809632256","appid":"wxaaeca438918db105","timestamp":"1539609790","noncestr":"dd7e60326d754f22938fbcc28e960d2d","partnerid":"1500136582","sign":"598B2F4C8F64BE5C67FEA43461E88E9C","packageValue":"prepay_id=wx15212310355524f6919e22ca3169506194","prepayid":"wx15212310355524f6919e22ca3169506194"}
     * result : true
     */

    private int code;
    private String msg;
    private DataBean data;
    private boolean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
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
         * orderNo : 1051825811809632256
         * appid : wxaaeca438918db105
         * timestamp : 1539609790
         * noncestr : dd7e60326d754f22938fbcc28e960d2d
         * partnerid : 1500136582
         * sign : 598B2F4C8F64BE5C67FEA43461E88E9C
         * packageValue : prepay_id=wx15212310355524f6919e22ca3169506194
         * prepayid : wx15212310355524f6919e22ca3169506194
         */

        private String orderNo;
        private String appid;
        private String timestamp;
        private String noncestr;
        private String partnerid;
        private String sign;
        private String packageValue;
        private String prepayid;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPackageValue() {
            return packageValue;
        }

        public void setPackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }
    }
}
