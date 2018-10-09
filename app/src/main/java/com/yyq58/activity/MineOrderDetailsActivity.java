package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.MineOrderDetailsAdapter;
import com.yyq58.activity.application.MyApplication;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.MineOrderForPayAndCompleteListBean;
import com.yyq58.activity.utils.ConfigUtil;
import com.yyq58.activity.widget.IButtonClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yyq58.activity.MineOrderActivity.EXTRA_MINE_ORDER_TITLE_NAME;
import static com.yyq58.activity.MineOrderActivity.EXTRA_MINE_ORDER_TYPE_CODE;

public class MineOrderDetailsActivity extends BaseActivity {

    private PullToRefreshListView listView;
    private List<MineOrderForPayAndCompleteListBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private MineOrderDetailsAdapter adapter;
    private int extraMineOrderTypeCode;
    private int page = 1;
    private boolean swipeLoadMore =false;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.layout_mine_order_details);
        mContext = MineOrderDetailsActivity.this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        String extraTitleName = getIntent().getStringExtra(EXTRA_MINE_ORDER_TITLE_NAME);
        extraMineOrderTypeCode = getIntent().getIntExtra(EXTRA_MINE_ORDER_TYPE_CODE, 0);
        setInVisibleTitleIcon(extraTitleName, true, true);

        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        adapter = new MineOrderDetailsAdapter(mContext,mList,extraMineOrderTypeCode);
        listView.setAdapter(adapter);

        setListener();

        queryMineOrderList(extraMineOrderTypeCode,page);
    }


    /***
     * 查询列表
     * @param code 类型区分
     * @param page 分页
     */
    private void queryMineOrderList(int code,int page) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("consumerId", MyApplication.userId);
        if (code == 1) {
            //待定档
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PENDING_URL, params, ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PENDING_URL_ACTION);
        } else if (code == 2) {
            //待支付
            params.put("state", "0");
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL, params, ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL_ACTION);
        } else if (code == 3) {
            //完成
            params.put("state", "1");
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL, params, ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL_ACTION);
        } else if (code == 4) {
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_REFUND_URL, params, ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_REFUND_URL_ACTION);
        } else if (code == 5) {
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_QD_PENDING_URL, params, ConfigUtil.QUERY_MINE_ORDER_QD_PENDING_URL_ACTION);
        } else if (code == 6) {
            params.put("state", "0");
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL, params, ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL_ACTION);
        } else if (code == 7) {
            params.put("state", "1");
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL, params, ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL_ACTION);
        } else {
            params.put("state", "2");
            httpPostRequest(ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL, params, ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL_ACTION);
        }
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);

        switch (action) {
            case ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PAY_AND_COMPLETE_URL_ACTION:
                handleQueryMineOrderListForPayAndComplete(json);
                break;
            case ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_REFUND_URL_ACTION:
                handleQueryMineOrderListForPayAndComplete(json);
                break;
            case ConfigUtil.QUERY_MINE_ORDER_LIST_FOR_PENDING_URL_ACTION:
                handleQueryMineOrderListForPayAndComplete(json);
                break;
            case ConfigUtil.QUERY_MINE_ORDER_QD_PAY_AND_NOT_PAY_URL_ACTION:
                handleQueryMineOrderListForPayAndComplete(json);
                break;
            case ConfigUtil.QUERY_MINE_ORDER_QD_PENDING_URL_ACTION:
                handleQueryMineOrderListForPayAndComplete(json);
                break;
        }
    }

    //通告--待支付 & 完成
    private void handleQueryMineOrderListForPayAndComplete(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        MineOrderForPayAndCompleteListBean bean = JSON.parseObject(json, MineOrderForPayAndCompleteListBean.class);
        if (bean != null) {
            mList = bean.getData();
            if (mList != null && mList.size() > 0) {
                if (swipeLoadMore) {
                    swipeLoadMore = false;
                    mList.addAll(mList.size(), bean.getData());
                    adapter.setData(mList);
                } else {
                    adapter.setData(mList);
                }
            } else {
                setEmptyView(listView);
            }
        }
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                queryMineOrderList(extraMineOrderTypeCode, page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++page;
                swipeLoadMore = true;
                queryMineOrderList(extraMineOrderTypeCode,page);
            }
        });

        adapter.setOnItemClickListener(new IButtonClickListener() {
            @Override
            public void onEditClick(View view, int position) {
                //联系
                toastMessage("position 联系" );
            }

            @Override
            public void onDeleClick(View view, int position) {
                //取消订单
                toastMessage("position 取消订单" );
            }

            @Override
            public void onSaveClick(View view, int position) {
                //支付
                toastMessage("position 支付" );
            }
        });
    }
}
