package com.yyq58.activity.bean;

public class MineOrderCountBean {

    /**
     * code : 1000
     * msg : null
     * data : {"qdTransactions":"1","notPay":"11","notComplete":"7","qdNotPay":"4","qdComplete":"2","qdNotComplete":"0","transactions":"0","refund":"1"}
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
         * qdTransactions : 1
         * notPay : 11
         * notComplete : 7
         * qdNotPay : 4
         * qdComplete : 2
         * qdNotComplete : 0
         * transactions : 0
         * refund : 1
         */

        private String qdTransactions;
        private String notPay;
        private String notComplete;
        private String qdNotPay;
        private String qdComplete;
        private String qdNotComplete;
        private String transactions;
        private String refund;

        public String getQdTransactions() {
            return qdTransactions;
        }

        public void setQdTransactions(String qdTransactions) {
            this.qdTransactions = qdTransactions;
        }

        public String getNotPay() {
            return notPay;
        }

        public void setNotPay(String notPay) {
            this.notPay = notPay;
        }

        public String getNotComplete() {
            return notComplete;
        }

        public void setNotComplete(String notComplete) {
            this.notComplete = notComplete;
        }

        public String getQdNotPay() {
            return qdNotPay;
        }

        public void setQdNotPay(String qdNotPay) {
            this.qdNotPay = qdNotPay;
        }

        public String getQdComplete() {
            return qdComplete;
        }

        public void setQdComplete(String qdComplete) {
            this.qdComplete = qdComplete;
        }

        public String getQdNotComplete() {
            return qdNotComplete;
        }

        public void setQdNotComplete(String qdNotComplete) {
            this.qdNotComplete = qdNotComplete;
        }

        public String getTransactions() {
            return transactions;
        }

        public void setTransactions(String transactions) {
            this.transactions = transactions;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }
    }
}
