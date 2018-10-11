package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.AllBllDetailsAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.AllBillDetailsBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 账单明细
 */
public class AllBillDetailsActivity extends BaseActivity {
    private PullToRefreshListView listView;
    private List<AllBillDetailsBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private AllBllDetailsAdapter adapter;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_all_bill_details);
        mContext = AllBillDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("账单明细", true, true);
        listView = findViewById(R.id.listView);
        adapter = new AllBllDetailsAdapter(mContext,mList);
        listView.setAdapter(adapter);



        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                queryBillDetailsList();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        queryBillDetailsList();
    }

    /***
     * 获取账单明细
     */
    private void queryBillDetailsList() {
        startIOSDialogLoading(mContext, "加载中..");
        Map<String, String> params = new HashMap<>();
        params.put("consumerId", MyApplication.userId);
        httpPostRequest(ConfigUtil.QUERY_BILL_DETAILS_URL, params, ConfigUtil.QUERY_BILL_DETAILS_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_BILL_DETAILS_URL_ACTION:
                handleQueryBillDetails(json);
                break;
        }
    }

    private void handleQueryBillDetails(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        AllBillDetailsBean bean = JSON.parseObject(json, AllBillDetailsBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                adapter.setData(mList);
            }
        }
    }
}
