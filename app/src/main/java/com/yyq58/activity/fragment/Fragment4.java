package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yyq58.R;
import com.yyq58.activity.IntegralActivity;
import com.yyq58.activity.MineAttentionActivity;
import com.yyq58.activity.MineFansActivity;
import com.yyq58.activity.MineFinanceActivity;
import com.yyq58.activity.MineOrderActivity;
import com.yyq58.activity.OpenVIPActivity;
import com.yyq58.activity.PersonCenterActivity;
import com.yyq58.activity.SettingActivity;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.widget.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;

public class Fragment4 extends BaseFragment implements View.OnClickListener {

    private ImageView ivSetting;
    private CircleImageView circleImageView;
    private AutoLinearLayout layoutVIP;
    private AutoLinearLayout layoutMineOrder;
    private AutoLinearLayout layoutMineFinance;
    private AutoLinearLayout layoutFans;
    private AutoLinearLayout layoutAttention;
    private AutoLinearLayout layoutOrder;
    private AutoLinearLayout layoutBalance;
    private AutoLinearLayout layoutFS;
    private AutoLinearLayout layoutIntegral;

    @Override
    public View onCustomCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment4, null);
        }
        //缓存的rootView需要判断是否已经被加过parent,如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setListener();
    }

    private void initView() {
        ivSetting = mRootView.findViewById(R.id.iv_settting);
        circleImageView = mRootView.findViewById(R.id.circleImageView);
        layoutVIP = mRootView.findViewById(R.id.layout_vip_member);
        layoutMineOrder = mRootView.findViewById(R.id.layotu_mine_order);
        layoutMineFinance = mRootView.findViewById(R.id.layout_mine_finance);
        layoutFans = mRootView.findViewById(R.id.layout_fans);
        layoutAttention = mRootView.findViewById(R.id.layout_attention);
        layoutOrder = mRootView.findViewById(R.id.layout_order);
        layoutBalance = mRootView.findViewById(R.id.layout_balance);
        layoutFS = mRootView.findViewById(R.id.layout_fensi);
        layoutIntegral = mRootView.findViewById(R.id.layout_integral);

    }

    private void setListener() {
        ivSetting.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
        layoutVIP.setOnClickListener(this);
        layoutMineOrder.setOnClickListener(this);
        layoutMineFinance.setOnClickListener(this);
        layoutFans.setOnClickListener(this);
        layoutAttention.setOnClickListener(this);
        layoutOrder.setOnClickListener(this);
        layoutBalance.setOnClickListener(this);
        layoutFS.setOnClickListener(this);
        layoutIntegral.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_settting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.circleImageView:
                startActivity(new Intent(getActivity(), PersonCenterActivity.class));
                break;
            case R.id.layout_vip_member:
                startActivity(new Intent(getActivity(), OpenVIPActivity.class));
                break;
            case R.id.layotu_mine_order:
            case R.id.layout_order:
                //我的订单
                startActivity(new Intent(getActivity(), MineOrderActivity.class));
                break;
            case R.id.layout_mine_finance:
            case R.id.layout_balance:
                //我的财务
                startActivity(new Intent(getActivity(), MineFinanceActivity.class));
                break;
            case R.id.layout_fans:
            case R.id.layout_fensi:
                //我的粉丝
                startActivity(new Intent(getActivity(), MineFansActivity.class));
                break;
            case R.id.layout_attention:
                //我关注的人
                startActivity(new Intent(getActivity(), MineAttentionActivity.class));
                break;
            case R.id.layout_integral:
                //积分描述
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
        }
    }
}
