package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/***
 * 提现
 */
public class WithdrawMoneyActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBankInfo;
    private EditText etMoney;
    private TextView tvBalance;
    private AutoLinearLayout layoutWithdraw;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_withdraw_money);
        mContext = WithdrawMoneyActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("提现", true, true);
        tvBankInfo = findViewById(R.id.tv_bank_info);
        etMoney = findViewById(R.id.et_money);
        tvBalance = findViewById(R.id.tv_balance);
        layoutWithdraw = findViewById(R.id.layout_withdraw);

        setListener();
    }

    private void setListener() {
        tvBankInfo.setOnClickListener(this);
        layoutWithdraw.setOnClickListener(this);
        etMoney.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bank_info:
                //绑定银行卡
                startActivity(new Intent(mContext, BindBankCardInfoActivity.class));
                break;
            case R.id.layout_withdraw:
                //提现
                withdrawPost();
                break;
        }
    }

    /***
     * 提现
     */
    private void withdrawPost() {
        String bankInfo = tvBankInfo.getText().toString().trim();
        String balance = tvBalance.getText().toString().trim();
        String money = etMoney.getText().toString().trim();
        if ("请绑定银行卡".equals(bankInfo)) {
            toastMessage("请先绑定银行卡");
            return;
        }
        if (Double.parseDouble(money) > Double.parseDouble(balance)) {
            toastMessage("提现金额不能大于余额");
            return;
        }
        if (Double.parseDouble(money) <= 10) {
            toastMessage("提现金额不能小于10元");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("money", money);
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.WITHDRAW_BALANCE_URL, params, ConfigUtil.WITHDRAW_BALANCE_URL_ACTION);
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String money = etMoney.getText().toString();
            if (StringUtils.isEmpty(money)) {
                layoutWithdraw.setBackgroundColor(getResources().getColor(R.color.color_e));
            } else {
                layoutWithdraw.setBackgroundColor(getResources().getColor(R.color.color_4b3a75));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryPersonDetails();
    }

    /***
     * 查询个人信息
     */
    private void queryPersonDetails() {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.QUERY_PERSON_INFO_URL, params, ConfigUtil.QUERY_PERSON_INFO_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_PERSON_INFO_URL_ACTION:
                //查询个人信息
                handleQuereyPersonInfo(json);
                break;
            case ConfigUtil.WITHDRAW_BALANCE_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    toastMessage("提现申请成功");
                    finish();
                }
                break;
        }
    }

    /***
     * 个人信息
     * @param json
     */
    private void handleQuereyPersonInfo(String json) {
        PersonDetailsBean bean = JSON.parseObject(json, PersonDetailsBean.class);
        if (bean != null) {
            PersonDetailsBean.DataBean data = bean.getData();
            if (data != null) {
                String bankName = data.getBankName();
                String bankNameNum = data.getBankNameNum();
                if (!StringUtils.isEmpty(bankName) && !StringUtils.isEmpty(bankNameNum)) {
                    tvBankInfo.setText(bankName + "(" + bankNameNum + ")");
                }
            }
        }
    }
}
