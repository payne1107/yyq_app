package com.yyq58.activity.bean;

public class UnreadMsgBean {

    /**
     * code : 1000
     * msg : null
     * data : {"createTime":null,"unreadNums":0,"content":"点击查看系统消息"}
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
         * createTime : null
         * unreadNums : 0
         * content : 点击查看系统消息
         */

        private Object createTime;
        private int unreadNums;
        private String content;

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getUnreadNums() {
            return unreadNums;
        }

        public void setUnreadNums(int unreadNums) {
            this.unreadNums = unreadNums;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
