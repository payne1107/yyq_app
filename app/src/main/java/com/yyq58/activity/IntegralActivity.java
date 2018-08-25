package com.yyq58.activity;

import android.os.Bundle;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;

public class IntegralActivity extends BaseActivity{
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_integral);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("积分说明",true,true);
    }
}
