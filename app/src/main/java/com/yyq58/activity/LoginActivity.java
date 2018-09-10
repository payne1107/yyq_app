package com.yyq58.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.LoginSucessBean;
import com.yyq58.activity.utils.AppManager;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.SPUtil;
import com.yyq58.activity.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private ImageView ivPhoneClear;
    private EditText etPhone;
    private EditText etPassword;
    private ImageView ivPwdVisible;
    private TextView tvRegister;
    private TextView tvLogin;
    private TextView tvRemeberPwd;
    private boolean isVisble = false;
    private String userPhone;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("登录", true, false);
        ivPhoneClear = (ImageView) findViewById(R.id.iv_phone_clear);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_password);
        ivPwdVisible = (ImageView) findViewById(R.id.iv_pwd_visible);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvRemeberPwd = (TextView) findViewById(R.id.tv_remember_pwd);
        setListener();
    }

    private void setListener() {
        ivPhoneClear.setOnClickListener(this);
        ivPwdVisible.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvRemeberPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_phone_clear:
                etPhone.getText().clear();
                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_remember_pwd:
                //忘记密码
                startActivity(new Intent(mContext, RemberPwdActivity.class));
                break;
            case R.id.iv_pwd_visible:
                //隐藏和显示密码
                setVisiblePwd();
                break;
        }
    }

    /****
     * 登录
     */
    private void login() {
        userPhone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (StringUtils.isEmpty(userPhone) || StringUtils.isEmpty(password)) {
            toastMessage("用户名或者密码不能为空");
            return;
        }
        startIOSDialogLoading(mContext,"登录中..");
        Map<String, String> params = new HashMap<>();
        params.put("phone", userPhone);
        params.put("pwd", password);
        httpPostRequest(ConfigUtil.LOGIN_URL, params, ConfigUtil.LOGIN_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.LOGIN_URL_ACTION:
                Log.d("Dong", "登录成功了吗 ---- 》 " + json);
                LoginSucessBean bean = JSON.parseObject(json, LoginSucessBean.class);
                if (bean != null) {
                    int code = bean.getCode();
                    LoginSucessBean.DataBean data = bean.getData();
                    if (code == 1000) {
                        toastMessage("登录成功");
                        startActivity(new Intent(mContext, MainActivity.class));
                        if (data != null) {
                            String userPhone = data.getPHONENUM();
                            String userId = data.getCONSUMER_ID();
                            SPUtil.putString(mContext, "userPhone", userPhone);
                            SPUtil.putString(mContext, "userId", userId);
                            MyApplication.userPhone = userPhone;
                            MyApplication.userId = userId;
                        }
                        finish();
                    }
                    break;
                }
        }
    }

    /***
     * 设置密码是否显示
     */
    private void setVisiblePwd() {
        isVisble = !isVisble;
        if (isVisble) {
            ivPwdVisible.setImageResource(R.mipmap.icon_visible);
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            ivPwdVisible.setImageResource(R.mipmap.icon_unvisible);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(etPassword.getText().length());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getInstance().finishActivity();
        finish();
    }
}
