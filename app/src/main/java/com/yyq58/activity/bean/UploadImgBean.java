package com.yyq58.activity.bean;

import java.util.List;

public class UploadImgBean {


    /**
     * code : 1000
     * msg : null
     * data : ["http://wxapi.yyq58.com/static/images/avatar/2018/8/25/03d3697d-d24e-4b37-9f44-a1bce66affac.octet-stream"]
     * result : true
     */

    private int code;
    private Object msg;
    private boolean result;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
