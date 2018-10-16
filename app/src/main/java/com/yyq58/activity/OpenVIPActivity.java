package com.yyq58.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yyq58.R;
import com.yyq58.activity.adapter.VipListAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.VipListBean;
import com.yyq58.activity.bean.WXPayBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.utils.StringUtils;
import com.yyq58.activity.widget.CircleImageView;
import com.yyq58.activity.widget.IButtonClickListener;
import com.yyq58.activity.widget.MyListView;
import com.yyq58.activity.wxapi.WXPayEntryActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 开通VIP功能
 */
public class OpenVIPActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvChoosePayType;
    private Dialog dialog;
    private Context mContext;
    private TextView tvUserName;
    private CircleImageView ivAvatar;
    private MyListView listView;
    private List<VipListBean.DataBean> mList = new ArrayList<>();
    private VipListAdapter adapter;
    private int payType = 3;// 1：余额 2.支付宝 3.微信支付

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_open_vip);
        mContext = OpenVIPActivity.this;
    }

    @Override
    protected void initData() {
        queryVipList();
    }

    @Override
    protected void initView() {
        super.initView();
        String userName = getIntent().getStringExtra("username");
        String avaatarUrl = getIntent().getStringExtra("avatarUrl");
        setInVisibleTitleIcon("开通VIP", true, true);
        tvChoosePayType = findViewById(R.id.tv_choose_pay_type);
        tvUserName = findViewById(R.id.tv_username);
        ivAvatar = findViewById(R.id.circleImageView);
        tvUserName.setText(StringUtils.isEmpty(userName) ? "" : userName);
        if (!StringUtils.isEmpty(avaatarUrl)) {
            MyApplication.imageLoader.displayImage(avaatarUrl, ivAvatar);
        }

        listView = findViewById(R.id.listView);
        adapter = new VipListAdapter(mContext, mList);
        listView.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        tvChoosePayType.setOnClickListener(this);
        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                if (payType == 2) {
                    toastMessage("暂未开通支付宝支付");
                    return;
                }
                VipListBean.DataBean bean = mList.get(position);
                if (bean != null) {
                    String vipId = bean.getVIP_ID();
                    openupVip(vipId, payType);
                }
            }

            @Override
            public void onDeleClick(View view, int position) {

            }

            @Override
            public void onSaveClick(View view, int position) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_pay_type:
                //选择支付方式
                showInviteDialog();
                break;
            case R.id.tv_choose_pay_type1:
                payType = 1;//余额支付
                tvChoosePayType.setText("余额支付");
                dialog.dismiss();
                break;
            case R.id.tv_choose_pay_type2:
                payType = 3; //微信支付
                tvChoosePayType.setText("微信支付");
                dialog.dismiss();
                break;
            case R.id.tv_choose_pay_type3:
                 payType = 2; //支付宝支付
                 tvChoosePayType.setText("支付宝支付");
                 dialog.dismiss();
                break;
        }
    }

    /***
     * 开通vip
     */
    private void openupVip(String vipId,int type) {
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        params.put("vipId", vipId);
        params.put("type", String.valueOf(type));
        httpPostRequest(ConfigUtil.BUY_APP_VIP_URL, params, ConfigUtil.BUY_APP_VIP_URL_ACTION);
    }

    /***
     * 查询vip套餐列表
     */
    private void queryVipList() {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("random", "123");
        httpPostRequest(ConfigUtil.QUERY_VIP_LIST_URL, params, ConfigUtil.QUERY_VIP_LIST_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_VIP_LIST_URL_ACTION:
                handleQueryVipList(json);
                break;
            case ConfigUtil.BUY_APP_VIP_URL_ACTION:
                Log.d("Dong", "--->" + json);
                JSONObject jsonObject = JSONObject.parseObject(json);
                if (payType == 1) {
                    String msg = jsonObject.getString("msg");
                    toastMessage("" + msg);
                } else if (payType == 2) {
                 //支付宝

                } else {
                    if (getRequestCode(json) == 1000) {
                        String data = jsonObject.getString("data");
                        wxPay(data);
                    }
                }

                break;
        }
    }


    /***
     * 微信充值
     * @param json
     */
    private void wxPay(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        WXPayBean.prepayid = jsonObject.getString("prepayid"); //预支付交易会话ID
        WXPayBean.noncestr = jsonObject.getString("noncestr"); //随机字符串
        WXPayBean.timestamp = jsonObject.getString("timestamp");//时间戳
        WXPayBean.sign = jsonObject.getString("sign"); //签名

        PayReq req = new PayReq();
        req.appId = MyApplication.wxAppID; //应用ID
        req.partnerId = MyApplication.MCH_ID; //商户号
        req.prepayId = WXPayBean.prepayid;//预支付交易会话ID
        req.packageValue = "Sign=WXPay"; //扩展字段
        req.nonceStr = WXPayBean.noncestr;
        req.timeStamp = WXPayBean.timestamp;
        req.sign = WXPayBean.sign;
        MyApplication.iwxapi.sendReq(req);
//        AppManager.getInstance().PushActivity(this);
    }

    private void handleQueryVipList(String json) {
        VipListBean bean = JSON.parseObject(json, VipListBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            }
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
        TextView tvChoosePayType1 =dialog.findViewById(R.id.tv_choose_pay_type1);
        TextView tvChoosePayType2 =dialog.findViewById(R.id.tv_choose_pay_type2);
        TextView tvChoosePayType3 =dialog.findViewById(R.id.tv_choose_pay_type3);
        tvChoosePayType1.setOnClickListener(this);
        tvChoosePayType2.setOnClickListener(this);
        tvChoosePayType3.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (WXPayEntryActivity.IS_WX_PAY_SUCESS) {
            finish();
        }
    }
}
