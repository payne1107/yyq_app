package com.yyq58.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/***
 * 绑定银行卡页面
 */
public class BindBankCardInfoActivity extends BaseActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etBankcard;
    private TextView tvChooseBankInfo;
    private EditText etSubbankName;
    private AutoLinearLayout layoutSave;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_bind_bank_card_info);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("添加银行卡", true, true);
        etName = findViewById(R.id.et_name);
        etBankcard = findViewById(R.id.et_bank_card);
        tvChooseBankInfo = findViewById(R.id.tv_choose_bank_info);
        etSubbankName = findViewById(R.id.et_sub_band_name);
        layoutSave = findViewById(R.id.layout_save);

        setListener();
    }

    private void setListener() {
        tvChooseBankInfo.setOnClickListener(this);
        layoutSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_bank_info:
                //选择银行信息

                break;
            case R.id.layout_save:
                //保存银行卡信息
                saveBankcardInfo();
                break;
        }
    }

    /***
     * 保存银行卡信息
     */
    private void saveBankcardInfo() {
        String bankcard =etBankcard.getText().toString().trim();
        String username =etName.getText().toString().trim();
        String subBankName = etSubbankName.getText().toString().trim();
        String bankInfo =tvChooseBankInfo.getText().toString().trim();

        if (StringUtils.isEmpty(bankcard)) {
            toastMessage("银行卡信息不能为空");
            return;
        }
        if (StringUtils.isEmpty(username)) {
            toastMessage("用户姓名不能为空");
            return;
        }
        if ("请选择".equals(bankInfo)) {
            toastMessage("请选择银行名称");
            return;
        }

        if (StringUtils.isEmpty(subBankName)) {
            toastMessage("请输入支行名称");
            return;
        }

        startIOSDialogLoading(BindBankCardInfoActivity.this, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("bankname",bankInfo);
        params.put("banknamenum",bankcard);
        params.put("bankpalce", subBankName);
        params.put("truename", username);
        httpPostRequest(ConfigUtil.BIND_BANK_CARD_URL, params, ConfigUtil.BIND_BANK_CARD_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.BIND_BANK_CARD_URL_ACTION:
                Log.d("Dong", "绑定银行卡--》" +json);
                if (getRequestCode(json) == 1000) {
                    toastMessage("绑定成功");
                    finish();
                }
                break;
        }
    }
}
