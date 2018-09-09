package com.yyq58.activity.bean;

import java.util.List;

public class AllBillDetailsBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-04 16:36:43","money":1,"fromtype":9,"detail_id":"8934bb628e64455298f8b51f87434dba","type":2,"fromid":"07d1f4d2b3b24c9ab2bd24dae68e824c","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-04 16:35:34","money":1,"fromtype":7,"detail_id":"b6349769430f42fb8279768b415b7e71","type":2,"fromid":"07d1f4d2b3b24c9ab2bd24dae68e824c","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-04 10:03:41","money":5,"fromtype":9,"detail_id":"07f3c4f46fce4616b63ae099f6b55a94","type":2,"fromid":"41c57da0c99c4f608b2b3f6d428f207b","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-04 10:01:42","money":5,"fromtype":7,"detail_id":"14344d1e2f604ef6a4b696fa06a08326","type":2,"fromid":"41c57da0c99c4f608b2b3f6d428f207b","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 11:00:10","money":1.5,"fromtype":9,"detail_id":"ea7d17ac6a794da280eefc81d7c3ba9a","type":2,"fromid":"f40644c50cbc48a3b50278a1a8cb3627","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 10:59:09","money":1.5,"fromtype":7,"detail_id":"59901aabe6124f38a3a21c9099e82d34","type":2,"fromid":"f40644c50cbc48a3b50278a1a8cb3627","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 10:57:38","money":1,"fromtype":9,"detail_id":"8fd5da2eacae431caccdd13b4bb3bb1c","type":2,"fromid":"d5ab44700cf642f6a5363ead7ae76153","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 10:55:54","money":1,"fromtype":7,"detail_id":"e9601398427e4d2fbff0244aebff248a","type":2,"fromid":"d5ab44700cf642f6a5363ead7ae76153","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 10:10:27","money":3,"fromtype":9,"detail_id":"8595efad29cb4bed95f2005a01e35d31","type":2,"fromid":"2c73f126c2414b939772647c35cd4144","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-03 10:08:34","money":3,"fromtype":7,"detail_id":"6f3e30aad7124ce6b35571f34b6f3ecf","type":2,"fromid":"2c73f126c2414b939772647c35cd4144","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-02 10:36:53","money":2,"fromtype":9,"detail_id":"2fe828d999d04f1a958a1bbcccf4de41","type":1,"fromid":"24d6bd15a2254c04b0c01a63ba590561","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-02 10:26:27","money":2,"fromtype":9,"detail_id":"8f007fdc1eec4403b5ffe74c511bf9c8","type":2,"fromid":"195d06673e214fabb621cceda554f929","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-02 10:22:44","money":2,"fromtype":7,"detail_id":"66dec6ded73e41d293a1ffdb2f9fb24b","type":2,"fromid":"195d06673e214fabb621cceda554f929","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-06-02 08:08:43","money":1,"fromtype":7,"detail_id":"ec6d80a772be467b8bae1b34231de461","type":2,"fromid":"80c185f7006e4bc0a34f6d32191f27db","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-05-29 11:08:33","money":17,"fromtype":3,"detail_id":"2fc1e70f1879457faf072f7b9f4da290","type":1,"fromid":"f46451390d794203b9961c2b70282e2f","status":2},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-05-26 11:38:47","money":17,"fromtype":3,"detail_id":"71e9e8de2ee74efba3b18c02303295c8","type":2,"fromid":"f46451390d794203b9961c2b70282e2f","status":0},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-05-26 11:12:53","money":1,"fromtype":9,"detail_id":"6ff4a65fca7549a6bd66ba0a48445546","type":1,"fromid":"3211919d5c284441a1376fd5d5de4dfe","status":null},{"uid":"cc8c6f9fa1574a519dc5bdd63c6af8ba","createtime":"2018-05-26 10:34:05","money":1,"fromtype":9,"detail_id":"406d1c958c6e48dca6f553f50f755019","type":2,"fromid":"d51d36fb316f48f0a7b249879ed90a82","status":null}]
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
         * uid : cc8c6f9fa1574a519dc5bdd63c6af8ba
         * createtime : 2018-06-04 16:36:43
         * money : 1.0
         * fromtype : 9
         * detail_id : 8934bb628e64455298f8b51f87434dba
         * type : 2
         * fromid : 07d1f4d2b3b24c9ab2bd24dae68e824c
         * status : null
         */

        private String uid;
        private String createtime;
        private double money;
        private int fromtype;
        private String detail_id;
        private int type;
        private String fromid;
        private Object status;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public int getFromtype() {
            return fromtype;
        }

        public void setFromtype(int fromtype) {
            this.fromtype = fromtype;
        }

        public String getDetail_id() {
            return detail_id;
        }

        public void setDetail_id(String detail_id) {
            this.detail_id = detail_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFromid() {
            return fromid;
        }

        public void setFromid(String fromid) {
            this.fromid = fromid;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }
    }
}
