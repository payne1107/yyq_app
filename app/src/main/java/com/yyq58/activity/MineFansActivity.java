package com.yyq58.activity;

import android.os.Bundle;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;

/*我的粉丝
 * */
public class MineFansActivity extends BaseActivity {

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_fans);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的粉丝", true, true);
    }
}
