package com.yyq58.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

public class RemberPwdActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutUpdatePwd;
    private EditText etPwd;
    private TextView tvGetCode;
    private EditText etVerifyCode;
    private EditText etPhone;
    boolean isRun = true;//是否在获取验证码中
    private int count;
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activtiy_remeber_pwd);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("密码找回", true, true);
        etPhone = findViewById(R.id.et_phone);
        etVerifyCode = findViewById(R.id.et_verify_code);
        tvGetCode = findViewById(R.id.tv_verify_code);
        etPwd = findViewById(R.id.et_pwd);
        layoutUpdatePwd = findViewById(R.id.layout_update_pwd);

        setListener();
    }

    private void setListener() {
        tvGetCode.setOnClickListener(this);
        layoutUpdatePwd.setOnClickListener(this);
        etPhone.addTextChangedListener(new MyTextWatcher());
        etPwd.addTextChangedListener(new MyTextWatcher());
        etVerifyCode.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_code:
                //获取验证码
                getCheckCode();
                break;
            case R.id.layout_update_pwd:
                //确定修改密码
                updatePwd();
                break;
        }
    }

    private void updatePwd() {
        String phone = etPhone.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        String code = etVerifyCode.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("code", code);
        params.put("pwd", pwd);
        httpPostRequest(ConfigUtil.RESET_PWD_URL,params,ConfigUtil.RESET_PWD_URL_ACTION);
    }

    /***
     * 获取验证码
     */
    private void getCheckCode() {
        String userPhone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(userPhone) || userPhone.length() != 11) {
            toastMessage("请输入正确的手机号");
            return;
        }
        codeUnClick();
        Map<String, String> params = new HashMap<>();
        params.put("userPhone", userPhone);
        httpPostRequest(ConfigUtil.GET_REMEBER_PWD_VERITIFY_CODE_URL, params, ConfigUtil.GET_REMEBER_PWD_VERITIFY_CODE_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.GET_REMEBER_PWD_VERITIFY_CODE_URL_ACTION:
                //获取验证码...
                break;
            case ConfigUtil.RESET_PWD_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("密码重置成功");
                    finish();
                }
                break;
        }
    }

    /**
     * 获取验证码不能点击
     */
    private void codeUnClick() {
        tvGetCode.setTextColor(getResources().getColor(R.color.text_color_9));
        tvGetCode.setClickable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    count++;
                    Message msg = Message.obtain();
                    msg.arg1 = count;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (count >= 59) {
                        isRun = false;
                    }
                }
                Message msg = Message.obtain();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 != -1) {
                int i = 60 - msg.arg1;
                tvGetCode.setText(i + "s后重发");
            } else {
                tvGetCode.setText("获取验证码");
                tvGetCode.setTextColor(getResources().getColor(R.color.white));
                tvGetCode.setClickable(true);
                count = 0;
                isRun = true;
            }
        }
    };

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String username = etPhone.getText().toString().trim();
            String pwd = etPwd.getText().toString().trim();
            String code = etVerifyCode.getText().toString().trim();
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(pwd)  && !StringUtils.isEmpty(code)) {
                //下一步按钮点亮
                layoutUpdatePwd.setBackgroundColor(getResources().getColor(R.color.color_4b3a75));
            } else {
                //下一步按钮灰暗色
                layoutUpdatePwd.setBackgroundColor(getResources().getColor(R.color.cccccc));
            }
        }
    }
}
