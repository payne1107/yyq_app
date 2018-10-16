package com.yyq58.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.itheima.pulltorefreshlib.PullToRefreshBase;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.yyq58.R;
import com.yyq58.activity.adapter.YRDynamicListAdapter;
import com.yyq58.activity.base.BaseActivity;
import com.yyq58.activity.bean.DynamicFragmentBean;
import com.yyq58.activity.utils.ConfigUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YRDynamicListActivity extends BaseActivity{

    private PullToRefreshListView listView;
    private List<DynamicFragmentBean.DataBean> mList = new ArrayList<>();
    private Context mContext;
    private YRDynamicListAdapter adapter;
    private int pageNo =1;
    private boolean swipeLoadMore =false;

    @Override
    protected void onCreateCustom(Bundle savedInstanceState) {
        setContentView(R.layout.activity_yr_dynamic_list);
        mContext = YRDynamicListActivity.this;
    }

    @Override
    protected void initData() {
        queryDynamicList(pageNo);
    }

    @Override
    protected void initView() {
        super.initView();
        setInVisibleTitleIcon("艺人动态", true, true);
        listView = findViewById(R.id.listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new YRDynamicListAdapter(mContext,mList);
        listView.setAdapter(adapter);

        setListener();
    }

    private void setListener() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                queryDynamicList(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ++pageNo;
                swipeLoadMore = true;
                queryDynamicList(pageNo);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /***
     * 查询动态
     *
     */
    private void queryDynamicList(int page) {
        startIOSDialogLoading(mContext, "");
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        httpPostRequest(ConfigUtil.QUERY_MINE_DAYNIC_URL, params, ConfigUtil.QUERY_MINE_DAYNIC_URL_ACTION);
    }

    @Override
    protected void httpOnResponse(String json, int action) {
        super.httpOnResponse(json, action);
        switch (action) {
            case ConfigUtil.QUERY_MINE_DAYNIC_URL_ACTION:
                handleQueryMineDaynicList(json);
                break;
        }
    }

    private void handleQueryMineDaynicList(String json) {
        if (listView != null && listView.isRefreshing()) {
            listView.onRefreshComplete();
        }
        DynamicFragmentBean bean = JSON.parseObject(json, DynamicFragmentBean.class);
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
}
