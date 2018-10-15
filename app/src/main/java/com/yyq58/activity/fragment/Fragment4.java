package com.yyq58.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.yyq58.R;
import com.yyq58.activity.IntegralActivity;
import com.yyq58.activity.MineAttentionActivity;
import com.yyq58.activity.MineFansActivity;
import com.yyq58.activity.MineFinanceActivity;
import com.yyq58.activity.MineOrderActivity;
import com.yyq58.activity.OpenVIPActivity;
import com.yyq58.activity.PersonCenterActivity;
import com.yyq58.activity.ScheduleManagementActivity;
import com.yyq58.activity.SettingActivity;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseFragment;
import com.yyq58.activity.bean.PersonDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.MyDialog;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

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
    private TextView tvRefreshVip;
    private TextView tvUsername;
    private TextView tvWorkCategory;
    private TextView tvBalance;
    private TextView tvJifenNum;
    private TextView tvFansNum;
    private TextView tvOrderNum;
    private TextView tvGuanzhuNum;
    private TextView tvFansNum2;
    private TextView tvBalance2;
    private TextView tvOrderNum2;
    private ImageView ivSex;
    private double balance;
    private AutoLinearLayout layoutCalendar;
    private String avatar;
    private String account;

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
        tvRefreshVip = mRootView.findViewById(R.id.tv_refresh_vip);
        tvUsername = mRootView.findViewById(R.id.tv_username);
        tvWorkCategory = mRootView.findViewById(R.id.tv_work_category);
        tvBalance = mRootView.findViewById(R.id.tv_balance);
        tvJifenNum = mRootView.findViewById(R.id.tv_jifen_num);
        tvFansNum = mRootView.findViewById(R.id.tv_fans_num);
        tvOrderNum = mRootView.findViewById(R.id.tv_order_num);
        tvGuanzhuNum = mRootView.findViewById(R.id.tv_guanzhu_num);
        tvFansNum2 = mRootView.findViewById(R.id.tv_fans_num2);
        tvBalance2 = mRootView.findViewById(R.id.tv_balance2);
        tvOrderNum2 = mRootView.findViewById(R.id.tv_order_num2);
        ivSex = mRootView.findViewById(R.id.iv_sex);
        layoutCalendar = mRootView.findViewById(R.id.layout_calendar);
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
        tvRefreshVip.setOnClickListener(this);
        layoutCalendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_settting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.circleImageView:
                startActivity(new Intent(getActivity(), PersonCenterActivity.class).putExtra("userId", MyApplication.userId));
                break;
            case R.id.layout_vip_member:
                startActivity(new Intent(getActivity(), OpenVIPActivity.class).putExtra("username", account).putExtra("avatarUrl", avatar));
                break;
            case R.id.layotu_mine_order:
            case R.id.layout_order:
                //我的订单
                startActivity(new Intent(getActivity(), MineOrderActivity.class));
                break;
            case R.id.layout_mine_finance:
            case R.id.layout_balance:
                //我的财务
                startActivity(new Intent(getActivity(), MineFinanceActivity.class).putExtra("balance", balance));
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
            case R.id.tv_refresh_vip:
                //刷新vip
                refreshVipDialog();
                break;
            case R.id.layout_calendar:
                startActivity(new Intent(getActivity(), ScheduleManagementActivity.class));
                break;
        }
    }

    /***
     * 刷新vip对话框
     */
    private void refreshVipDialog() {
        final MyDialog dialog = new MyDialog(getActivity());
        dialog.setTitle("刷新提示");
        dialog.setMessage("确定要刷新个人信息吗，个人信息刷新会消耗会员次数.");
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshVip();
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        queryPersonDetails();
    }

    /***
     * 查询个人信息
     */
    private void queryPersonDetails() {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.QUERY_PERSON_INFO_URL, params, ConfigUtil.QUERY_PERSON_INFO_URL_ACTION);
    }

    /****
     * 刷新vip
     */
    private void refreshVip() {
        Map<String, String> params = new HashMap<>();
        params.put("uid", MyApplication.userId);
        httpPostRequest(ConfigUtil.REFRESH_VIP_URL, params, ConfigUtil.REFRESH_VIP_URL_ACTION);
    }

    @Override
    public void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.REFRESH_VIP_URL_ACTION:
                if (getRequestCode(json) == 1000) {
                    Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_LONG).show();
                }
                break;
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
                account = data.getAccount();
                avatar = data.getAvatar();
                String sex = data.getSex();
                //余额
                balance = data.getChanges();
                String orderNum = data.getOrderNums();//订单
                String fansNum = data.getFansNums();//粉丝
                String jifen = data.getJifen();//积分
                String attendNums = data.getAttentionNums();//我关注的人
                String motto = data.getMotto();//心情

                tvUsername.setText(StringUtils.isEmpty(account) ? "" : account);
                tvWorkCategory.setText(StringUtils.isEmpty(motto) ? "" : motto);
                if (!StringUtils.isEmpty(avatar)) {
                    MyApplication.imageLoader.displayImage(avatar, circleImageView);
                }
                tvBalance.setText("" + balance);
                tvBalance2.setText("" + balance + "元");
                tvJifenNum.setText("" + jifen);
                tvFansNum.setText(StringUtils.isEmpty(fansNum) ? "" : fansNum);
                tvFansNum2.setText(StringUtils.isEmpty(fansNum) ? "" : fansNum);
                tvOrderNum.setText(StringUtils.isEmpty(orderNum) ? "" : orderNum);
                tvOrderNum.setText(StringUtils.isEmpty(orderNum) ? "" : orderNum);
                tvOrderNum2.setText(StringUtils.isEmpty(orderNum) ? "" : orderNum);
                tvGuanzhuNum.setText(StringUtils.isEmpty(attendNums) ? "" : attendNums);
                if (sex.equals("1")) {
                    ivSex.setImageResource(R.mipmap.icon_male);
                } else {
                    ivSex.setImageResource(R.mipmap.icon_female);
                }
            }
        }
    }
}
