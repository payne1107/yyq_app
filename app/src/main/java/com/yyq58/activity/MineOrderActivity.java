package com.yyq58.activity;

import android.os.Bundle;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;

/*
* 我的订单
* */
public class MineOrderActivity extends BaseActivity {
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_order);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的订单", true, true);
    }
}
