package com.yyq58.activity;

import android.os.Bundle;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("系统设置", true, true);
    }
}
