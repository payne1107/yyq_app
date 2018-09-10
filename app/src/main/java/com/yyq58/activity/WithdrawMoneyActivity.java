package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.utils.StringUtils;
import com.zhy.autolayout.AutoLinearLayout;

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
                break;
        }
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
}
