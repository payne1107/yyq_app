package com.yyq58.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/*我的财务*/
public class MineFinanceActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutDrawwithBalance;
    private AutoLinearLayout layoutAllOrder;
    private AutoLinearLayout layoutTopup;
    private Context mContext;
    private TextView tvBalance;

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
        tvBalance = findViewById(R.id.tv_balance);
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
                double balance = data.getChanges(); //余额
                tvBalance.setText("￥" + balance);
            }
        }
    }
}
