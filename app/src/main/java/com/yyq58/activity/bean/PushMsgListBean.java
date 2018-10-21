package com.yyq58.activity.bean;

import java.util.List;

public class PushMsgListBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"createTime":"2018-10-01 00:39:46","id":"610a2064de204b2f95b7ccd2e824b072","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:39:41","id":"3f87216ff5a5423ebefd7d8cd42c9fa2","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:39:02","id":"247ebd95238b478182bc221819b48d82","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:38:51","id":"3ba35dd7d27945ceb7e742571385b497","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:38:44","id":"aba25db5cda1477097199ff33d06106f","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:30:48","id":"075db97fb6d345f9a38826ebb2f05b20","type":1,"fromId":null,"content":"长期提供一切临时兼职资源用工"},{"createTime":"2018-10-01 00:30:14","id":"4a0033d3d303432d84dbbe212f6040ed","type":1,"fromId":null,"content":"长期提供一切临时兼职资源用工"},{"createTime":"2018-10-01 00:29:52","id":"57a8cde9561041d99a03f2b43b797697","type":1,"fromId":null,"content":"长期提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:29:47","id":"ac05a1bfda454b0fa5e99aa0e2e30ddf","type":1,"fromId":null,"content":"长期提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:28:50","id":"2cd1ccbc4d2a4cebbf4016f70bd8da55","type":1,"fromId":null,"content":"长期提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:28:25","id":"31c7a2c4713343e0b7beb4633db10879","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:28:10","id":"4b35c84c05ca4c5987092715030800a2","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-10-01 00:27:57","id":"8819fa3b01b64d149cec215a30903263","type":1,"fromId":null,"content":"长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！\n专业的人力资源服务团队，为您提供人力资源服务！"},{"createTime":"2018-08-27 11:23:14","id":"7ced0b8c99cb42908567a0d34aa2ab47","type":1,"fromId":null,"content":"寻合肥实力活动策划公司或领队\n节目预算：2万左右\n时间：9月24日 中秋晚宴\n节目时长 ：1个小时左右\n带节目单私 微信 yanyiquan888"},{"createTime":"2018-08-22 18:05:55","id":"19d26e1277654398801e62cb8a8f09d4","type":1,"fromId":null,"content":"内衣主题拍摄，价格优。要求气质身材好。"},{"createTime":"2018-08-22 18:05:47","id":"6b0866971093431aad3926707e4986e2","type":1,"fromId":null,"content":"内衣主题拍摄，价格优。要求气质身材好。"},{"createTime":"2018-08-22 18:04:16","id":"a860888ccbd4484eb964e8d5184e6b76","type":1,"fromId":null,"content":"内衣主题拍摄。"},{"createTime":"2018-08-17 22:42:12","id":"2bf79c210d194aa58acacf43978824bc","type":1,"fromId":null,"content":"运动达人 自报价"},{"createTime":"2018-08-17 17:30:16","id":"cb894f99e7b9436e97d4dd647037d9e3","type":1,"fromId":null,"content":"沙画沙画 自报价"},{"createTime":"2018-08-17 17:29:26","id":"0baf6e37d4e04c32bb138ee1ff5e113a","type":1,"fromId":null,"content":"沙画沙画 自报价"}]
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
         * createTime : 2018-10-01 00:39:46
         * id : 610a2064de204b2f95b7ccd2e824b072
         * type : 1
         * fromId : null
         * content : 长期承接活动会展，临时安保，活动宣传，服务人员等，提供一切临时兼职资源用工服务！
         专业的人力资源服务团队，为您提供人力资源服务！
         */

        private String createTime;
        private String id;
        private int type;
        private Object fromId;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getFromId() {
            return fromId;
        }

        public void setFromId(Object fromId) {
            this.fromId = fromId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
