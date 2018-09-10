package com.yyq58.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.widget.MyDialog;
import com.zhy.autolayout.AutoLinearLayout;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private AutoLinearLayout layoutExitApp;
    public static boolean IS_EXIT_APP_FLAG = false;

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
        layoutExitApp = findViewById(R.id.layout_exit_app);

        layoutExitApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_exit_app:
                final MyDialog dialog = new MyDialog(SettingActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("确定退出么？");
                dialog.setOnNegativeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clearAppData();
                        dialog.dismiss();
                        startActivity(new Intent(SettingActivity.this,LoginActivity.class));
                        IS_EXIT_APP_FLAG = true;
                        finish();
                    }
                });
                dialog.show();
                break;
        }
    }
}
