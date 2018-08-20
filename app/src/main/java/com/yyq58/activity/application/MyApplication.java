package com.yyq58.activity.application;

import android.app.Application;

import com.android.volley.RequestQueue;

public class MyApplication extends Application{
    public static final String REQUEST_URL = "http://mobile.lieshouupin.com:102/";
    //网络框架
    public static RequestQueue mRequestQueue;
    //是否已经登陆
    public static String isLogin = "";
    //用户的userId
    public static String userId = "";
    //用户的邀请码
    public static String inviteCode = "";
}
