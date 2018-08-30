package com.yyq58.activity.utils;


import com.yyq58.activity.application.MyApplication;

/**
 * Created by Administrator on 2017/9/29 0029.
 * 网络地址存放
 */

public class ConfigUtil {
    public static long TenYears8 = 10L * 365 * 1000 * 60 * 60 * 24L * 80;
    public static long TenYears = 10L * 365 * 1000 * 60 * 60 * 24L * 3 ;
    public static String sessionId;
    public static final String SAVE_CITY_ID="city_id";
    public static final String SAVE_CITY_NAME="city_name";

    /****
     * 注册短信验证码接口
     */
    public static final String GET_REGISTER_SMS_URL = MyApplication.REQUEST_URL + "appv1Consumer/smsReg.do";
    public static final int GET_REGISTER_SMS_URL_ACTION = 1;
    /*****
     * 注册接口
     */
    public static final String REGISTER_URL = MyApplication.REQUEST_URL + "appv1Consumer/appRegister.do";
    public static final int REGISTER_URL_ACTION = 2;
    /****
     * 登陆接口
     */
    public static final String LOGIN_URL = MyApplication.REQUEST_URL + "applogin/userLogin.do";
    public static final int LOGIN_URL_ACTION = 3;
    /****
     * 上传头像和图片
     */
    public static final String UPLOAD_PIC_URL = MyApplication.REQUEST_URL + "appv1zone/upload.do";
    public static final int UPLOAD_PIC_URL_ACTION = 4;
    /***
     * 获取我的才艺 台前和幕后
     */
    public static final String QUERY_MINE_ACQUIREMENT_URL = MyApplication.REQUEST_URL + "appv1consumerlabel/getAllLabel.do";
    public static final int QUERY_MINE_ACQUIREMENT_URL_ACTION = 5;
    /****
     * 忘记密码 ->获取验证码
     */
    public static final String GET_REMEBER_PWD_VERITIFY_CODE_URL = MyApplication.REQUEST_URL + "appv1Consumer/smsPwd.do";
    public static final int GET_REMEBER_PWD_VERITIFY_CODE_URL_ACTION = 6;
    /****
     * 找回密码--->重置密码
     */
    public static final String RESET_PWD_URL = MyApplication.REQUEST_URL + "appv1Consumer/resetPwd.do";
    public static final int RESET_PWD_URL_ACTION = 7;
    /***
     * 获取首页通告列表
     */
    public static final String QUERY_APPV1_NOTICE_LIST_URL = MyApplication.REQUEST_URL + "appv1notice/list.do";
    public static final int QUERY_APPV1_NOTICE_LIST_URL_ACTION = 8;


}
