package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yyq58.R;
import com.yyq58.activity.base.BaseActivity;

/***
 * 开通VIP功能
 */
public class OpenVIPActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvChoosePayType;
    private Dialog dialog;
    private Context mContext;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_open_vip);
        mContext = OpenVIPActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("开通VIP", true, true);
        tvChoosePayType = findViewById(R.id.tv_choose_pay_type);


        setListener();
    }

    private void setListener() {
        tvChoosePayType.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_pay_type:
                //选择支付方式
                showInviteDialog();
                break;
        }
    }


    /***
     * 邀请对话框
     */
    private void showInviteDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_choose_pay_type, null);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y =this.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
