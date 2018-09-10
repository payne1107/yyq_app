package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.zhy.autolayout.AutoLinearLayout;

/*我的财务*/
public class MineFinanceActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutDrawwithBalance;
    private AutoLinearLayout layoutAllOrder;
    private AutoLinearLayout layoutTopup;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_finance);
        mContext = MineFinanceActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的财务", true, true);
        double balance = getIntent().getDoubleExtra("balance", 0);
        TextView tvBalance = findViewById(R.id.tv_balance);
        tvBalance.setText("￥" + balance);

        layoutDrawwithBalance = findViewById(R.id.layout_drawwith_balance);
        layoutAllOrder = findViewById(R.id.layout_all_order);
        layoutTopup = findViewById(R.id.layout_topup);

        setListener();
    }

    private void setListener() {
        layoutDrawwithBalance.setOnClickListener(this);
        layoutAllOrder.setOnClickListener(this);
        layoutTopup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_drawwith_balance:
                //绑定银行卡
                startActivity(new Intent(mContext, WithdrawMoneyActivity.class));
                break;
            case R.id.layout_all_order:
                //所有账单
                startActivity(new Intent(mContext, AllBillDetailsActivity.class));
                break;
            case R.id.layout_topup:
                //充值
                break;
        }
    }
}
