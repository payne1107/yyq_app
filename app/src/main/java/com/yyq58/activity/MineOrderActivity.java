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
import com.yyq58.activity.bean.MineOrderCountBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.HashMap;
import java.util.Map;

/*
* 我的订单
* */
public class MineOrderActivity extends BaseActivity implements View.OnClickListener {
    protected static final String EXTRA_MINE_ORDER_TITLE_NAME = "extra_mine_order_title_name";
    private Context mContext;
    private TextView tvSearch;
    private TextView tvPendingFile;
    private TextView tvUnpaid;
    private TextView tvConfirm;
    private TextView tvRefund;
    private TextView tvPendingFile2;
    private TextView tvIsSetFile;
    private TextView tvAskForPayment;
    private TextView tvConfirm2;
    private AutoLinearLayout layoutPendingFile;
    private AutoLinearLayout layoutUnpaid;
    private AutoLinearLayout layoutConfirm;
    private AutoLinearLayout layoutRefund;
    private AutoLinearLayout layoutPendingFile2;
    private AutoLinearLayout layoutIsSetFile;
    private AutoLinearLayout layoutAskForPayment;
    private AutoLinearLayout layoutConfirm2;
    private AutoLinearLayout layoutPendingFileContainer;
    private AutoLinearLayout layoutUnpaidContainer;
    private AutoLinearLayout layoutConfirmContainer;
    private AutoLinearLayout layoutRefundContainer;
    private AutoLinearLayout layoutPendingFile2Container;
    private AutoLinearLayout layoutIsSetFileContainer;
    private AutoLinearLayout layoutAskForPaymentContainer;
    private AutoLinearLayout layoutConfirm2Container;


    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mine_order);
        mContext = MineOrderActivity.this;
    }

    @Override
    protected void initData() {
        queryMineOrderCount(MyApplication.userId);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("我的订单", true, true);
        tvSearch = findViewById(R.id.tv_search);
        tvPendingFile = findViewById(R.id.tv_pending_file);
        tvUnpaid = findViewById(R.id.tv_unpaid);
        tvConfirm = findViewById(R.id.tv_confirm);
        tvRefund = findViewById(R.id.tv_refund);

        tvPendingFile2 = findViewById(R.id.tv_pending_file2);
        tvIsSetFile = findViewById(R.id.tv_is_set_file);
        tvAskForPayment = findViewById(R.id.tv_ask_for_payment);
        tvConfirm2 = findViewById(R.id.tv_confirm2);

        layoutPendingFile = findViewById(R.id.layout_pending_file);
        layoutUnpaid = findViewById(R.id.layotu_unpaid);
        layoutConfirm = findViewById(R.id.layout_confirm);
        layoutRefund = findViewById(R.id.layout_refund);
        layoutPendingFile2 = findViewById(R.id.layout_pending_file2);
        layoutIsSetFile = findViewById(R.id.layout_is_set_file);
        layoutAskForPayment = findViewById(R.id.layout_ask_for_payment);
        layoutConfirm2 = findViewById(R.id.layout_confirm2);

        layoutPendingFileContainer = findViewById(R.id.layout_pending_file_container);
        layoutUnpaidContainer = findViewById(R.id.layout_unpaid_container);
        layoutConfirmContainer = findViewById(R.id.layout_confirm);
        layoutRefundContainer = findViewById(R.id.layout_refund_container);
        layoutPendingFile2Container = findViewById(R.id.layout_pending_file2_container);
        layoutIsSetFileContainer = findViewById(R.id.layout_is_set_file_container);
        layoutAskForPaymentContainer = findViewById(R.id.layout_ask_for_payment_container);
        layoutConfirm2Container = findViewById(R.id.layout_confirm2_container);

        setListener();
    }

    private void setListener() {
        tvSearch.setOnClickListener(this);
        layoutPendingFileContainer.setOnClickListener(this);
        layoutUnpaidContainer.setOnClickListener(this);
        layoutConfirmContainer.setOnClickListener(this);
        layoutRefundContainer.setOnClickListener(this);
        layoutPendingFile2Container.setOnClickListener(this);
        layoutIsSetFileContainer.setOnClickListener(this);
        layoutAskForPaymentContainer.setOnClickListener(this);
        layoutConfirm2Container.setOnClickListener(this);
    }


    /****
     * 查询数量
     * @param consumerId
     */
    private void queryMineOrderCount(String consumerId) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", consumerId);
        httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_COUNT_URL, params, ConfigUtil.QUERY_MINE_ORDER_COUNT_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MINE_ORDER_COUNT_URL_ACTION:
                handleQueryMineOrderCount(json);
                break;
        }
    }

    private void handleQueryMineOrderCount(String json) {
        MineOrderCountBean bean = JSON.parseObject(json, MineOrderCountBean.class);
        if (bean != null) {
            MineOrderCountBean.DataBean data = bean.getData();
            if (data != null) {
                String transaction = data.getTransactions();
                String notPay = data.getNotPay();
                String notComplete = data.getNotComplete();
                String refund =data.getRefund();

                String qdTransactions = data.getQdTransactions();
                String qdNotPay = data.getQdNotPay();
                String qdNotComplete = data.getQdNotComplete();
                String qdComplete = data.getQdComplete();
                if (Integer.valueOf(transaction) > 0) {
                    tvPendingFile.setText(transaction);
                } else {
                    //隐藏
                    layoutPendingFile.setVisibility(View.GONE);
                }
                if (Integer.valueOf(notPay) > 0) {
                    tvUnpaid.setText(notPay);
                } else {
                    layoutUnpaid.setVisibility(View.GONE);
                }
                if (Integer.valueOf(notComplete) > 0) {
                    tvConfirm.setText(notComplete);
                } else {
                    layoutConfirm.setVisibility(View.GONE);
                }
                if (Integer.valueOf(refund) > 0) {
                    tvRefund.setText(refund);
                } else {
                    layoutRefund.setVisibility(View.GONE);
                }
                if (Integer.valueOf(qdTransactions) > 0) {
                    tvPendingFile2.setText(qdTransactions);
                } else {
                    layoutPendingFile2.setVisibility(View.GONE);
                }
                if (Integer.valueOf(qdNotPay) > 0) {
                    tvIsSetFile.setText(qdNotPay);
                } else {
                    layoutIsSetFile.setVisibility(View.GONE);
                }
                if (Integer.valueOf(qdNotComplete) > 0) {
                    tvAskForPayment.setText(qdNotComplete);
                } else {
                    layoutAskForPayment.setVisibility(View.GONE);
                }
                if (Integer.valueOf(qdComplete) > 0) {
                    tvConfirm2.setText(qdComplete);
                } else {
                    layoutConfirm2.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                //搜索
                break;
            case R.id.layout_pending_file_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "通告订单-待定档"));
                break;
            case R.id.layout_unpaid_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "通告订单-待支付"));
                break;
            case R.id.layout_confirm:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "通告订单-确定/完成"));
                break;
            case R.id.layout_refund_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "通告订单-申请退款"));
                break;
            case R.id.layout_pending_file2_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "抢单订单-待定档"));
                break;
            case R.id.layout_is_set_file_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "抢单订单-已定档"));
                break;
            case R.id.layout_ask_for_payment_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "抢单订单-催付款"));
                break;
            case R.id.layout_confirm2_container:
                startActivity(new Intent(mContext, MineOrderDetailsActivity.class).putExtra(EXTRA_MINE_ORDER_TITLE_NAME, "抢单订单-完成"));
                break;
        }
    }
}
