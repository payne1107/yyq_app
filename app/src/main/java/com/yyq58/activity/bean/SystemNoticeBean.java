package com.yyq58.activity.bean;

import java.util.List;

public class SystemNoticeBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"createTime":"2018-06-25 17:08:31","id":"83ab0ef0a3204434b4466c08b6e1e953","content":"这个是一条系统消息16"},{"createTime":"2018-04-18 22:40:16","id":"85964e1517114f50ab9ee1fbd93720e5","content":"这个是一条系统消息25"},{"createTime":"2018-04-18 22:40:10","id":"9786872033e24a0dbd7e9ea40eeb520c","content":"这个是一条系统消息24"},{"createTime":"2018-04-18 22:40:05","id":"9c32436c689d4ffdb2afd1c8640bb05d","content":"这个是一条系统消息23"},{"createTime":"2018-04-18 22:40:00","id":"71981a8280d24a06a7ea58ebe8f6b7b2","content":"这个是一条系统消息22"},{"createTime":"2018-04-18 22:39:56","id":"071d16e6591b4fc8ab8eb7894528916c","content":"这个是一条系统消息21"},{"createTime":"2018-04-18 22:39:52","id":"278b18452366438f925d087967efcd36","content":"这个是一条系统消息20"},{"createTime":"2018-04-18 22:39:47","id":"0fb3157efa4d410780208c48f9480d49","content":"这个是一条系统消息19"},{"createTime":"2018-04-18 22:39:43","id":"c0251f3f4c6d442fb1a5067363505fe9","content":"这个是一条系统消息18\r\n"},{"createTime":"2018-04-18 22:39:37","id":"9affa7c737624aa88d64b7c1294c1a69","content":"这个是一条系统消息17"},{"createTime":"2018-04-18 22:39:31","id":"069862654624452a841f4b0c6cf77924","content":"这个是一条系统消息16"},{"createTime":"2018-04-18 22:39:27","id":"8b9a0359faf746738986190a0b70493b","content":"这个是一条系统消息15"},{"createTime":"2018-04-18 22:39:22","id":"f34450ff7150403f8b5967e50731f168","content":"这个是一条系统消息14"},{"createTime":"2018-04-18 22:39:17","id":"7aff296463de40d48db7219389988736","content":"这个是一条系统消息13"},{"createTime":"2018-04-18 22:39:12","id":"8b490d4114c348349199b369416b8a40","content":"这个是一条系统消息12"},{"createTime":"2018-04-18 22:39:07","id":"fbaee81a08d34eea872a9be9190caa9a","content":"这个是一条系统消息11"},{"createTime":"2018-04-18 22:39:01","id":"4028cb1a60d74d828ca42f9576f8adf9","content":"这个是一条系统消息10"},{"createTime":"2018-04-18 22:38:56","id":"e365165aded549999db2231462fe5d66","content":"这个是一条系统消息9"},{"createTime":"2018-04-18 22:38:51","id":"2f4a42d95bec42d286a0abe778332647","content":"这个是一条系统消息8"},{"createTime":"2018-04-18 22:38:47","id":"9149e84c79154eb38b0941f707f1e68e","content":"这个是一条系统消息7"}]
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
         * createTime : 2018-06-25 17:08:31
         * id : 83ab0ef0a3204434b4466c08b6e1e953
         * content : 这个是一条系统消息16
         */

        private String createTime;
        private String id;
        private String content;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
