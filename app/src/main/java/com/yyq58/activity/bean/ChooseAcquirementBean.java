package com.yyq58.activity.bean;

import java.util.List;

public class ChooseAcquirementBean {

    /**
     * code : 1000
     * msg : null
     * data : [{"labelId":"0d088e3734424f44a27af358895686c4","caiyiType":0,"labelType":1,"labelName":"D J","type":1},{"labelId":"31a2cd63731a4ea4b1d82d9c93bdfcb0","caiyiType":0,"labelType":1,"labelName":"演员","type":1},{"labelId":"3463f8438ada424d801a5a37908864e4","caiyiType":0,"labelType":1,"labelName":"舞狮/ 鼓舞","type":1},{"labelId":"36ecbfa22d6b465da97be9ed486cdcd6","caiyiType":0,"labelType":1,"labelName":"乐队","type":1},{"labelId":"442302fad2f84814a36eb881c63609b9","caiyiType":1,"labelType":1,"labelName":"舞蹈","type":1},{"labelId":"474fa6469414412fbbcb0cf7c69f4092","caiyiType":0,"labelType":1,"labelName":"沙画","type":1},{"labelId":"63b449506e0a45288384016a663a1f37","caiyiType":0,"labelType":1,"labelName":"小品","type":1},{"labelId":"65ce509091bb4e27a524fabcfb8240d6","caiyiType":0,"labelType":1,"labelName":"二人转","type":1},{"labelId":"65f4ee3877bc4d9b91557f3a037ab624","caiyiType":0,"labelType":1,"labelName":"表演团","type":1},{"labelId":"769d0e0fbeba44f088c9a10848e7cf04","caiyiType":0,"labelType":1,"labelName":"相声","type":1},{"labelId":"7f57918b55304288a652f764905822a3","caiyiType":2,"labelType":1,"labelName":"模特","type":1},{"labelId":"97f82434fb1241599332add454575f6f","caiyiType":0,"labelType":1,"labelName":"戏剧","type":1},{"labelId":"9b654f8f4e35476d9c874e7d97efcdf4","caiyiType":0,"labelType":1,"labelName":"礼仪","type":1},{"labelId":"a283d75ebf974fbdb34ac2604b96152d","caiyiType":0,"labelType":1,"labelName":"变脸","type":1},{"labelId":"a597e3509ad242d38bd85d2d300fb1ad","caiyiType":0,"labelType":1,"labelName":"运动","type":1},{"labelId":"ab3755de45434f8c95db8cef3826b4c4","caiyiType":0,"labelType":1,"labelName":"真人秀","type":1},{"labelId":"b286120e8b0d44cead90ddf1c8e6a0cf","caiyiType":0,"labelType":1,"labelName":"乐器","type":1},{"labelId":"b9fcb5d015384d98a5589f287a84e221","caiyiType":4,"labelType":1,"labelName":"选秀/ 赛事","type":1},{"labelId":"bcd61568359b4d0c82b72df317d90ea3","caiyiType":0,"labelType":1,"labelName":"画像","type":1},{"labelId":"bce0cbbf2b914a33ac1b0a85a7d53ec0","caiyiType":0,"labelType":1,"labelName":"小丑","type":1},{"labelId":"c02157588cf04594bcfc65a834d10a62","caiyiType":0,"labelType":1,"labelName":"杂技","type":1},{"labelId":"cb26ab29a3b14a2eb26a79e9ddf3a089","caiyiType":0,"labelType":1,"labelName":"COS","type":1},{"labelId":"cdb6407c282d4cc3a82d9756f018c0ca","caiyiType":0,"labelType":2,"labelName":"化妆/ 服装","type":1},{"labelId":"d16f7aa9ad094325940d1a32fff55e91","caiyiType":0,"labelType":1,"labelName":"特约/ 群演","type":1},{"labelId":"d51303845ed4473886fecf94b17f192e","caiyiType":0,"labelType":2,"labelName":"摄像/ 摄影","type":1},{"labelId":"d5d2a3232df04a3ca984ebb236239e27","caiyiType":0,"labelType":1,"labelName":"模仿秀","type":1},{"labelId":"d6fa927855d04e818ceff01c360e9078","caiyiType":0,"labelType":1,"labelName":"魔术","type":1},{"labelId":"da734e12d34345c984e8b0ec560790a0","caiyiType":0,"labelType":1,"labelName":"调酒/ 茶艺师","type":1},{"labelId":"df3d18df7e314a8aafa9d10d36ace442","caiyiType":0,"labelType":2,"labelName":"道具/ 音响","type":1},{"labelId":"e17315760af440dda56ffc86c4006e63","caiyiType":0,"labelType":1,"labelName":"音乐","type":1},{"labelId":"e78df4901491446782de050feae25744","caiyiType":0,"labelType":1,"labelName":"武术","type":1},{"labelId":"ec1cbe7401a84c599d1520c7ac0aac82","caiyiType":0,"labelType":1,"labelName":"脱口秀","type":1},{"labelId":"f5f4e1e900174043be6e456a166db19d","caiyiType":0,"labelType":1,"labelName":"歌手","type":1},{"labelId":"fa931778122a43f4aba3bf70ece9b826","caiyiType":0,"labelType":2,"labelName":"导演/ 制片人","type":1},{"labelId":"fc56aa0da1244244891693ac0f475faf","caiyiType":0,"labelType":1,"labelName":"网红/ 主播","type":1},{"labelId":"fd868f1a7f544c5dad38b074213ebc71","caiyiType":0,"labelType":1,"labelName":"其他","type":1},{"labelId":"ff7580349be5413980a6b8ecbec88a27","caiyiType":4,"labelType":1,"labelName":"主持/ 司仪","type":1}]
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
         * labelId : 0d088e3734424f44a27af358895686c4
         * caiyiType : 0
         * labelType : 1
         * labelName : D J
         * type : 1
         */

        private String labelId;
        private int caiyiType;
        private int labelType;
        private String labelName;
        private int type;

        public String getLabelId() {
            return labelId;
        }

        public void setLabelId(String labelId) {
            this.labelId = labelId;
        }

        public int getCaiyiType() {
            return caiyiType;
        }

        public void setCaiyiType(int caiyiType) {
            this.caiyiType = caiyiType;
        }

        public int getLabelType() {
            return labelType;
        }

        public void setLabelType(int labelType) {
            this.labelType = labelType;
        }

        public String getLabelName() {
            return labelName;
        }

        public void setLabelName(String labelName) {
            this.labelName = labelName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
